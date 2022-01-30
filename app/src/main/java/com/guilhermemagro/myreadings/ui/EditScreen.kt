package com.guilhermemagro.myreadings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guilhermemagro.myreadings.R
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.ui.components.BookDataFields
import com.guilhermemagro.myreadings.utils.BookValidator
import com.guilhermemagro.myreadings.utils.filterNumbers
import com.guilhermemagro.myreadings.utils.trimStartAndEnd
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
        var currentPageTextState by remember { mutableStateOf(book.currentPage.toString()) }
        var totalPagesTextState by remember { mutableStateOf(book.totalPages.toString()) }

        var currentPageErrorMessage by remember { mutableStateOf("") }
        var totalPagesErrorMessage by remember { mutableStateOf("") }

        var hasError by remember { mutableStateOf(false) }

        val context = LocalContext.current

        val allFieldsFilled = titleTextState != "" &&
                totalPagesTextState != "" &&
                currentPageTextState != ""

        fun clearErrorMessages() {
            currentPageErrorMessage = ""
            totalPagesErrorMessage = ""
        }

        fun deleteButtonAction() {
            onDeleteBook(book)
            appCoroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    context.getString(R.string.edit_screen_delete_snackbar, book.title)
                )
            }
        }

        fun validateFields() {
            if (BookValidator.isTotalPagesGreaterOrEqualsCurrentPage(
                    totalPages = totalPagesTextState.toInt(),
                    currentPage = currentPageTextState.toInt()
                )
            ) {
                hasError = false
            } else {
                currentPageErrorMessage = context.getString(R.string.edit_screen_current_page_error)
                totalPagesErrorMessage = context.getString(R.string.edit_screen_total_pages_error)
                hasError = true
            }
        }

        fun editButtonAction() {
            validateFields()
            if (hasError) return
            onUpdateBook(
                Book(
                    id = book.id,
                    title = titleTextState.trimStartAndEnd(),
                    totalPages = totalPagesTextState.toInt(),
                    initialCurrentPage = currentPageTextState.toInt()
                )
            )
            appCoroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    context.getString(R.string.edit_screen_edit_snackbar, book.title)
                )
            }
        }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            BookDataFields(
                titleValue = titleTextState,
                currentPageValue = currentPageTextState,
                totalPagesValue = totalPagesTextState,
                currentPageErrorMessage = currentPageErrorMessage,
                totalPagesErrorMessage = totalPagesErrorMessage,
                onTitleValueChange = {
                    titleTextState = it
                    clearErrorMessages()
                },
                onCurrentPageValueChange = {
                    currentPageTextState = it.filterNumbers()
                    clearErrorMessages()
                },
                onTotalPagesValueChange = {
                    totalPagesTextState = it.filterNumbers()
                    clearErrorMessages()
                }
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = ::deleteButtonAction,
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
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    onClick = ::editButtonAction,
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
                initialCurrentPage = 100
            ),
            records = listOf()
        ),
        onDeleteBook = {},
        onUpdateBook = {}
    )
}