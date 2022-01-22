package com.guilhermemagro.myreadings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guilhermemagro.myreadings.R
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.ui.components.BookDataFields
import com.guilhermemagro.myreadings.utils.BookValidator
import com.guilhermemagro.myreadings.utils.filterNumbers
import com.guilhermemagro.myreadings.utils.trimStartAndEnd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(
    scaffoldState: ScaffoldState,
    onReturnButtonClick: () -> Unit,
    appCoroutineScope: CoroutineScope,
    onBookRegistration: (Book) -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.registration_screen_title))
                },
                navigationIcon = {
                    IconButton(onClick = onReturnButtonClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.return_arrow)
                        )
                    }
                }
            )
        },
    ) {
        RegistrationScreenContent(
            scaffoldState = scaffoldState,
            appCoroutineScope = appCoroutineScope,
            onBookRegistration = onBookRegistration
        )
    }
}

@Composable
fun RegistrationScreenContent(
    scaffoldState: ScaffoldState,
    appCoroutineScope: CoroutineScope,
    onBookRegistration: (Book) -> Unit
) {
    var titleTextState by remember { mutableStateOf("") }
    var currentPageTextState by remember { mutableStateOf("") }
    var totalPagesTextState by remember { mutableStateOf("") }

    var currentPageErrorMessage by remember { mutableStateOf("") }
    var totalPagesErrorMessage by remember { mutableStateOf("") }

    var hasError by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val allFieldsFilled = titleTextState != "" &&
            currentPageTextState != "" &&
            totalPagesTextState != ""

    fun clearErrorMessages() {
        currentPageErrorMessage = ""
        totalPagesErrorMessage = ""
    }

    fun validateFields() {
        if (BookValidator.isTotalPagesGreaterOrEqualsCurrentPage(
                totalPages = totalPagesTextState.toInt(),
                currentPage = currentPageTextState.toInt()
            )) {
            hasError = false
        } else {
            currentPageErrorMessage = context.getString(R.string.registration_screen_current_page_error)
            totalPagesErrorMessage = context.getString(R.string.registration_screen_total_pages_error)
            hasError = true
        }
    }

    fun registerButtonAction() {
        validateFields()
        if (hasError) return
        val title = titleTextState.trimStartAndEnd()
        onBookRegistration(
            Book(
                title = title,
                totalPages = totalPagesTextState.toInt(),
                currentPage = currentPageTextState.toInt()
            )
        )
        appCoroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                context.getString(R.string.registration_screen_registration_snackbar, title)
            )
        }
        titleTextState = ""
        totalPagesTextState = ""
        currentPageTextState = ""
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        BookDataFields(
            titleValue = titleTextState,
            currentPageValue = currentPageTextState,
            totalPagesValue = totalPagesTextState,
            currentPageErrorMessage = currentPageErrorMessage,
            totalPagesErrorMessage = totalPagesErrorMessage,
            onTitleValueChange = {
                titleTextState = it
                clearErrorMessages()
            },
            onCurrentPageValueChange = {
                currentPageTextState = it.filterNumbers()
                clearErrorMessages()
            },
            onTotalPagesValueChange = {
                totalPagesTextState = it.filterNumbers()
                clearErrorMessages()
            }
        )
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = ::registerButtonAction,
            enabled = allFieldsFilled,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF78D865),
                contentColor = Color.White
            )
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = stringResource(R.string.registration_screen_register_button_icon),
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(stringResource(R.string.registration_screen_register_button))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreenContent(
        scaffoldState = rememberScaffoldState(),
        appCoroutineScope = rememberCoroutineScope(),
        onBookRegistration = {}
    )
}
