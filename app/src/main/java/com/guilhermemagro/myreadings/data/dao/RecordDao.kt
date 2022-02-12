package com.guilhermemagro.myreadings.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.guilhermemagro.myreadings.data.entities.Record

@Dao
interface RecordDao {
    @Query("UPDATE record SET pages_read = pages_read + 1 WHERE id = :recordId AND date = :date")
    suspend fun incrementRecordPages(bookId: Int, date: String)

    @Query("UPDATE record SET pages_read = pages_read - 1 WHERE id = :recordId AND date = :date")
    suspend fun decreaseRecordPages(bookId: Int, date: String)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(record: Record)

    @Insert
    suspend fun insert(record: Record)

    @Delete
    suspend fun delete(record: Record)
}
