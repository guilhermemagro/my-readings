package com.guilhermemagro.myreadings.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.guilhermemagro.myreadings.ui.theme.MyReadingsTheme
import com.guilhermemagro.myreadings.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val homeViewModel: HomeViewModel by viewModels()
            val booksAndRecords by homeViewModel.booksAndRecords.observeAsState()
            MyReadingsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "MyReadings")
                                }
                            )
                        }
                    ) {
                        HomeScreen(
                            booksAndRecords = booksAndRecords,
                            onBookRegistration = homeViewModel::insertBook
                        )
                    }
                }
            }
        }
    }
}
