package com.example.schedule

import kotlinx.coroutines.flow.Flow

class ChapterRepository(private val chapterDao: ChapterDao) {

    fun getAllChapters(): Flow<List<Chapter>> = chapterDao.getAllChapters()

    suspend fun addChapter(chapter: Chapter) {
        chapterDao.insertChapter(chapter)
    }

    suspend fun updateReadStatus(chapterId: Int, isRead: Boolean) {
        chapterDao.updateReadStatus(chapterId, isRead)
    }

    suspend fun addSampleChapters() {
        val chapters = listOf(
            Chapter(title = "Введение в программирование", pageNumber = 1, isRead = false),
            Chapter(title = "Основы Kotlin", pageNumber = 15, isRead = false),
            Chapter(title = "Функции и классы", pageNumber = 42, isRead = false),
            Chapter(title = "Работа с коллекциями", pageNumber = 78, isRead = false),
            Chapter(title = "Coroutines и Flow", pageNumber = 112, isRead = false),
            Chapter(title = "Android разработка", pageNumber = 156, isRead = false),
            Chapter(title = "Compose UI", pageNumber = 203, isRead = false),
            Chapter(title = "Базы данных Room", pageNumber = 245, isRead = false),
            Chapter(title = "Сетевые запросы", pageNumber = 289, isRead = false),
            Chapter(title = "Тестирование", pageNumber = 324, isRead = false),
            Chapter(title = "Заключение", pageNumber = 358, isRead = false)
        )
        chapters.forEach { chapterDao.insertChapter(it) }
    }
}