package com.guilhermemagro.myreadings.di

import com.guilhermemagro.myreadings.MyApplication
import com.guilhermemagro.myreadings.data.repositories.BookRepository
import com.guilhermemagro.myreadings.data.repositories.BookRepositoryImpl

object Injector {
    fun provideBookRepository(myApplication: MyApplication): BookRepository {
        return BookRepositoryImpl(myApplication.appDatabase)
    }
}