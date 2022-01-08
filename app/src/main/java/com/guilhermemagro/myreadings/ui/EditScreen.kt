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
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.guilhermemagro.myreadings.R
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.utils.filterNumbers

@Composable
fun EditScreen(
    bookAndRecords: BookAndRecords? = null,
    onDeleteBook: (Book) -> Unit,
    onUpdateBook: (Book) -> Unit
) {
    bookAndRecords?.let {
        val book = bookAndRecords.book.copy()
        var titleTextState by remember { mutableStateOf(book.title) }
        var totalPagesTextState by remember { mutableStateOf(book.totalPages.toString()) }
        var currentPageTextState by remember { mutableStateOf(book.currentPage.toString()) }

        val allFieldsFilled = titleTextState != "" &&
                totalPagesTextState != "" &&
                currentPageTextState != ""

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
                    onClick = { onDeleteBook(book) }
                ) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = stringResource(R.string.edit_screen_delete_icon),
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                    Text(stringResource(R.string.edit_screen_delete))
                }
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
                    },
                    enabled = allFieldsFilled
                ) {
                    Text(stringResource(R.string.edit_screen_edit))
                }
            }
        }
    } ?: run {
        Text(text = "Something went wrong! Try again later! =/")
    }
}