package com.example.gradeattendancemanagement

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.gradeattendancemanagement.auth.context.useAuthContext
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.miscellaneous.context.useRouterContext
import com.example.gradeattendancemanagement.miscellaneous.local.LocalContext
import com.example.gradeattendancemanagement.miscellaneous.local.LocalRouter

@Composable
fun Store(content: @Composable() () -> Unit, context: ComponentActivity) {
    val router = useRouterContext()
    val authContext = useAuthContext()

    CompositionLocalProvider(
        LocalRouter provides router,
        LocalAuth provides authContext,
        LocalContext provides context
    ) {
        content()
    }
}
