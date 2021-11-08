package com.example.gradeattendancemanagement.miscellaneous.types

import androidx.navigation.NavHostController

data class UseRouterContextResult(
    val routerAppController: NavHostController,
    val navToRegister: () -> Unit,
    val navToLogin: () -> Unit,
    val navToCourses: () -> Unit,
    val navToCourseContent: (courseId: String) -> Unit,
    val navToCourseRecordGrade: () -> Unit,
    val navToCourseRecordAttendance: () -> Unit,
    val navTitle: String,
    val setNavTitle: (String) -> Unit,
    val currentCourseRecordId: Int?,
    val setCurrentCourseRecordId: (Int) -> Unit,
)
