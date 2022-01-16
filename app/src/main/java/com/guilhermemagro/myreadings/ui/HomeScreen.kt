package com.guilhermemagro.myreadings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guilhermemagro.myreadings.R
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.ui.custom.BookCard
import com.guilhermemagro.myreadings.utils.DEFAULT_FAB_SIZE

@Composable
fun HomeScreen(
    scaffoldState: ScaffoldState,
    booksAndRecords: List<BookAndRecords>? = null,
    onRegistrationClick: () -> Unit,
    onBookCardClick: (Book) -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.home_screen_title))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onRegistrationClick) {
                Icon(Icons.Filled.Add, stringResource(id = R.string.home_screen_fab))
            }
        }
    ) {
        HomeScreenContent(
            scaffoldState = scaffoldState,
            booksAndRecords = booksAndRecords,
            onBookCardClick = onBookCardClick
        )
    }
}

@Composable
fun HomeScreenContent(
    scaffoldState: ScaffoldState,
    booksAndRecords: List<BookAndRecords>? = null,
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
                item {
                    Spacer(modifier = Modifier.height(DEFAULT_FAB_SIZE.dp))
                }
            } ?: run {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Filled.Warning, null)
                        Text(stringResource(R.string.home_screen_empty_books))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BodyContentPreview() {
    HomeScreenContent(
        scaffoldState = rememberScaffoldState(),
        onBookCardClick = {}
    )
}