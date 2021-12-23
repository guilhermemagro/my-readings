package com.guilhermemagro.myreadings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.guilhermemagro.myreadings.data.entities.Book

@Composable
fun BookItem(book: Book) {
    Column {
        BookItemDescription("Título: ", book.title)
        BookItemDescription("Total de páginas: ", book.totalPages.toString())
        BookItemDescription("Página atual: ", book.currentPage.toString())
    }
}

@Composable
fun BookItemDescription(description: String, descriptionValue: String) {
    Row {
       Text(text = description)
       Text(text = descriptionValue)
    }
}