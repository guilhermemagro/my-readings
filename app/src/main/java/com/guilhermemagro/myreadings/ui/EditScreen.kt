package com.guilhermemagro.myreadings.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.guilhermemagro.myreadings.data.entities.BookAndRecords

@Composable
fun EditScreen(
    bookAndRecords: BookAndRecords? = null,
    numberTest: Int
) {
    bookAndRecords?.let {
        Text(it.book.title)
    } ?: run {
        Text("Empty book!")
    }
    Text(numberTest.toString())
}