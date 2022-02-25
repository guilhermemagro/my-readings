package com.guilhermemagro.myreadings.data.entities

import androidx.room.TypeConverter
import com.guilhermemagro.myreadings.data.wrappers.DateWrapper

class Converters {
    @TypeConverter
    fun dateWrapperToDateString(date: DateWrapper): String {
        return date.getDate()
    }

    @TypeConverter
    fun dateStringToDateWrapper(date: String): DateWrapper {
        return DateWrapper.from(date)
    }
}