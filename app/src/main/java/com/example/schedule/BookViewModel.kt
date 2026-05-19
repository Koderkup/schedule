package com.example.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BookViewModel(private val repository: ChapterRepository) : ViewModel() {

    val chapters: Flow<List<Chapter>> = repository.getAllChapters()

    fun toggleReadStatus(chapter: Chapter) {
        viewModelScope.launch {
            repository.updateReadStatus(chapter.id, !chapter.isRead)
        }
    }

    fun addSampleData() {
        viewModelScope.launch {
            repository.addSampleChapters()
        }
    }
}