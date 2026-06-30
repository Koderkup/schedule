package com.example.schedule

import kotlinx.coroutines.flow.Flow

class ChapterRepository(private val chapterDao: ChapterDao) {

    fun getAllChapters(): Flow<List<Chapter>> = chapterDao.getAllChapters()

    fun searchChapters(query: String): Flow<List<Chapter>> = chapterDao.searchChapters(query)

    suspend fun addChapter(chapter: Chapter) {
        chapterDao.insertChapter(chapter)
    }

    suspend fun updateReadStatus(chapterId: Int, isRead: Boolean) {
        val readDate = if (isRead) System.currentTimeMillis() else null
        chapterDao.updateReadStatusWithDate(chapterId, isRead, readDate)
    }

    suspend fun getReadCount(): Int = chapterDao.getReadCount()
    suspend fun getTotalCount(): Int = chapterDao.getTotalCount()
    suspend fun getReadCountSince(startDate: Long): Int = chapterDao.getReadCountSince(startDate)
    suspend fun getBookStatistics(): List<BookStat> = chapterDao.getBookStatistics()

    suspend fun getTodayReadCount(): Int {
        val calendar = java.util.Calendar.getInstance()
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0)
        calendar.set(java.util.Calendar.MINUTE, 0)
        calendar.set(java.util.Calendar.SECOND, 0)
        calendar.set(java.util.Calendar.MILLISECOND, 0)
        return chapterDao.getReadCountSince(calendar.timeInMillis)
    }

    suspend fun getProgress(): Pair<Int, Int> {
        val read = chapterDao.getReadCount()
        val total = chapterDao.getTotalCount()
        return Pair(read, total)
    }

    suspend fun addSampleChapters() {
        val chapters = getBibleChapters()
        chapters.forEach { chapterDao.insertChapter(it) }
    }

    suspend fun clearAllChapters() {
        chapterDao.deleteAll()
    }

    private fun getBibleChapters(): List<Chapter> {
        return listOf(
            // ========== 1. КНИГИ МОИСЕЯ (65 шагов) ==========
            // Бытие (17)
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "1-2",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "3-4",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "5-6",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "7-8",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "9-10",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "11-12",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "13-14",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "15-16",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "17-18",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "19-20",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "21-22",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "23-24",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "25-26",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "27-28",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "29-30",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "31-32",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "33-34",
                pageNumber = 1
            ),

            // Исход (14)
            Chapter(
                bookName = "Исход",
                bookCategory = "1. Книги Моисея",
                chapterRange = "1-3",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Исход",
                bookCategory = "1. Книги Моисея",
                chapterRange = "4-6",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Исход",
                bookCategory = "1. Книги Моисея",
                chapterRange = "7-9",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Исход",
                bookCategory = "1. Книги Моисея",
                chapterRange = "10-12",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Исход",
                bookCategory = "1. Книги Моисея",
                chapterRange = "13-15",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Исход",
                bookCategory = "1. Книги Моисея",
                chapterRange = "16-18",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Исход",
                bookCategory = "1. Книги Моисея",
                chapterRange = "19-21",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Исход",
                bookCategory = "1. Книги Моисея",
                chapterRange = "22-24",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Исход",
                bookCategory = "1. Книги Моисея",
                chapterRange = "25-27",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Исход",
                bookCategory = "1. Книги Моисея",
                chapterRange = "28-30",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Исход",
                bookCategory = "1. Книги Моисея",
                chapterRange = "31-33",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Исход",
                bookCategory = "1. Книги Моисея",
                chapterRange = "34-36",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Исход",
                bookCategory = "1. Книги Моисея",
                chapterRange = "37-38",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Исход",
                bookCategory = "1. Книги Моисея",
                chapterRange = "39-40",
                pageNumber = 2
            ),

            // Левит (10)
            Chapter(
                bookName = "Левит",
                bookCategory = "1. Книги Моисея",
                chapterRange = "1-3",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Левит",
                bookCategory = "1. Книги Моисея",
                chapterRange = "4-6",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Левит",
                bookCategory = "1. Книги Моисея",
                chapterRange = "7-9",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Левит",
                bookCategory = "1. Книги Моисея",
                chapterRange = "10-12",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Левит",
                bookCategory = "1. Книги Моисея",
                chapterRange = "13-15",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Левит",
                bookCategory = "1. Книги Моисея",
                chapterRange = "16-18",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Левит",
                bookCategory = "1. Книги Моисея",
                chapterRange = "19-21",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Левит",
                bookCategory = "1. Книги Моисея",
                chapterRange = "22-24",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Левит",
                bookCategory = "1. Книги Моисея",
                chapterRange = "25-27",
                pageNumber = 3
            ),

            // Числа (12)
            Chapter(
                bookName = "Числа",
                bookCategory = "1. Книги Моисея",
                chapterRange = "1-3",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Числа",
                bookCategory = "1. Книги Моисея",
                chapterRange = "4-6",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Числа",
                bookCategory = "1. Книги Моисея",
                chapterRange = "7-9",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Числа",
                bookCategory = "1. Книги Моисея",
                chapterRange = "10-12",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Числа",
                bookCategory = "1. Книги Моисея",
                chapterRange = "13-15",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Числа",
                bookCategory = "1. Книги Моисея",
                chapterRange = "16-18",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Числа",
                bookCategory = "1. Книги Моисея",
                chapterRange = "19-21",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Числа",
                bookCategory = "1. Книги Моисея",
                chapterRange = "22-24",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Числа",
                bookCategory = "1. Книги Моисея",
                chapterRange = "25-27",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Числа",
                bookCategory = "1. Книги Моисея",
                chapterRange = "28-30",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Числа",
                bookCategory = "1. Книги Моисея",
                chapterRange = "31-33",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Числа",
                bookCategory = "1. Книги Моисея",
                chapterRange = "34-36",
                pageNumber = 3
            ),

            // Второзаконие (12)
            Chapter(
                bookName = "Второзаконие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "1-3",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Второзаконие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "4-6",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Второзаконие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "7-9",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Второзаконие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "10-12",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Второзаконие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "13-15",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Второзаконие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "16-18",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Второзаконие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "19-21",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Второзаконие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "22-24",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Второзаконие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "25-27",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Второзаконие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "28-30",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Второзаконие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "31-32",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Второзаконие",
                bookCategory = "1. Книги Моисея",
                chapterRange = "33-34",
                pageNumber = 3
            ),


            // ========== 2. ВХОД В ОБЕТОВАННУЮ ЗЕМЛЮ (18 шагов) ==========
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "1-4",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "5-7",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "8-9",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "10-12",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "13-15",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "16-18",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "19-21",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "22-24",
                pageNumber = 3
            ),

            Chapter(
                bookName = "Судьи",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "1-2",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "3-5",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "6-7",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "8-9",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "10-11",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "12-13",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "14-16",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "17-19",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "20-21",
                pageNumber = 3
            ),

            Chapter(
                bookName = "Руфь",
                bookCategory = "2. Вход в Обетованную землю",
                chapterRange = "1-4",
                pageNumber = 3
            ),


            // ========== 3. ВРЕМЕНА ЦАРЕЙ (62 шага) ==========
            // 1 Самуила (10)
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "1-2",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "3-5",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "6-7",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "8-9",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "10-12",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "13-14",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "15-16",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "17-18",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "19-21",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "22-24",
                pageNumber = 3
            ),

            // 2 Самуила (10)
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "1-2",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "3-5",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "6-8",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "9-12",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "13-14",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "15-16",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "17-18",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "19-20",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "21-22",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "3. Времена царей",
                chapterRange = "23-24",
                pageNumber = 3
            ),

            // 1 Царей (11)
            Chapter(
                bookName = "1 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "1-2",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "3-5",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "6-7",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "8-9",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "10-11",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "12",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "13-14",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "15-17",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "18-19",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "20-21",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "22",
                pageNumber = 4
            ),

            // 2 Царей (9)
            Chapter(
                bookName = "2 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "1-2",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "3-5",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "6-7",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "8-10",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "11-13",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "14-16",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "17-19",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "20-22",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Царей",
                bookCategory = "3. Времена царей",
                chapterRange = "23-25",
                pageNumber = 4
            ),

            // 1 Летопись (10)
            Chapter(
                bookName = "1 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "1-3",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "4-6",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "7-9",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "10-12",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "13-15",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "16-18",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "19-21",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "22-24",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "25-27",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "28-29",
                pageNumber = 4
            ),

            // 2 Летопись (12)
            Chapter(
                bookName = "2 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "1-3",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "4-6",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "7-9",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "10-12",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "13-15",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "16-18",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "19-21",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "22-24",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "25-27",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "28-30",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "31-33",
                pageNumber = 4
            ),
            Chapter(
                bookName = "2 Летопись",
                bookCategory = "3. Времена царей",
                chapterRange = "34-36",
                pageNumber = 4
            ),


            // ========== 4. ВОЗВРАЩЕНИЕ ИЗ ПЛЕНА (10 шагов) ==========
            Chapter(
                bookName = "Ездра",
                bookCategory = "4. Возвращение из плена",
                chapterRange = "1-3",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Ездра",
                bookCategory = "4. Возвращение из плена",
                chapterRange = "4-7",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Ездра",
                bookCategory = "4. Возвращение из плена",
                chapterRange = "8-10",
                pageNumber = 4
            ),

            Chapter(
                bookName = "Неемия",
                bookCategory = "4. Возвращение из плена",
                chapterRange = "1-3",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Неемия",
                bookCategory = "4. Возвращение из плена",
                chapterRange = "4-6",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Неемия",
                bookCategory = "4. Возвращение из плена",
                chapterRange = "7-8",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Неемия",
                bookCategory = "4. Возвращение из плена",
                chapterRange = "9-10",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Неемия",
                bookCategory = "4. Возвращение из плена",
                chapterRange = "11-13",
                pageNumber = 4
            ),

            Chapter(
                bookName = "Эсфирь",
                bookCategory = "4. Возвращение из плена",
                chapterRange = "1-4",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Эсфирь",
                bookCategory = "4. Возвращение из плена",
                chapterRange = "5-10",
                pageNumber = 4
            ),


            // ========== 5. ПОЭТИЧЕСКИЕ КНИГИ (42 шага) ==========
            // Иов (11)
            Chapter(
                bookName = "Иов",
                bookCategory = "5. Поэтические книги",
                chapterRange = "1-5",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "5. Поэтические книги",
                chapterRange = "6-9",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "5. Поэтические книги",
                chapterRange = "10-14",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "5. Поэтические книги",
                chapterRange = "15-18",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "5. Поэтические книги",
                chapterRange = "19-20",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "5. Поэтические книги",
                chapterRange = "21-24",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "5. Поэтические книги",
                chapterRange = "25-29",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "5. Поэтические книги",
                chapterRange = "30-31",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "5. Поэтические книги",
                chapterRange = "32-34",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "5. Поэтические книги",
                chapterRange = "35-38",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "5. Поэтические книги",
                chapterRange = "39-42",
                pageNumber = 5
            ),

            // Псалмы (30)
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "1-5",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "6-10",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "11-15",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "16-20",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "21-25",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "26-30",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "31-35",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "36-40",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "41-45",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "46-50",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "51-55",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "56-60",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "61-65",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "66-70",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "71-75",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "76-80",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "81-85",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "86-90",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "91-95",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "96-100",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "101-105",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "106-110",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "111-115",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "116-120",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "121-125",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "126-130",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "131-135",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "136-140",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "141-145",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "5. Поэтические книги",
                chapterRange = "146-150",
                pageNumber = 5
            ),

            // Притчи (8)
            Chapter(
                bookName = "Притчи",
                bookCategory = "5. Поэтические книги",
                chapterRange = "1-4",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Притчи",
                bookCategory = "5. Поэтические книги",
                chapterRange = "5-8",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Притчи",
                bookCategory = "5. Поэтические книги",
                chapterRange = "9-12",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Притчи",
                bookCategory = "5. Поэтические книги",
                chapterRange = "13-16",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Притчи",
                bookCategory = "5. Поэтические книги",
                chapterRange = "17-20",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Притчи",
                bookCategory = "5. Поэтические книги",
                chapterRange = "21-24",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Притчи",
                bookCategory = "5. Поэтические книги",
                chapterRange = "25-28",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Притчи",
                bookCategory = "5. Поэтические книги",
                chapterRange = "29-31",
                pageNumber = 5
            ),

            // Екклезиаст (3)
            Chapter(
                bookName = "Екклезиаст",
                bookCategory = "5. Поэтические книги",
                chapterRange = "1-4",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Екклезиаст",
                bookCategory = "5. Поэтические книги",
                chapterRange = "5-8",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Екклезиаст",
                bookCategory = "5. Поэтические книги",
                chapterRange = "9-12",
                pageNumber = 5
            ),

            // Песнь Песней (1)
            Chapter(
                bookName = "Песнь Песней",
                bookCategory = "5. Поэтические книги",
                chapterRange = "1-8",
                pageNumber = 5
            ),


            // ========== 6. ПРОРОКИ (98 шагов) ==========
            // Исаия (18)
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "1-4",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "5-7",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "8-10",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "11-14",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "15-19",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "20-24",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "25-28",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "29-31",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "32-35",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "36-37",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "38-40",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "41-43",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "44-47",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "48-50",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "51-55",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "56-58",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "59-62",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "6. Пророки",
                chapterRange = "63-66",
                pageNumber = 6
            ),

            // Иеремия (15)
            Chapter(
                bookName = "Иеремия",
                bookCategory = "6. Пророки",
                chapterRange = "1-3",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "6. Пророки",
                chapterRange = "4-5",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "6. Пророки",
                chapterRange = "6-7",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "6. Пророки",
                chapterRange = "8-10",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "6. Пророки",
                chapterRange = "11-13",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "6. Пророки",
                chapterRange = "14-16",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "6. Пророки",
                chapterRange = "17-20",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "6. Пророки",
                chapterRange = "21-23",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "6. Пророки",
                chapterRange = "24-26",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "6. Пророки",
                chapterRange = "27-29",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "6. Пророки",
                chapterRange = "30-31",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "6. Пророки",
                chapterRange = "32-33",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "6. Пророки",
                chapterRange = "34-36",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "6. Пророки",
                chapterRange = "37-39",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "6. Пророки",
                chapterRange = "40-42",
                pageNumber = 6
            ),

            // Плач Иеремии (2)
            Chapter(
                bookName = "Плач Иеремии",
                bookCategory = "6. Пророки",
                chapterRange = "1-2",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Плач Иеремии",
                bookCategory = "6. Пророки",
                chapterRange = "3-5",
                pageNumber = 6
            ),

            // Иезекииль (19)
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "1-3",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "4-6",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "7-9",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "10-12",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "13-15",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "16",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "17-18",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "19-21",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "22-23",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "24-26",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "27-28",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "29-31",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "32-33",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "34-36",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "37-38",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "39-40",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "41-43",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "44-45",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "6. Пророки",
                chapterRange = "46-48",
                pageNumber = 6
            ),

            // Даниил (5)
            Chapter(
                bookName = "Даниил",
                bookCategory = "6. Пророки",
                chapterRange = "1-2",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Даниил",
                bookCategory = "6. Пророки",
                chapterRange = "3-4",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Даниил",
                bookCategory = "6. Пророки",
                chapterRange = "5-7",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Даниил",
                bookCategory = "6. Пророки",
                chapterRange = "8-10",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Даниил",
                bookCategory = "6. Пророки",
                chapterRange = "11-12",
                pageNumber = 6
            ),

            // 12 малых пророков (39)
            Chapter(
                bookName = "Осия",
                bookCategory = "6. Пророки",
                chapterRange = "1-7",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Осия",
                bookCategory = "6. Пророки",
                chapterRange = "8-14",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Иоиль",
                bookCategory = "6. Пророки",
                chapterRange = "1-3",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Амос",
                bookCategory = "6. Пророки",
                chapterRange = "1-5",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Амос",
                bookCategory = "6. Пророки",
                chapterRange = "6-9",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Авдий, Иона",
                bookCategory = "6. Пророки",
                chapterRange = "1-4",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Михей",
                bookCategory = "6. Пророки",
                chapterRange = "1-7",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Наум, Аввакум",
                bookCategory = "6. Пророки",
                chapterRange = "1-3",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Софония, Аггей",
                bookCategory = "6. Пророки",
                chapterRange = "1-2",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Захария",
                bookCategory = "6. Пророки",
                chapterRange = "1-7",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Захария",
                bookCategory = "6. Пророки",
                chapterRange = "8-11",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Захария",
                bookCategory = "6. Пророки",
                chapterRange = "12-14",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Малахия",
                bookCategory = "6. Пророки",
                chapterRange = "1-4",
                pageNumber = 7
            ),


            // ========== 7. ЖИЗНЬ ХРИСТА (30 шагов) ==========
            // Матфея (10)
            Chapter(
                bookName = "Матфея",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "1-4",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "5-7",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "8-10",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "11-13",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "14-17",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "18-20",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "21-23",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "24-25",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "26",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "27-28",
                pageNumber = 7
            ),

            // Марка (6)
            Chapter(
                bookName = "Марка",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "1-3",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Марка",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "4-5",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Марка",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "6-8",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Марка",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "9-11",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Марка",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "12-14",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Марка",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "15-16",
                pageNumber = 7
            ),

            // Луки (10)
            Chapter(
                bookName = "Луки",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "1-2",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "3-5",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "6-7",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "8-9",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "10-11",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "12-13",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "14-17",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "18-19",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "20-22",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "23-24",
                pageNumber = 7
            ),

            // Иоанна (7)
            Chapter(
                bookName = "Иоанна",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "1-3",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Иоанна",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "4-5",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Иоанна",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "6-7",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Иоанна",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "8-9",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Иоанна",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "10-12",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Иоанна",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "13-15",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Иоанна",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "16-18",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Иоанна",
                bookCategory = "7. Жизнь Христа",
                chapterRange = "19-21",
                pageNumber = 7
            ),


            // ========== 8. ХРИСТИАНСКОЕ СОБРАНИЕ (11 шагов) ==========
            Chapter(
                bookName = "Деяния",
                bookCategory = "8. Христианское собрание",
                chapterRange = "1-3",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "8. Христианское собрание",
                chapterRange = "4-6",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "8. Христианское собрание",
                chapterRange = "7-8",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "8. Христианское собрание",
                chapterRange = "9-11",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "8. Христианское собрание",
                chapterRange = "12-14",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "8. Христианское собрание",
                chapterRange = "15-16",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "8. Христианское собрание",
                chapterRange = "17-19",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "8. Христианское собрание",
                chapterRange = "20-21",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "8. Христианское собрание",
                chapterRange = "22-23",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "8. Христианское собрание",
                chapterRange = "24-26",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "8. Христианское собрание",
                chapterRange = "27-28",
                pageNumber = 8
            ),


            // ========== 9. ПИСЬМА ПАВЛА (24 шага) ==========
            Chapter(
                bookName = "Римлянам",
                bookCategory = "9. Письма Павла",
                chapterRange = "1-3",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Римлянам",
                bookCategory = "9. Письма Павла",
                chapterRange = "4-7",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Римлянам",
                bookCategory = "9. Письма Павла",
                chapterRange = "8-11",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Римлянам",
                bookCategory = "9. Письма Павла",
                chapterRange = "12-16",
                pageNumber = 8
            ),

            Chapter(
                bookName = "1 Коринфянам",
                bookCategory = "9. Письма Павла",
                chapterRange = "1-6",
                pageNumber = 8
            ),
            Chapter(
                bookName = "1 Коринфянам",
                bookCategory = "9. Письма Павла",
                chapterRange = "7-10",
                pageNumber = 8
            ),
            Chapter(
                bookName = "1 Коринфянам",
                bookCategory = "9. Письма Павла",
                chapterRange = "11-14",
                pageNumber = 8
            ),
            Chapter(
                bookName = "1 Коринфянам",
                bookCategory = "9. Письма Павла",
                chapterRange = "15-16",
                pageNumber = 8
            ),

            Chapter(
                bookName = "2 Коринфянам",
                bookCategory = "9. Письма Павла",
                chapterRange = "1-6",
                pageNumber = 8
            ),
            Chapter(
                bookName = "2 Коринфянам",
                bookCategory = "9. Письма Павла",
                chapterRange = "7-10",
                pageNumber = 8
            ),
            Chapter(
                bookName = "2 Коринфянам",
                bookCategory = "9. Письма Павла",
                chapterRange = "11-13",
                pageNumber = 8
            ),

            Chapter(
                bookName = "Галатам",
                bookCategory = "9. Письма Павла",
                chapterRange = "1-6",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Ефесянам",
                bookCategory = "9. Письма Павла",
                chapterRange = "1-6",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Филиппийцам",
                bookCategory = "9. Письма Павла",
                chapterRange = "1-4",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Колоссянам",
                bookCategory = "9. Письма Павла",
                chapterRange = "1-4",
                pageNumber = 8
            ),
            Chapter(
                bookName = "1 Фессалоникийцам",
                bookCategory = "9. Письма Павла",
                chapterRange = "1-5",
                pageNumber = 8
            ),
            Chapter(
                bookName = "2 Фессалоникийцам",
                bookCategory = "9. Письма Павла",
                chapterRange = "1-3",
                pageNumber = 8
            ),
            Chapter(
                bookName = "1 Тимофею",
                bookCategory = "9. Письма Павла",
                chapterRange = "1-6",
                pageNumber = 8
            ),
            Chapter(
                bookName = "2 Тимофею",
                bookCategory = "9. Письма Павла",
                chapterRange = "1-4",
                pageNumber = 8
            ),

            Chapter(
                bookName = "Титу, Филимону",
                bookCategory = "9. Письма Павла",
                chapterRange = "1-3",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Евреям",
                bookCategory = "9. Письма Павла",
                chapterRange = "1-6",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Евреям",
                bookCategory = "9. Письма Павла",
                chapterRange = "7-10",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Евреям",
                bookCategory = "9. Письма Павла",
                chapterRange = "11-13",
                pageNumber = 8
            ),


            // ========== 10. ДРУГИЕ АПОСТОЛЫ (10 шагов) ==========
            Chapter(
                bookName = "Иакова",
                bookCategory = "10. Другие апостолы",
                chapterRange = "1-5",
                pageNumber = 8
            ),
            Chapter(
                bookName = "1 Петра",
                bookCategory = "10. Другие апостолы",
                chapterRange = "1-5",
                pageNumber = 8
            ),
            Chapter(
                bookName = "2 Петра",
                bookCategory = "10. Другие апостолы",
                chapterRange = "1-3",
                pageNumber = 8
            ),
            Chapter(
                bookName = "1 Иоанна",
                bookCategory = "10. Другие апостолы",
                chapterRange = "1-5",
                pageNumber = 8
            ),
            Chapter(
                bookName = "2 и 3 Иоанна, Иуды",
                bookCategory = "10. Другие апостолы",
                chapterRange = "1-5",
                pageNumber = 8
            ),

            Chapter(
                bookName = "Откровение",
                bookCategory = "10. Другие апостолы",
                chapterRange = "1-4",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Откровение",
                bookCategory = "10. Другие апостолы",
                chapterRange = "5-9",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Откровение",
                bookCategory = "10. Другие апостолы",
                chapterRange = "10-14",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Откровение",
                bookCategory = "10. Другие апостолы",
                chapterRange = "15-18",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Откровение",
                bookCategory = "10. Другие апостолы",
                chapterRange = "19-22",
                pageNumber = 8
            )
        )
    }
}