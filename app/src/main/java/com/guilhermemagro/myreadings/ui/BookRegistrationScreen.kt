package com.guilhermemagro.myreadings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.guilhermemagro.myreadings.R
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.utils.filterNumbers

@Composable
fun BookRegistrationScreen(
    onBookRegistration: (Book) -> Unit
) {
    var titleTextState by remember { mutableStateOf("") }
    var totalPagesTextState by remember { mutableStateOf("") }
    var currentPageTextState by remember { mutableStateOf("") }

    val allFieldsFilled = titleTextState != "" &&
        totalPagesTextState != "" &&
        currentPageTextState != ""

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = titleTextState,
            onValueChange = { titleTextState = it },
            label = { Text(stringResource(R.string.book_registration_title)) }
        )
        TextField(
            value = currentPageTextState,
            onValueChange = { currentPageTextState = it.filterNumbers() },
            label = { Text(stringResource(R.string.book_registration_current_page)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = totalPagesTextState,
            onValueChange = { totalPagesTextState = it.filterNumbers() },
            label = { Text(stringResource(R.string.book_registration_total_pages)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                onBookRegistration(
                    Book(
                        title = titleTextState,
                        totalPages = totalPagesTextState.toInt(),
                        currentPage = currentPageTextState.toInt()
                    )
                )
                titleTextState = ""
                totalPagesTextState = ""
                currentPageTextState = ""
            },
            enabled = allFieldsFilled
        ) {
            Text(stringResource(R.string.book_registration_register))
        }
    }


}
