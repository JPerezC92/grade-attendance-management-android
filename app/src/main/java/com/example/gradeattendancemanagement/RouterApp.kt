package com.example.gradeattendancemanagement

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gradeattendancemanagement.Route.*

@Composable
fun RouterApp() {
    val routerAppController = rememberNavController()

    NavHost(navController = routerAppController, startDestination = LoginScreen.route) {
        composable(LoginScreen.route) {
            LoginScreen(navToRegister = { routerAppController.navigate(RegisterScreen.route) })
        }

        composable(RegisterScreen.route) {
            RegisterScreen()
        }
    }
}