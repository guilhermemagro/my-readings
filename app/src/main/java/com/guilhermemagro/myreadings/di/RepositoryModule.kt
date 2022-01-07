package com.guilhermemagro.myreadings.di

import com.guilhermemagro.myreadings.data.repositories.EditRepository
import com.guilhermemagro.myreadings.data.repositories.EditRepositoryImpl
import com.guilhermemagro.myreadings.data.repositories.HomeRepository
import com.guilhermemagro.myreadings.data.repositories.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(
        bookRepositoryImpl: HomeRepositoryImpl
    ) : HomeRepository

    @Binds
    abstract fun bindEditRepository(
        editRepositoryImpl: EditRepositoryImpl
    ) : EditRepository
}
