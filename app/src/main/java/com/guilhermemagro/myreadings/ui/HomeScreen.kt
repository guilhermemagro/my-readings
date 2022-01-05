package com.guilhermemagro.myreadings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guilhermemagro.myreadings.R
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords

@Composable
fun HomeScreen(
    booksAndRecords: List<BookAndRecords>? = null,
    onBookRegistration: (Book) -> Unit,
    onBookCardClick: (Book) -> Unit
) {
    val scrollState = rememberLazyListState()

    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 8.dp),
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            booksAndRecords?.takeIf { it.isNotEmpty() }?.let {
                items(booksAndRecords) { bookAndRecords ->
                    BookCard(book = bookAndRecords.book, onBookCardClick = onBookCardClick)
                }
            } ?: run {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Filled.AddCircle, null)
                        Text(stringResource(R.string.home_screen_empty_books))
                    }
                }
            }
        }

        BookRegistrationScreen(onBookRegistration = onBookRegistration)
    }
}

@Preview
@Composable
fun BodyContentPreview() {
    HomeScreen(
        onBookRegistration = {},
        onBookCardClick = {}
    )
}