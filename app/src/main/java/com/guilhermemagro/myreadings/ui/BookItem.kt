package com.guilhermemagro.myreadings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guilhermemagro.myreadings.R
import com.guilhermemagro.myreadings.data.entities.Book

@Composable
fun BookItem(book: Book) {
    var progress = book.currentPage.toFloat() / book.totalPages.toFloat()
    if (progress > 1.0) progress = 1.0F

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = book.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text =
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                            append(book.currentPage.toString())
                        }
                        append(stringResource(R.string.home_screen_pages_separation))
                        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                            append(book.totalPages.toString())
                        }
                        append(stringResource(R.string.home_screen_pages_end))
                    }
            )
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                progress = progress
            )
        }
    }
}
