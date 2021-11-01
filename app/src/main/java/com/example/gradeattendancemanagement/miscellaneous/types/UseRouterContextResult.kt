package com.example.gradeattendancemanagement.miscellaneous.types

import androidx.navigation.NavHostController

data class UseRouterContextResult(
    val routerAppController: NavHostController,
    val navToRegister: () -> Unit,
    val navToLogin: () -> Unit,
    val navToCourses: () -> Unit,
    val navToCourseContent: (courseId: String) -> Unit,
    val navToCourseRecord: (courseRecordId: String) -> Unit,
    val navTitle: String ,
    val setNavTitle: (String) -> Unit
)
