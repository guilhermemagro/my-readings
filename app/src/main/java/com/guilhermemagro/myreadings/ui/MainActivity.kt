package com.guilhermemagro.myreadings.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guilhermemagro.myreadings.navigation.Screen
import com.guilhermemagro.myreadings.ui.theme.MyReadingsTheme
import com.guilhermemagro.myreadings.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

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
                        NavHost(
                            navController = navController,
                            startDestination = Screen.HomeScreen.route
                        ) {
                            composable(Screen.HomeScreen.route) {
                                val homeViewModel: HomeViewModel = hiltViewModel()
                                val booksAndRecords by homeViewModel.booksAndRecords.observeAsState()

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
    }
}
