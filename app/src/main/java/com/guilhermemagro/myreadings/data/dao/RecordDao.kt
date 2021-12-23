package com.guilhermemagro.myreadings.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.guilhermemagro.myreadings.data.entities.Record

@Dao
interface RecordDao {
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(record: Record)

    @Insert
    suspend fun insert(record: Record)

    @Delete
    suspend fun delete(record: Record)
}
