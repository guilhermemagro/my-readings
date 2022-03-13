package com.guilhermemagro.myreadings.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.guilhermemagro.myreadings.R

@Composable
fun UpdatePageComponent(
    modifier: Modifier = Modifier,
    currentPage: Int = 0,
    lowerLimit: Int = 0,
    upperLimit: Int = Int.MAX_VALUE,
    onDecreasePageClick: () -> Unit = {},
    onIncreasePageClick: () -> Unit = {}
) {
    val decreasePageEnabled = currentPage > lowerLimit
    val increasePageEnabled = currentPage < upperLimit

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top
    ) {
        IconButton(
            onClick = onDecreasePageClick,
            enabled = decreasePageEnabled
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_minus),
                contentDescription = stringResource(id = R.string.update_page_decrease_description)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.update_page_today),
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.caption
            )
            Text(
                text = currentPage.toString(),
                fontWeight = FontWeight.Bold
            )
        }
        IconButton(
            onClick = onIncreasePageClick,
            enabled = increasePageEnabled
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = stringResource(id = R.string.update_page_increase_description)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UpdatePageComponentPreview() {
    UpdatePageComponent(
        currentPage = 350
    )
}