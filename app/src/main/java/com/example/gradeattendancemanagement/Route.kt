package com.example.gradeattendancemanagement

sealed class Route(val route: String) {
    object LoginScreen : Route("loginScreen")
    object RegisterScreen : Route("registerScreen")
}