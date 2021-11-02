package com.example.gradeattendancemanagement.miscellaneous.context

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.gradeattendancemanagement.miscellaneous.types.UseRouterContextResult
import com.example.gradeattendancemanagement.miscellaneous.utils.Route.*


@Composable
fun useRouterContext(): UseRouterContextResult {

    val currentCourseRecordId = remember {
        mutableStateOf<Int?>(null)
    }

    val setCurrentCourseRecordId = fun(courseRecordId: Int) {
        currentCourseRecordId.value = courseRecordId
    }

    val routerAppController = rememberNavController()

    val navToRegister = { routerAppController.navigate(RegisterScreen.route) }
    val navToLogin = { routerAppController.navigate(LoginScreen.route) }
    val navToCourses = { routerAppController.navigate(CoursesScreen.route) }
    val navToCourseContent =
        fun(courseId: String) { routerAppController.navigate(CourseContentScreen.create(courseId)) }

    val navToCourseRecordGrade =
        fun() {
            routerAppController.navigate(
                CourseRecordScreen.create(
                    currentCourseRecordId.value!!.toString(), "GRADE"
                )
            )
        }

    val navToCourseRecordAttendance =
        fun() {
            routerAppController.navigate(
                CourseRecordScreen.create(
                    currentCourseRecordId.value!!.toString(), "ATTENDANCE"
                )
            )
        }

    val title = remember { mutableStateOf("") }

    val setTitle = fun(stitle: String) {
        title.value = stitle
    }



    return UseRouterContextResult(
        routerAppController = routerAppController,
        navToRegister = navToRegister,
        navToLogin = navToLogin,
        navToCourses = navToCourses,
        navToCourseContent = navToCourseContent,
        navToCourseRecordGrade = navToCourseRecordGrade,
        navToCourseRecordAttendance = navToCourseRecordAttendance,
        navTitle = title.value,
        setNavTitle = setTitle,
        currentCourseRecordId = currentCourseRecordId.value,
        setCurrentCourseRecordId = setCurrentCourseRecordId
    )
}