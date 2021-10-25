package com.example.gradeattendancemanagement.auth.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.miscellaneous.local.LocalRouter

@Composable
fun IfUserAuthenticatedGotoApp(content: @Composable() () -> Unit) {
    val authContext = LocalAuth.current
    val router = LocalRouter.current

    LaunchedEffect(authContext.user) {
        if (authContext.user != null) {
            router.navToCourses()
        }
    }

    if (authContext.user != null) {
        router.navToCourses()
    } else {
        content()
    }
}