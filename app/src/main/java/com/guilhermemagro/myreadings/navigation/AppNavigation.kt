package com.guilhermemagro.myreadings.navigation

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.guilhermemagro.myreadings.ui.EditScreen
import com.guilhermemagro.myreadings.ui.HomeScreen
import com.guilhermemagro.myreadings.ui.RegistrationScreen
import com.guilhermemagro.myreadings.utils.assistedViewModel
import com.guilhermemagro.myreadings.viewmodels.EditViewModel
import com.guilhermemagro.myreadings.viewmodels.HomeViewModel
import com.guilhermemagro.myreadings.viewmodels.RegistrationViewModel

@Composable
fun AppNavigation() {
    val scaffoldState = rememberScaffoldState()
    val appCoroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val booksAndRecords by homeViewModel.booksAndRecords.observeAsState()
            HomeScreen(
                scaffoldState = scaffoldState,
                booksAndRecords = booksAndRecords,
                onRegistrationClick = { navController.navigate(Screen.RegistrationScreen.route) },
                onBookCardClick = { selectedBook ->
                    navController.navigate(Screen.EditScreen.route(selectedBook.id))
                }
            )
        }

        composable(route = Screen.RegistrationScreen.route) {
            val registrationViewModel: RegistrationViewModel = hiltViewModel()
            RegistrationScreen(
                scaffoldState = scaffoldState,
                appCoroutineScope = appCoroutineScope,
                onBookRegistration = { book ->
                    registrationViewModel.insertBook(book)
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = Screen.EditScreen.route,
            arguments = listOf(
                navArgument(Screen.EditScreen.argument0) { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            val bookId = navBackStackEntry.arguments?.getInt(Screen.EditScreen.argument0)
                ?: return@composable
            val editViewModel: EditViewModel = assistedViewModel {
                EditViewModel.provideFactory(editViewModelFactory(), bookId)
            }
            val selectedBookAndRecords by editViewModel.bookAndRecords.observeAsState()
            EditScreen(
                scaffoldState = scaffoldState,
                appCoroutineScope = appCoroutineScope,
                bookAndRecords = selectedBookAndRecords,
                onDeleteBook = { book ->
                    editViewModel.deleteBook(book)
                    navController.navigateUp()
                },
                onUpdateBook = { book ->
                    editViewModel.updateBook(book)
                    navController.navigateUp()
                }
            )
        }
    }
}