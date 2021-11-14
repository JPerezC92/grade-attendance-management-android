package com.example.gradeattendancemanagement.miscellaneous.context

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.gradeattendancemanagement.course.types.Course
import com.example.gradeattendancemanagement.course.types.CourseRecord
import com.example.gradeattendancemanagement.miscellaneous.types.UseRouterContextResult
import com.example.gradeattendancemanagement.miscellaneous.utils.Route.*


@Composable
fun useRouterContext(): UseRouterContextResult {

    val currentCourseRecord = remember {
        mutableStateOf<CourseRecord?>(null)
    }

    val currentCourse = remember {
        mutableStateOf<Course?>(null)
    }

    val setCurrentCourseRecord = fun(courseRecord: CourseRecord) {
        currentCourseRecord.value = courseRecord
    }

    val setCurrentCourse = fun(course: Course) {
        currentCourse.value = course
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
                    currentCourseRecord.value!!.id.toString(), "GRADE"
                )
            )
        }

    val navToCourseRecordAttendance =
        fun() {
            routerAppController.navigate(
                CourseRecordScreen.create(
                    currentCourseRecord.value!!.id.toString(), "ATTENDANCE"
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
        currentCourseRecord = currentCourseRecord.value,
        setCurrentCourseRecord = setCurrentCourseRecord,
        currentCourse = currentCourse.value,
        setCurrentCourse = setCurrentCourse,

        )
}