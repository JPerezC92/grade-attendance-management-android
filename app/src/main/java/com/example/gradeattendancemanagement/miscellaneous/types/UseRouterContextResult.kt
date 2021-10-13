package com.example.gradeattendancemanagement.miscellaneous.types

import androidx.navigation.NavHostController

data class UseRouterContextResult(
    val routerAppController: NavHostController,
    val navToRegister: () -> Unit,
    val navToLogin: () -> Unit,
    val navToCourses: () -> Unit
)
