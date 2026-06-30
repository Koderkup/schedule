package com.example.schedule

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ChapterDao {
    @Query("SELECT * FROM chapters ORDER BY bookCategory, pageNumber")
    fun getAllChapters(): Flow<List<Chapter>>

    @Query("SELECT * FROM chapters WHERE bookName LIKE '%' || :query || '%' OR bookCategory LIKE '%' || :query || '%' ORDER BY bookCategory, pageNumber")
    fun searchChapters(query: String): Flow<List<Chapter>>

    @Insert
    suspend fun insertChapter(chapter: Chapter)

    @Update
    suspend fun updateChapter(chapter: Chapter)

    @Query("UPDATE chapters SET isRead = :isRead, readDate = :readDate WHERE id = :chapterId")
    suspend fun updateReadStatusWithDate(chapterId: Int, isRead: Boolean, readDate: Long?)

    @Query("UPDATE chapters SET isRead = :isRead WHERE id = :chapterId")
    suspend fun updateReadStatus(chapterId: Int, isRead: Boolean)

    @Query("DELETE FROM chapters")
    suspend fun deleteAll()

    // Статистика
    @Query("SELECT COUNT(*) FROM chapters WHERE isRead = 1")
    suspend fun getReadCount(): Int

    @Query("SELECT COUNT(*) FROM chapters")
    suspend fun getTotalCount(): Int

    @Query("SELECT COUNT(*) FROM chapters WHERE isRead = 1 AND readDate >= :startDate")
    suspend fun getReadCountSince(startDate: Long): Int

    // Группировка по книгам для статистики
    @Query("""
        SELECT bookName, 
               COUNT(*) as total, 
               SUM(CASE WHEN isRead = 1 THEN 1 ELSE 0 END) as readCount 
        FROM chapters 
        GROUP BY bookName
        ORDER BY bookName
    """)
    suspend fun getBookStatistics(): List<BookStat>
}

// Класс для статистики по книгам
data class BookStat(
    val bookName: String,
    val total: Int,
    val readCount: Int
)