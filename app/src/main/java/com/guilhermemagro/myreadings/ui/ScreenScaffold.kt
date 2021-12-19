package com.guilhermemagro.myreadings.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScreenScaffold() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "MyReadings")
                }
            )
        }
    ) { innerPadding ->
        HomeScreen(
            modifier = Modifier.padding(innerPadding)
        )
    }
}
