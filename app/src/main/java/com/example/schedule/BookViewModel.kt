package com.example.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookViewModel(private val repository: ChapterRepository) : ViewModel() {

    val chapters: Flow<List<Chapter>> = repository.getAllChapters()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<Chapter>>(emptyList())
    val searchResults: StateFlow<List<Chapter>> = _searchResults.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching.asStateFlow()

    private val _stats = MutableStateFlow<StatsData>(StatsData())
    val stats: StateFlow<StatsData> = _stats.asStateFlow()

    data class StatsData(
        val totalChapters: Int = 0,
        val readChapters: Int = 0,
        val todayRead: Int = 0,
        val progress: Float = 0f,
        val bookStats: List<BookStat> = emptyList()
    )

    fun toggleReadStatus(chapter: Chapter) {
        viewModelScope.launch {
            repository.updateReadStatus(chapter.id, !chapter.isRead)
            updateStats()
        }
    }

    fun addSampleData() {
        viewModelScope.launch {
            repository.addSampleChapters()
            updateStats()
        }
    }

    fun clearAllData() {
        viewModelScope.launch {
            repository.clearAllChapters()
            updateStats()
        }
    }

    fun searchChapters(query: String) {
        _searchQuery.value = query
        if (query.isBlank()) {
            _isSearching.value = false
            _searchResults.value = emptyList()
        } else {
            _isSearching.value = true
            viewModelScope.launch {
                repository.searchChapters(query).collect { results ->
                    _searchResults.value = results
                }
            }
        }
    }

    fun clearSearch() {
        _searchQuery.value = ""
        _isSearching.value = false
        _searchResults.value = emptyList()
    }

    fun updateStats() {
        viewModelScope.launch {
            val total = repository.getTotalCount()
            val read = repository.getReadCount()
            val todayRead = repository.getTodayReadCount()
            val bookStats = repository.getBookStatistics()

            _stats.value = StatsData(
                totalChapters = total,
                readChapters = read,
                todayRead = todayRead,
                progress = if (total > 0) read.toFloat() / total else 0f,
                bookStats = bookStats
            )
        }
    }

    fun getDailyPlan(): List<Chapter>? {
        // Реализация ежедневного плана
        return null
    }
}