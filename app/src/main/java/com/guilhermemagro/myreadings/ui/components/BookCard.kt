package com.guilhermemagro.myreadings.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guilhermemagro.myreadings.R
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.data.entities.Record
import java.util.*

@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    bookAndRecords: BookAndRecords,
    onBookCardClick: (Book) -> Unit = {},
    onIncreaseCurrentPageClick: (Int) -> Unit = {},
    onDecreaseCurrentPageClick: (Int) -> Unit = {}
) {
    val book = bookAndRecords.book
    val records = bookAndRecords.records

    var progress = book.currentPage.toFloat() / book.totalPages.toFloat()
    if (progress > 1.0) progress = 1.0F

    val progressColor = if (progress == 1.0F) Color.Green else MaterialTheme.colors.primary

    Card(
        modifier = modifier,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = book.title.uppercase(Locale.getDefault()),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = { onBookCardClick(book) }
                    ) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Editar"
                        )
                    }
                    UpdatePageComponent(
                        currentPage = book.currentPage,
                        upperLimit = book.totalPages,
                        onIncreasePageClick = { onIncreaseCurrentPageClick(book.id) },
                        onDecreasePageClick = { onDecreaseCurrentPageClick(book.id) }
                    )
                    Text(
                        text =
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append(book.currentPage.toString())
                            }
                            append(stringResource(R.string.book_card_pages_separation))
                            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append(book.totalPages.toString())
                            }
                            append(stringResource(R.string.book_card_pages_end))
                        }
                    )
                }
            }
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth(),
                color = progressColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookCardPreview() {
    BookCard(
        bookAndRecords = BookAndRecords(
            book = Book(
                title = "O Senhor dos Anéis",
                totalPages = 300,
                initialCurrentPage = 100,
                registrationDate = "2022-02-12"
            ),
            records = listOf(
                Record(
                    bookId = 1,
                    date = "2022-02-19",
                    pagesRead = 10
                )
            )
        )
    )
}