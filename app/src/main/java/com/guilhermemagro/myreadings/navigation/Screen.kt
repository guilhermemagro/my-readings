package com.guilhermemagro.myreadings.navigation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home")
    object EditScreen: Screen("edit/{bookId}") {
        fun route(bookId: Int) = "edit/$bookId"
        const val argument0: String = "bookId"
    }
    object RegistrationScreen: Screen("registration")
}
