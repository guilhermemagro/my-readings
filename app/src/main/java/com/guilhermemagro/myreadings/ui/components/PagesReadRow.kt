package com.guilhermemagro.myreadings.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.data.wrappers.DateWrapper
import com.guilhermemagro.myreadings.utils.ZERO_PAGES_READ

@Composable
fun PagesReadRow(
    modifier: Modifier = Modifier,
    bookAndRecords: BookAndRecords,
    onIncreaseCurrentPageClick: (Int) -> Unit = {},
    onDecreaseCurrentPageClick: (Int) -> Unit = {}
) {
    val recordsFromLastDays = bookAndRecords.getRecordsFromYesterdayToTheLastSevenDays()
    val todayRecord = bookAndRecords.getTodayRecordIfExists()
    val todayPagesRead = todayRecord?.pagesRead ?: ZERO_PAGES_READ
    val book = bookAndRecords.book
    val currentPagesReadLimit = book.totalPages - book.currentPage + todayPagesRead

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (record in recordsFromLastDays) {
            PagesReadComponent(
               date = record.date,
               pagesRead = record.pagesRead
            )
        }
        UpdatePageComponent(
            currentPage = todayPagesRead,
            upperLimit = currentPagesReadLimit,
            onIncreasePageClick = { onIncreaseCurrentPageClick(book.id) },
            onDecreasePageClick = { onDecreaseCurrentPageClick(book.id) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PagesReadRowPreview() {
    PagesReadRow(bookAndRecords = BookAndRecords(
        book = Book(
            title = "Test",
            totalPages = 50,
            initialCurrentPage = 1,
            registrationDate = DateWrapper.from("2022-03-13")
        ),
        records = listOf()
    ))
}