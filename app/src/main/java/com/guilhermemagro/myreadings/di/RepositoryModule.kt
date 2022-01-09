package com.guilhermemagro.myreadings.di

import com.guilhermemagro.myreadings.data.repositories.BookRepository
import com.guilhermemagro.myreadings.data.repositories.BookRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(
        bookRepositoryImpl: BookRepositoryImpl
    ) : BookRepository
}
