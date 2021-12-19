package com.guilhermemagro.myreadings.di

import com.guilhermemagro.myreadings.MainApplication
import com.guilhermemagro.myreadings.data.repositories.BookRepository
import com.guilhermemagro.myreadings.data.repositories.BookRepositoryImpl

object Injector {
    fun provideBookRepository(mainApplication: MainApplication): BookRepository {
        return BookRepositoryImpl(mainApplication.appDatabase)
    }
}