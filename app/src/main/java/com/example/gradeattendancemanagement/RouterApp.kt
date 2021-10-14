package com.example.gradeattendancemanagement

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gradeattendancemanagement.miscellaneous.utils.Route.*
import com.example.gradeattendancemanagement.auth.components.LoginScreen
import com.example.gradeattendancemanagement.auth.components.RegisterScreen
import com.example.gradeattendancemanagement.auth.components.SendGetUserRequest
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.course.components.CourseContentScreen
import com.example.gradeattendancemanagement.course.components.CoursesScreen
import com.example.gradeattendancemanagement.courserecord.components.CourseRecordScreen
import com.example.gradeattendancemanagement.miscellaneous.hooks.useLoading
import com.example.gradeattendancemanagement.miscellaneous.local.LocalRouter

@Composable
fun RouterApp() {

    val router = LocalRouter.current
    val authContext = LocalAuth.current
    val getUserRequestLoading = useLoading()

    LaunchedEffect(authContext.token) {
        authContext.token?.let { getUserRequestLoading.startLoading() }
    }

    if (getUserRequestLoading.isLoading) {
        authContext.token?.let { SendGetUserRequest(token = it, setUser = authContext.setUser) }
    }


    NavHost(navController = router.routerAppController, startDestination = LoginScreen.route) {
        composable(LoginScreen.route) {
            LoginScreen()
        }

        composable(RegisterScreen.route) {
            RegisterScreen()
        }

        composable(CoursesScreen.route) {
            CoursesScreen()
        }

        composable(CourseContentScreen.route) {
            var courseId = it.arguments?.getString("courseId")

            CourseContentScreen(courseId = courseId!!)
        }

        composable(CourseRecordScreen.route) {
            var courseRecordId = it.arguments?.getString("courseRecordId")

            CourseRecordScreen(courseRecordId = courseRecordId!!)
        }
    }
}