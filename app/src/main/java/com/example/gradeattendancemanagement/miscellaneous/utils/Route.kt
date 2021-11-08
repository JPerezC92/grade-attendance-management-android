package com.example.gradeattendancemanagement.miscellaneous.utils

sealed class Route(val route: String) {
    object SplashScreen : Route("splashScreen")
    object LoginScreen : Route("loginScreen")
    object RegisterScreen : Route("registerScreen")
    object CoursesScreen : Route("coursesScreen")
    object CourseContentScreen : Route("coursesScreen/{courseId}") {
        fun create(courseId: String) = "coursesScreen/$courseId"
    }

    object CourseRecordScreen : Route("courseRecordScreen/{courseRecordId}/{componentView}") {
        fun create(courseRecordId: String, componentView: String) =
            "courseRecordScreen/$courseRecordId/$componentView"
    }

}