package com.guilhermemagro.myreadings.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.guilhermemagro.myreadings.data.entities.BookAndRecords

@Composable
fun EditScreen(
    bookAndRecords: BookAndRecords? = null
) {
    bookAndRecords?.let {
        Text(text = bookAndRecords.toString())
    } ?: run {
        Text("Something went wrong! Please try again later =/")
    }
}