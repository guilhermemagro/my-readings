package com.guilhermemagro.myreadings.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.guilhermemagro.myreadings.data.entities.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
//    @Query("SELECT * FROM book")
//    fun getAll(): Flow<List<Book>>

    @Insert
    suspend fun insert(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(book: Book)

    @Query("UPDATE book SET current_page = current_page + 1 WHERE id = :bookId")
    suspend fun incrementCurrentPage(bookId: Int)

    @Query("UPDATE book SET current_page = current_page - 1 WHERE id = :bookId")
    suspend fun decreaseCurrentPage(bookId: Int)
}
