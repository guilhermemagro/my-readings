package com.guilhermemagro.myreadings.navigation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("Home")
    object EditScreen: Screen("Edit")
    object RegistrationScreen: Screen("Registration")
}

// Example: route with argument
//object PosterDetails : NavScreen("PosterDetails") {
//    const val routeWithArgument: String = "PosterDetails/{posterId}"
//    const val argument0: String = "posterId"
//}