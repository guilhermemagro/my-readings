package com.guilhermemagro.myreadings.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
    bookViewModel: BookViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
    val numberOfBooks = 2

    val book1 = Book(
        id = 1,
        title = "Livro 1",
        goal = 20,
        totalPages = 300,
        currentPage = 170,
        initialPage = 1
    )
    val book2 = Book(
        id = 2,
        title = "Livro 2",
        goal = 20,
        totalPages = 350,
        currentPage = 200,
        initialPage = 1
    )

    LazyColumn(state = scrollState) {
        item {
            BookItem(book = book1)
            Spacer(modifier = Modifier.size(20.dp))
            BookItem(book = book2)
        }
    }
}

@Preview
@Composable
fun BodyContentPreview() {
    HomeScreen()
}