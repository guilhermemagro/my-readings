package com.guilhermemagro.myreadings.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val goal: Int,
    @ColumnInfo(name = "total_pages") val totalPages: Int,
    @ColumnInfo(name = "current_page") val currentPage: Int,
    @ColumnInfo(name = "initial_page") val initialPage: Int,
)
