package com.example.schedule

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ChapterDao {
    @Query("SELECT * FROM chapters ORDER BY pageNumber")
    fun getAllChapters(): Flow<List<Chapter>>

    @Insert
    suspend fun insertChapter(chapter: Chapter)

    @Update
    suspend fun updateChapter(chapter: Chapter)

    @Query("UPDATE chapters SET isRead = :isRead WHERE id = :chapterId")
    suspend fun updateReadStatus(chapterId: Int, isRead: Boolean)

    @Query("DELETE FROM chapters")
    suspend fun deleteAll()
}