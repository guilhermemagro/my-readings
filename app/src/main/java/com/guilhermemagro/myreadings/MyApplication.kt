package com.guilhermemagro.myreadings

import android.app.Application
import com.guilhermemagro.myreadings.data.AppDatabase

class MyApplication : Application() {
    val appDatabase: AppDatabase = AppDatabase.getInstance(this)
}