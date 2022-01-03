package com.guilhermemagro.myreadings.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.ui.EditScreen
import com.guilhermemagro.myreadings.ui.HomeScreen
import com.guilhermemagro.myreadings.viewmodels.EditViewModel
import com.guilhermemagro.myreadings.viewmodels.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
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
        composable(
            route = Screen.EditScreen.routeWithArgument,
            arguments = listOf(
                navArgument(Screen.EditScreen.argument0) { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            val bookId = navBackStackEntry.arguments?.getInt(Screen.EditScreen.argument0) ?: return@composable
            // TODO - Fix it
            val editViewModel: EditViewModel = hiltViewModel()
            var bookAndRecords: BookAndRecords? = null
            LaunchedEffect(true) {
                bookAndRecords = editViewModel.getBookAndRecordsByBookId(bookId)
            }
            EditScreen(bookAndRecords)
        }
    }
}