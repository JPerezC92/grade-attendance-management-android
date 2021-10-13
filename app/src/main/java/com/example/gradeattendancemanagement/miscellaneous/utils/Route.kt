package com.example.gradeattendancemanagement.miscellaneous.utils

sealed class Route(val route: String) {
    object LoginScreen : Route("loginScreen")
    object RegisterScreen : Route("registerScreen")
    object CoursesScreen : Route("coursesScreen")
}