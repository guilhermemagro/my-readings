package com.guilhermemagro.myreadings.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.guilhermemagro.myreadings.data.wrappers.DateWrapper

@Entity
data class Book (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    @ColumnInfo(name = "total_pages") val totalPages: Int,
    @ColumnInfo(name = "initial_current_page") val initialCurrentPage: Int,
    @ColumnInfo(name = "current_page") val currentPage: Int = initialCurrentPage,
    @ColumnInfo(name = "registration_date") val registrationDate: DateWrapper
)
