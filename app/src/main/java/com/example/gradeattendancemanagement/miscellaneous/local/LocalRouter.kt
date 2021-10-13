package com.example.gradeattendancemanagement.miscellaneous.local

import androidx.compose.runtime.compositionLocalOf
import com.example.gradeattendancemanagement.miscellaneous.types.UseRouterContextResult

val LocalRouter = compositionLocalOf(defaultFactory = { null as UseRouterContextResult })

