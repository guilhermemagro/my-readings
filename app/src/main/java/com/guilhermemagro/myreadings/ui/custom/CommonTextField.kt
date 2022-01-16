package com.guilhermemagro.myreadings.ui.custom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guilhermemagro.myreadings.utils.visibleIf

@Composable
fun CommonTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    errorMessage: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
) {
    val hasError = errorMessage.isNotBlank()

    Column(modifier = modifier) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            isError = hasError,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            maxLines = maxLines
        )
        Text(
            text = errorMessage,
            modifier = Modifier
                .padding(vertical = 2.dp, horizontal = 16.dp)
                .alpha(visibleIf(hasError)),
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommonTextFieldWithErrorPreview() {
    CommonTextField(
        value = "CommonTextField text",
        onValueChange = {},
        label = "Title",
        errorMessage = "Error message"
    )
}

@Preview(showBackground = true)
@Composable
fun CommonTextFieldPreview() {
    CommonTextField(
        value = "CommonTextField text",
        onValueChange = {},
        label = "Title"
    )
}