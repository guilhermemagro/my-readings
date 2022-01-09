package com.guilhermemagro.myreadings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guilhermemagro.myreadings.R
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.utils.filterNumbers

@Composable
fun RegistrationScreen(
    onBookRegistration: (Book) -> Unit
) {
    var titleTextState by remember { mutableStateOf("") }
    var totalPagesTextState by remember { mutableStateOf("") }
    var currentPageTextState by remember { mutableStateOf("") }

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
            label = { Text(stringResource(R.string.registration_screen_title)) },
            maxLines = 1
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = currentPageTextState,
            onValueChange = { currentPageTextState = it.filterNumbers() },
            label = { Text(stringResource(R.string.registration_screen_current_page)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 1
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = totalPagesTextState,
            onValueChange = { totalPagesTextState = it.filterNumbers() },
            label = { Text(stringResource(R.string.registration_screen_total_pages)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 1
        )
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
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
            enabled = allFieldsFilled,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF78D865),
                contentColor = Color.White
            )
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = stringResource(R.string.registration_screen_register_icon),
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(stringResource(R.string.registration_screen_register))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(onBookRegistration = {})
}
