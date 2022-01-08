package com.guilhermemagro.myreadings.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.guilhermemagro.myreadings.R
import com.guilhermemagro.myreadings.ui.EditScreen
import com.guilhermemagro.myreadings.ui.HomeScreen
import com.guilhermemagro.myreadings.utils.assistedViewModel
import com.guilhermemagro.myreadings.viewmodels.EditViewModel
import com.guilhermemagro.myreadings.viewmodels.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(
    scaffoldState: ScaffoldState
) {
    val navController = rememberNavController()
    val appScope = rememberCoroutineScope()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val booksAndRecords by homeViewModel.booksAndRecords.observeAsState()
            HomeScreen(
                booksAndRecords = booksAndRecords,
                onBookRegistration = homeViewModel::insertBook,
                onBookCardClick = { selectedBook ->
                    navController.navigate(Screen.EditScreen.route(selectedBook.id))
                }
            )
        }
        composable(
            route = Screen.EditScreen.route,
            arguments = listOf(
                navArgument(Screen.EditScreen.argument0) { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            val bookId = navBackStackEntry.arguments?.getInt(Screen.EditScreen.argument0) ?: return@composable
            val editViewModel: EditViewModel = assistedViewModel {
                EditViewModel.provideFactory(editViewModelFactory(), bookId)
            }
            val selectedBookAndRecords by editViewModel.bookAndRecords.observeAsState()
            val context = LocalContext.current
            EditScreen(
                bookAndRecords = selectedBookAndRecords,
                onDeleteBook = { book ->
                    editViewModel.deleteBook(book)
                    navController.navigateUp()
                    appScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            context.getString(R.string.edit_screen_delete_snackbar, book.title)
                        )
                    }
                },
                onUpdateBook = { book ->
                    editViewModel.updateBook(book)
                    navController.navigateUp()
                }
            )
        }
    }
}