package com.guilhermemagro.myreadings.navigation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("Home")
    object EditScreen: Screen("Edit") {
        const val routeWithArgument: String = "Edit/{bookId}"
        const val argument0: String = "bookId"
    }
    object RegistrationScreen: Screen("Registration")
}
