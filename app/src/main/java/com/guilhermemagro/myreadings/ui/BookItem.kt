package com.guilhermemagro.myreadings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.guilhermemagro.myreadings.data.entities.Book

@Composable
fun BookItem(book: Book) {
    Column {
        BookItemDescription(description = "Título: ", descriptionValue = book.title)
        BookItemDescription(description = "Objetivo: ", descriptionValue = book.goal.toString())
        BookItemDescription(description = "Total de páginas: ", descriptionValue = book.totalPages.toString())
        BookItemDescription(description = "Página atual: ", descriptionValue = book.currentPage.toString())
        BookItemDescription(description = "Página Inicial: ", descriptionValue = book.initialPage.toString())
    }
}

@Composable
fun BookItemDescription(description: String, descriptionValue: String) {
    Row {
       Text(text = description)
       Text(text = descriptionValue)
    }
}