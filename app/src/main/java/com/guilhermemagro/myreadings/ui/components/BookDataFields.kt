package com.guilhermemagro.myreadings.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.guilhermemagro.myreadings.R

@Composable
fun BookDataFields(
    modifier: Modifier = Modifier,
    titleValue: String = "",
    currentPageValue: String = "",
    totalPagesValue: String = "",
    titleErrorMessage: String = "",
    currentPageErrorMessage: String = "",
    totalPagesErrorMessage: String = "",
    onTitleValueChange: (String) -> Unit = {},
    onCurrentPageValueChange: (String) -> Unit = {},
    onTotalPagesValueChange: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        CommonTextField(
            value = titleValue,
            onValueChange = onTitleValueChange,
            modifier = Modifier.fillMaxWidth(),
            errorMessage = titleErrorMessage,
            label = stringResource(R.string.book_data_fields_title),
            singleLine = true
        )
        CommonTextField(
            value = currentPageValue,
            onValueChange = onCurrentPageValueChange,
            modifier = Modifier.fillMaxWidth(),
            errorMessage = currentPageErrorMessage,
            label = stringResource(R.string.book_data_fields_current_page),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        CommonTextField(
            value = totalPagesValue,
            onValueChange = onTotalPagesValueChange,
            modifier = Modifier.fillMaxWidth(),
            errorMessage = totalPagesErrorMessage,
            label = stringResource(R.string.book_data_fields_total_pages),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
    }
}