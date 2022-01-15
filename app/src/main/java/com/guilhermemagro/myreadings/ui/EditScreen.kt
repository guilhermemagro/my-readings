package com.guilhermemagro.myreadings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guilhermemagro.myreadings.R
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.utils.filterNumbers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun EditScreen(
    scaffoldState: ScaffoldState,
    appCoroutineScope: CoroutineScope,
    onReturnButtonClick: () -> Unit,
    bookAndRecords: BookAndRecords? = null,
    onDeleteBook: (Book) -> Unit,
    onUpdateBook: (Book) -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.edit_screen_title))
                },
                navigationIcon = {
                    IconButton(onClick = onReturnButtonClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.return_arrow)
                        )
                    }
                }
            )
        }
    ) {
        EditScreenContent(
            scaffoldState = scaffoldState,
            appCoroutineScope = appCoroutineScope,
            bookAndRecords = bookAndRecords,
            onDeleteBook = onDeleteBook,
            onUpdateBook = onUpdateBook
        )
    }
}

@Composable
fun EditScreenContent(
    scaffoldState: ScaffoldState,
    appCoroutineScope: CoroutineScope,
    bookAndRecords: BookAndRecords? = null,
    onDeleteBook: (Book) -> Unit,
    onUpdateBook: (Book) -> Unit
) {
    bookAndRecords?.let {
        val book = bookAndRecords.book.copy()
        var titleTextState by remember { mutableStateOf(book.title) }
        var totalPagesTextState by remember { mutableStateOf(book.totalPages.toString()) }
        var currentPageTextState by remember { mutableStateOf(book.currentPage.toString()) }
        val context = LocalContext.current

        val allFieldsFilled = titleTextState != "" &&
                totalPagesTextState != "" &&
                currentPageTextState != ""

        val padding = 16.dp
        Column(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.spacedBy(padding)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = titleTextState,
                onValueChange = { titleTextState = it },
                label = { Text(stringResource(R.string.edit_screen_title)) },
                maxLines = 1
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = currentPageTextState,
                onValueChange = { currentPageTextState = it.filterNumbers() },
                label = { Text(stringResource(R.string.edit_screen_current_page)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = totalPagesTextState,
                onValueChange = { totalPagesTextState = it.filterNumbers() },
                label = { Text(stringResource(R.string.edit_screen_total_pages)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        onDeleteBook(book)
                        appCoroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                context.getString(R.string.edit_screen_delete_snackbar, book.title)
                            )
                        }
                    },
                    modifier = Modifier.weight(1f, true),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFEC532F),
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = stringResource(R.string.edit_screen_delete_icon),
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                    Text(stringResource(R.string.edit_screen_delete))
                }
                Spacer(modifier = Modifier.size(padding))
                Button(
                    onClick = {
                        onUpdateBook(
                            Book(
                                id = book.id,
                                title = titleTextState,
                                totalPages = totalPagesTextState.toInt(),
                                currentPage = currentPageTextState.toInt()
                            )
                        )
                        appCoroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                context.getString(R.string.edit_screen_edit_snackbar, book.title)
                            )
                        }
                    },
                    modifier = Modifier.weight(1f, true),
                    enabled = allFieldsFilled,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF78D865),
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = stringResource(R.string.edit_screen_edit_icon),
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                    Text(stringResource(R.string.edit_screen_edit))
                }
            }
        }
    } ?: run {
        Text(text = "Something went wrong! Try again later! =/")
    }
}

@Preview(showBackground = true)
@Composable
fun EditScreenPreview() {
    EditScreenContent(
        scaffoldState = rememberScaffoldState(),
        appCoroutineScope = rememberCoroutineScope(),
        bookAndRecords = BookAndRecords(
            book = Book(
                title = "Título livro",
                totalPages = 300,
                currentPage = 100
            ),
            records = listOf()
        ),
        onDeleteBook = {},
        onUpdateBook = {}
    )
}