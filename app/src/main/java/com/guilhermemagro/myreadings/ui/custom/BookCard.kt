package com.guilhermemagro.myreadings.ui.custom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guilhermemagro.myreadings.R
import com.guilhermemagro.myreadings.data.entities.Book
import java.util.*

@Composable
fun BookCard(book: Book, onBookCardClick: (Book) -> Unit) {
    var progress = book.currentPage.toFloat() / book.totalPages.toFloat()
    if (progress > 1.0) progress = 1.0F

    val progressColor = if (progress == 1.0F) Color.Green else MaterialTheme.colors.primary

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onBookCardClick(book) },
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = book.title.uppercase(Locale.getDefault()),
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
                progress = progress,
                modifier = Modifier.fillMaxWidth(),
                color = progressColor
            )
        }
    }
}
