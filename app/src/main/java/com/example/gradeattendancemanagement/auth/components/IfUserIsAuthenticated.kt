package com.example.gradeattendancemanagement.auth.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.miscellaneous.local.LocalRouter

@Composable
fun IfUserIsAuthenticated(content: @Composable() () -> Unit) {
    val authContext = LocalAuth.current
    val router = LocalRouter.current

    var showContent = authContext.token != null && authContext.user != null

    LaunchedEffect(authContext.token, authContext.user) {
        showContent = authContext.token != null && authContext.user != null

        if (!showContent) {
            router.navToLogin()
        }
    }

    if (showContent) {
        content()
    } else {
        router.navToLogin()
    }
}