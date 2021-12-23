package com.guilhermemagro.myreadings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.guilhermemagro.myreadings.R
import com.guilhermemagro.myreadings.data.entities.Book

@Composable
fun BookRegistrationScreen(
    onBookRegistration: (Book) -> Unit
) {
    val titleTextState = remember { mutableStateOf(TextFieldValue())}
    val totalPagesTextState = remember { mutableStateOf(TextFieldValue())}
    val currentPageTextState = remember { mutableStateOf(TextFieldValue())}

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = titleTextState.value,
            onValueChange = { titleTextState.value = it },
            label = { Text(stringResource(R.string.book_registration_title)) }
        )
        TextField(
            value = totalPagesTextState.value,
            onValueChange = { totalPagesTextState.value = it },
            label = { Text(stringResource(R.string.book_registration_total_pages)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = currentPageTextState.value,
            onValueChange = { currentPageTextState.value = it },
            label = { Text(stringResource(R.string.book_registration_current_page)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        if (titleTextState.value.text != "" &&
            totalPagesTextState.value.text != "" &&
            currentPageTextState.value.text != ""
        ) {
            Button(onClick = {
                onBookRegistration(
                    Book(
                        title = titleTextState.value.text,
                        totalPages = totalPagesTextState.value.text.toInt(),
                        currentPage = currentPageTextState.value.text.toInt()
                    )
                )}
            ) {
                Text(stringResource(R.string.book_registration_register))
            }
        }
    }
}
