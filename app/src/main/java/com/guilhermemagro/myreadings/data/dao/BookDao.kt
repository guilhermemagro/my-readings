package com.guilhermemagro.myreadings.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.guilhermemagro.myreadings.data.entities.Book

@Dao
interface BookDao {
    @Query("UPDATE book SET current_page = :currentPage WHERE id = :bookId")
    fun setCurrentPage(currentPage: Int, bookId: Int)

    @Query("SELECT * FROM book")
    fun getAll(): List<Book>

    @Insert
    fun insert(book: Book)

    @Delete
    fun delete(book: Book)
}
