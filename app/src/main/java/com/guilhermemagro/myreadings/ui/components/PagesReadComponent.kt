package com.guilhermemagro.myreadings.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.guilhermemagro.myreadings.data.wrappers.DateWrapper

@Composable
fun PagesReadComponent(
    modifier: Modifier = Modifier,
    date: DateWrapper,
    pagesRead: Int = 0
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = date.getDay(),
            style = MaterialTheme.typography.caption
        )
        Text(
            text = pagesRead.toString()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PagesReadComponentPreview() {
    PagesReadComponent(date = DateWrapper.from("2022-03-13"))
}