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
    val navToCourseContent =
        fun(courseId: String) { routerAppController.navigate(CourseContentScreen.create(courseId)) }

    val navToCourseRecord =
        fun(courseRecordId: String) {
            routerAppController.navigate(
                CourseRecordScreen.create(
                    courseRecordId
                )
            )
        }

    return UseRouterContextResult(
        routerAppController = routerAppController,
        navToRegister = navToRegister,
        navToLogin = navToLogin,
        navToCourses = navToCourses,
        navToCourseContent = navToCourseContent,
        navToCourseRecord = navToCourseRecord
    )
}