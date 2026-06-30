package com.example.schedule

import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import java.util.Date

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
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
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
            // КНИГИ МОИСЕЯ (Бытие)
            Chapter(
                bookName = "Бытие",
                bookCategory = "Книги Моисея",
                chapterRange = "1-4",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "Книги Моисея",
                chapterRange = "5-7",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "Книги Моисея",
                chapterRange = "8-10",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "Книги Моисея",
                chapterRange = "11-13",
                pageNumber = 1
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "Книги Моисея",
                chapterRange = "14-16",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "Книги Моисея",
                chapterRange = "17-19",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "Книги Моисея",
                chapterRange = "20-22",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "Книги Моисея",
                chapterRange = "23-26",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "Книги Моисея",
                chapterRange = "27, 28",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "Книги Моисея",
                chapterRange = "29-31",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "Книги Моисея",
                chapterRange = "32",
                pageNumber = 2
            ),
            Chapter(
                bookName = "Бытие",
                bookCategory = "Книги Моисея",
                chapterRange = "33, 34",
                pageNumber = 2
            ),

            // ВХОД ИЗРАИЛЬТЯН В ОБЕТОВАННУЮ ЗЕМЛЮ
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "1-4",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "5-7",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "8, 9",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "10-12",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "13-15",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "16-18",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "19-21",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Иисус Навин",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "22-24",
                pageNumber = 3
            ),

            // СУДЬИ
            Chapter(
                bookName = "Судьи",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "1, 2",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "3-5",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "6, 7",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "8, 9",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "10, 11",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "12, 13",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "14-16",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "17-19",
                pageNumber = 3
            ),
            Chapter(
                bookName = "Судьи",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "20, 21",
                pageNumber = 3
            ),

            // РУФЬ
            Chapter(
                bookName = "Руфь",
                bookCategory = "Вход в Обетованную землю",
                chapterRange = "1-4",
                pageNumber = 3
            ),

            // ВРЕМЕНА ПРАВЛЕНИЯ ИЗРАИЛЬСКИХ ЦАРЕЙ
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "1, 2",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "3-5",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "6, 7",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "8, 9",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "10-12",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "13, 14",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "15, 16",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "17, 18",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "19-21",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "22-24",
                pageNumber = 3
            ),

            Chapter(
                bookName = "2 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "1, 2",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "3-5",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "6-8",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "9-12",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "13, 14",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "15, 16",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "17, 18",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "19, 20",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "21, 22",
                pageNumber = 3
            ),
            Chapter(
                bookName = "2 Самуила",
                bookCategory = "Времена царей",
                chapterRange = "23, 24",
                pageNumber = 3
            ),

            Chapter(
                bookName = "1 Царей",
                bookCategory = "Времена царей",
                chapterRange = "1, 2",
                pageNumber = 3
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "Времена царей",
                chapterRange = "3-5",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "Времена царей",
                chapterRange = "6, 7",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "Времена царей",
                chapterRange = "8, 9",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "Времена царей",
                chapterRange = "10, 11",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "Времена царей",
                chapterRange = "12",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "Времена царей",
                chapterRange = "13, 14",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "Времена царей",
                chapterRange = "15-17",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "Времена царей",
                chapterRange = "18, 19",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "Времена царей",
                chapterRange = "20, 21",
                pageNumber = 4
            ),
            Chapter(
                bookName = "1 Царей",
                bookCategory = "Времена царей",
                chapterRange = "22",
                pageNumber = 4
            ),

            // ВОЗВРАЩЕНИЕ ЕВРЕЕВ ИЗ ПЛЕНА
            Chapter(
                bookName = "Ездра",
                bookCategory = "Возвращение из плена",
                chapterRange = "1-3",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Ездра",
                bookCategory = "Возвращение из плена",
                chapterRange = "4-7",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Ездра",
                bookCategory = "Возвращение из плена",
                chapterRange = "8-10",
                pageNumber = 4
            ),

            Chapter(
                bookName = "Неемия",
                bookCategory = "Возвращение из плена",
                chapterRange = "1-3",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Неемия",
                bookCategory = "Возвращение из плена",
                chapterRange = "4-6",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Неемия",
                bookCategory = "Возвращение из плена",
                chapterRange = "7, 8",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Неемия",
                bookCategory = "Возвращение из плена",
                chapterRange = "9, 10",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Неемия",
                bookCategory = "Возвращение из плена",
                chapterRange = "11-13",
                pageNumber = 4
            ),

            Chapter(
                bookName = "Эсфирь",
                bookCategory = "Возвращение из плена",
                chapterRange = "1-4",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Эсфирь",
                bookCategory = "Возвращение из плена",
                chapterRange = "5-10",
                pageNumber = 4
            ),

            // ИОВ
            Chapter(
                bookName = "Иов",
                bookCategory = "Книги Моисея",
                chapterRange = "1-5",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "Книги Моисея",
                chapterRange = "6-9",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "Книги Моисея",
                chapterRange = "10-14",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "Книги Моисея",
                chapterRange = "15-18",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "Книги Моисея",
                chapterRange = "19, 20",
                pageNumber = 4
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "Книги Моисея",
                chapterRange = "21-24",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "Книги Моисея",
                chapterRange = "25-29",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "Книги Моисея",
                chapterRange = "30, 31",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "Книги Моисея",
                chapterRange = "32-34",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "Книги Моисея",
                chapterRange = "35-38",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Иов",
                bookCategory = "Книги Моисея",
                chapterRange = "39-42",
                pageNumber = 5
            ),

            // ПОЭТИЧЕСКИЕ КНИГИ
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "1-8",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "9-16",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "17-19",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "20-25",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "26-31",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "32-35",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "36-38",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "39-42",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "43-47",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "48-52",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "53-58",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "59-64",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "65-68",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "69-72",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "73-77",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "78, 79",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "80-86",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "87-90",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "91-96",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "97-103",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "104, 105",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "106-108",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "109-115",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "116-119:63",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "119:64-176",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "120-129",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "130-138",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "139-144",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Псалмы",
                bookCategory = "Поэтические книги",
                chapterRange = "145-150",
                pageNumber = 6
            ),

            Chapter(
                bookName = "Притчи",
                bookCategory = "Поэтические книги",
                chapterRange = "1-4",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Притчи",
                bookCategory = "Поэтические книги",
                chapterRange = "5-8",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Притчи",
                bookCategory = "Поэтические книги",
                chapterRange = "9-12",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Притчи",
                bookCategory = "Поэтические книги",
                chapterRange = "13-16",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Притчи",
                bookCategory = "Поэтические книги",
                chapterRange = "17-19",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Притчи",
                bookCategory = "Поэтические книги",
                chapterRange = "20-22",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Притчи",
                bookCategory = "Поэтические книги",
                chapterRange = "23-27",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Притчи",
                bookCategory = "Поэтические книги",
                chapterRange = "28-31",
                pageNumber = 5
            ),

            Chapter(
                bookName = "Екклезиаст",
                bookCategory = "Поэтические книги",
                chapterRange = "1-4",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Екклезиаст",
                bookCategory = "Поэтические книги",
                chapterRange = "5-8",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Екклезиаст",
                bookCategory = "Поэтические книги",
                chapterRange = "9-12",
                pageNumber = 5
            ),

            Chapter(
                bookName = "Песнь Песней Соломона",
                bookCategory = "Поэтические книги",
                chapterRange = "1-8",
                pageNumber = 5
            ),

            // ПРОРОКИ
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "1-4",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "5-7",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "8-10",
                pageNumber = 5
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "11-14",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "15-19",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "20-24",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "25-28",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "29-31",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "32-35",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "36, 37",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "38-40",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "41-43",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "44-47",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "48-50",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "51-55",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "56-58",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "59-62",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Исаия",
                bookCategory = "Пророки",
                chapterRange = "63-66",
                pageNumber = 6
            ),

            Chapter(
                bookName = "Иеремия",
                bookCategory = "Пророки",
                chapterRange = "1-3",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "Пророки",
                chapterRange = "4, 5",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "Пророки",
                chapterRange = "6, 7",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "Пророки",
                chapterRange = "8-10",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "Пророки",
                chapterRange = "11-13",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "Пророки",
                chapterRange = "14-16",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "Пророки",
                chapterRange = "17-20",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "Пророки",
                chapterRange = "21-23",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "Пророки",
                chapterRange = "24-26",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "Пророки",
                chapterRange = "27-29",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "Пророки",
                chapterRange = "30, 31",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "Пророки",
                chapterRange = "32, 33",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "Пророки",
                chapterRange = "34-36",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "Пророки",
                chapterRange = "37-39",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иеремия",
                bookCategory = "Пророки",
                chapterRange = "40-42",
                pageNumber = 6
            ),

            Chapter(
                bookName = "Плач Иеремии",
                bookCategory = "Пророки",
                chapterRange = "1, 2",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Плач Иеремии",
                bookCategory = "Пророки",
                chapterRange = "3-5",
                pageNumber = 6
            ),

            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "1-3",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "4-6",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "7-9",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "10-12",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "13-15",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "16",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "17, 18",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "19-21",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "22, 23",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "24-26",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "27, 28",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "29-31",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "32, 33",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "34-36",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "37, 38",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "39, 40",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "41-43",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "44, 45",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Иезекииль",
                bookCategory = "Пророки",
                chapterRange = "46-48",
                pageNumber = 6
            ),

            Chapter(
                bookName = "Даниил",
                bookCategory = "Пророки",
                chapterRange = "1, 2",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Даниил",
                bookCategory = "Пророки",
                chapterRange = "3, 4",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Даниил",
                bookCategory = "Пророки",
                chapterRange = "5-7",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Даниил",
                bookCategory = "Пророки",
                chapterRange = "8-10",
                pageNumber = 6
            ),
            Chapter(
                bookName = "Даниил",
                bookCategory = "Пророки",
                chapterRange = "11, 12",
                pageNumber = 6
            ),

            Chapter(
                bookName = "Осия",
                bookCategory = "Пророки",
                chapterRange = "1-7",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Осия",
                bookCategory = "Пророки",
                chapterRange = "8-14",
                pageNumber = 7
            ),

            Chapter(
                bookName = "Иоиль",
                bookCategory = "Пророки",
                chapterRange = "1-3",
                pageNumber = 7
            ),

            Chapter(
                bookName = "Амос",
                bookCategory = "Пророки",
                chapterRange = "1-5",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Амос",
                bookCategory = "Пророки",
                chapterRange = "6-9",
                pageNumber = 7
            ),

            Chapter(
                bookName = "Авдий, Иона",
                bookCategory = "Пророки",
                chapterRange = "1-4",
                pageNumber = 7
            ),

            Chapter(
                bookName = "Михей",
                bookCategory = "Пророки",
                chapterRange = "1-7",
                pageNumber = 7
            ),

            Chapter(
                bookName = "Наум, Аввакум",
                bookCategory = "Пророки",
                chapterRange = "1-3",
                pageNumber = 7
            ),

            Chapter(
                bookName = "Софония, Аггей",
                bookCategory = "Пророки",
                chapterRange = "1-2",
                pageNumber = 7
            ),

            Chapter(
                bookName = "Захария",
                bookCategory = "Пророки",
                chapterRange = "1-7",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Захария",
                bookCategory = "Пророки",
                chapterRange = "8-11",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Захария",
                bookCategory = "Пророки",
                chapterRange = "12-14",
                pageNumber = 7
            ),

            Chapter(
                bookName = "Малахия",
                bookCategory = "Пророки",
                chapterRange = "1-4",
                pageNumber = 7
            ),

            // ПОВЕСТВОВАНИЕ О ЖИЗНИ И СЛУЖЕНИИ ХРИСТА
            Chapter(
                bookName = "Матфея",
                bookCategory = "Жизнь Христа",
                chapterRange = "1-4",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "Жизнь Христа",
                chapterRange = "5-7",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "Жизнь Христа",
                chapterRange = "8-10",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "Жизнь Христа",
                chapterRange = "11-13",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "Жизнь Христа",
                chapterRange = "14-17",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "Жизнь Христа",
                chapterRange = "18-20",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "Жизнь Христа",
                chapterRange = "21-23",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "Жизнь Христа",
                chapterRange = "24, 25",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "Жизнь Христа",
                chapterRange = "26",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Матфея",
                bookCategory = "Жизнь Христа",
                chapterRange = "27, 28",
                pageNumber = 7
            ),

            Chapter(
                bookName = "Марка",
                bookCategory = "Жизнь Христа",
                chapterRange = "1-3",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Марка",
                bookCategory = "Жизнь Христа",
                chapterRange = "4, 5",
                pageNumber = 7
            ),

            Chapter(
                bookName = "Луки",
                bookCategory = "Жизнь Христа",
                chapterRange = "1-2",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "Жизнь Христа",
                chapterRange = "3-5",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "Жизнь Христа",
                chapterRange = "6, 7",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "Жизнь Христа",
                chapterRange = "8, 9",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "Жизнь Христа",
                chapterRange = "10, 11",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "Жизнь Христа",
                chapterRange = "12, 13",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "Жизнь Христа",
                chapterRange = "14-17",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "Жизнь Христа",
                chapterRange = "18, 19",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "Жизнь Христа",
                chapterRange = "20-22",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Луки",
                bookCategory = "Жизнь Христа",
                chapterRange = "23, 24",
                pageNumber = 7
            ),

            Chapter(
                bookName = "Иоанна",
                bookCategory = "Жизнь Христа",
                chapterRange = "1-3",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Иоанна",
                bookCategory = "Жизнь Христа",
                chapterRange = "4, 5",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Иоанна",
                bookCategory = "Жизнь Христа",
                chapterRange = "6, 7",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Иоанна",
                bookCategory = "Жизнь Христа",
                chapterRange = "8, 9",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Иоанна",
                bookCategory = "Жизнь Христа",
                chapterRange = "10-12",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Иоанна",
                bookCategory = "Жизнь Христа",
                chapterRange = "13-15",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Иоанна",
                bookCategory = "Жизнь Христа",
                chapterRange = "16-18",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Иоанна",
                bookCategory = "Жизнь Христа",
                chapterRange = "19-21",
                pageNumber = 7
            ),

            // ОБРАЗОВАНИЕ И РОСТ ХРИСТИАНСКОГО СОБРАНИЯ
            Chapter(
                bookName = "Деяния",
                bookCategory = "Христианское собрание",
                chapterRange = "1-3",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "Христианское собрание",
                chapterRange = "4-6",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "Христианское собрание",
                chapterRange = "7, 8",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "Христианское собрание",
                chapterRange = "9-11",
                pageNumber = 7
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "Христианское собрание",
                chapterRange = "12-14",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "Христианское собрание",
                chapterRange = "15, 16",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "Христианское собрание",
                chapterRange = "17-19",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "Христианское собрание",
                chapterRange = "20, 21",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "Христианское собрание",
                chapterRange = "22, 23",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "Христианское собрание",
                chapterRange = "24-26",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Деяния",
                bookCategory = "Христианское собрание",
                chapterRange = "27, 28",
                pageNumber = 8
            ),

            // ПИСЬМА АПОСТОЛА ПАВЛА
            Chapter(
                bookName = "Римлянам",
                bookCategory = "Письма Павла",
                chapterRange = "1-3",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Римлянам",
                bookCategory = "Письма Павла",
                chapterRange = "4-7",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Римлянам",
                bookCategory = "Письма Павла",
                chapterRange = "8-11",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Римлянам",
                bookCategory = "Письма Павла",
                chapterRange = "12-16",
                pageNumber = 8
            ),

            Chapter(
                bookName = "1 Коринфянам",
                bookCategory = "Письма Павла",
                chapterRange = "1-6",
                pageNumber = 8
            ),
            Chapter(
                bookName = "1 Коринфянам",
                bookCategory = "Письма Павла",
                chapterRange = "7-10",
                pageNumber = 8
            ),
            Chapter(
                bookName = "1 Коринфянам",
                bookCategory = "Письма Павла",
                chapterRange = "11-14",
                pageNumber = 8
            ),
            Chapter(
                bookName = "1 Коринфянам",
                bookCategory = "Письма Павла",
                chapterRange = "15-16",
                pageNumber = 8
            ),

            Chapter(
                bookName = "2 Коринфянам",
                bookCategory = "Письма Павла",
                chapterRange = "1-6",
                pageNumber = 8
            ),
            Chapter(
                bookName = "2 Коринфянам",
                bookCategory = "Письма Павла",
                chapterRange = "7-10",
                pageNumber = 8
            ),
            Chapter(
                bookName = "2 Коринфянам",
                bookCategory = "Письма Павла",
                chapterRange = "11-13",
                pageNumber = 8
            ),

            Chapter(
                bookName = "Галатам",
                bookCategory = "Письма Павла",
                chapterRange = "1-6",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Ефесянам",
                bookCategory = "Письма Павла",
                chapterRange = "1-6",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Филиппийцам",
                bookCategory = "Письма Павла",
                chapterRange = "1-4",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Колоссянам",
                bookCategory = "Письма Павла",
                chapterRange = "1-4",
                pageNumber = 8
            ),
            Chapter(
                bookName = "1 Фессалоникийцам",
                bookCategory = "Письма Павла",
                chapterRange = "1-5",
                pageNumber = 8
            ),
            Chapter(
                bookName = "2 Фессалоникийцам",
                bookCategory = "Письма Павла",
                chapterRange = "1-3",
                pageNumber = 8
            ),
            Chapter(
                bookName = "1 Тимофею",
                bookCategory = "Письма Павла",
                chapterRange = "1-6",
                pageNumber = 8
            ),
            Chapter(
                bookName = "2 Тимофею",
                bookCategory = "Письма Павла",
                chapterRange = "1-4",
                pageNumber = 8
            ),

            Chapter(
                bookName = "Титу, Филимон",
                bookCategory = "Письма Павла",
                chapterRange = "1-3",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Евреям",
                bookCategory = "Письма Павла",
                chapterRange = "1-6",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Евреям",
                bookCategory = "Письма Павла",
                chapterRange = "7-10",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Евреям",
                bookCategory = "Письма Павла",
                chapterRange = "11-13",
                pageNumber = 8
            ),

            // КНИГИ ДРУГИХ АПОСТОЛОВ
            Chapter(
                bookName = "Иакова",
                bookCategory = "Другие апостолы",
                chapterRange = "1-5",
                pageNumber = 8
            ),
            Chapter(
                bookName = "1 Петра",
                bookCategory = "Другие апостолы",
                chapterRange = "1-5",
                pageNumber = 8
            ),
            Chapter(
                bookName = "2 Петра",
                bookCategory = "Другие апостолы",
                chapterRange = "1-3",
                pageNumber = 8
            ),
            Chapter(
                bookName = "1 Иоанна",
                bookCategory = "Другие апостолы",
                chapterRange = "1-5",
                pageNumber = 8
            ),
            Chapter(
                bookName = "2 и 3 Иоанна, Иуды",
                bookCategory = "Другие апостолы",
                chapterRange = "1-5",
                pageNumber = 8
            ),

            Chapter(
                bookName = "Откровение",
                bookCategory = "Другие апостолы",
                chapterRange = "1-4",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Откровение",
                bookCategory = "Другие апостолы",
                chapterRange = "5-9",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Откровение",
                bookCategory = "Другие апостолы",
                chapterRange = "10-14",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Откровение",
                bookCategory = "Другие апостолы",
                chapterRange = "15-18",
                pageNumber = 8
            ),
            Chapter(
                bookName = "Откровение",
                bookCategory = "Другие апостолы",
                chapterRange = "19-22",
                pageNumber = 8
            )
        )
    }
}