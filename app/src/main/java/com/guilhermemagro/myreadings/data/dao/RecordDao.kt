package com.guilhermemagro.myreadings.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.guilhermemagro.myreadings.data.entities.Record

@Dao
interface RecordDao {
    @Update
    fun update(record: Record)

    @Insert
    fun insert(record: Record)

    @Delete
    fun delete(record: Record)
}
