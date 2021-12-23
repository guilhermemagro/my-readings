package com.guilhermemagro.myreadings.di

import com.guilhermemagro.myreadings.data.repositories.BookRepository
import com.guilhermemagro.myreadings.data.repositories.BookRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBookRepository(
        bookRepositoryImpl: BookRepositoryImpl
    ) : BookRepository
}
