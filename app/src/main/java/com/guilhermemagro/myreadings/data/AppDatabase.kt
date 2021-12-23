package com.guilhermemagro.myreadings.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.guilhermemagro.myreadings.data.dao.BookAndRecordsDao
import com.guilhermemagro.myreadings.data.dao.BookDao
import com.guilhermemagro.myreadings.data.dao.RecordDao
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.Record
import com.guilhermemagro.myreadings.utils.DATABASE_NAME

@Database(entities = [Book::class, Record::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun recordDao(): RecordDao
    abstract fun bookAndRecordsDao(): BookAndRecordsDao
}
