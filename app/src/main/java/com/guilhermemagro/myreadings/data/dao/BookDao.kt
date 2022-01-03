package com.guilhermemagro.myreadings.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.guilhermemagro.myreadings.data.entities.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("UPDATE book SET current_page = :currentPage WHERE id = :bookId")
    suspend fun setCurrentPage(currentPage: Int, bookId: Int)

    @Query("SELECT * FROM book")
    fun getAll(): Flow<List<Book>>

    @Insert
    suspend fun insert(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Update
    suspend fun update(book: Book)
}
