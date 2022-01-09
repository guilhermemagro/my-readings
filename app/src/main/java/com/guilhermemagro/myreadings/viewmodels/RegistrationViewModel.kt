package com.guilhermemagro.myreadings.viewmodels

import androidx.lifecycle.ViewModel
import com.guilhermemagro.myreadings.data.repositories.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val bookRepository: BookRepository
): ViewModel() {

}