package com.example.schedule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chapters")
data class Chapter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val pageNumber: Int,
    var isRead: Boolean = false
)