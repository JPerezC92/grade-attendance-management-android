package com.example.gradeattendancemanagement.miscellaneous.context

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.gradeattendancemanagement.miscellaneous.types.UseRouterContextResult
import com.example.gradeattendancemanagement.miscellaneous.utils.Route.*


@Composable
fun useRouterContext(): UseRouterContextResult {
    val routerAppController = rememberNavController()

    val navToRegister = { routerAppController.navigate(RegisterScreen.route) }
    val navToLogin = { routerAppController.navigate(LoginScreen.route) }
    val navToCourses = { routerAppController.navigate(CoursesScreen.route) }

    return UseRouterContextResult(
        routerAppController = routerAppController,
        navToRegister = navToRegister,
        navToLogin = navToLogin,
        navToCourses = navToCourses
    )
}