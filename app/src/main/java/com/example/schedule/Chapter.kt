package com.example.schedule

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "chapters")
data class Chapter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bookName: String,
    val bookCategory: String,
    val chapterRange: String,
    val pageNumber: Int,
    var isRead: Boolean = false,
    var readDate: Long? = null
)