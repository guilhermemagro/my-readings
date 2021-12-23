package com.guilhermemagro.myreadings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.viewmodels.BookViewModel

@Composable
fun HomeScreen(
    onBookRegistration: (Book) -> Unit
) {
    val scrollState = rememberLazyListState()

    val book1 = Book(
        id = 1,
        title = "Livro 1",
        totalPages = 300,
        currentPage = 170
    )
    val book2 = Book(
        id = 2,
        title = "Livro 2",
        totalPages = 350,
        currentPage = 200
    )

    val books = listOf(book1, book2)

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        LazyColumn(
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(books) { book ->
                BookItem(book)
            }
        }

        BookRegistrationScreen(onBookRegistration = onBookRegistration)
    }
}

@Preview
@Composable
fun BodyContentPreview() {
    HomeScreen(
        onBookRegistration = {}
    )
}