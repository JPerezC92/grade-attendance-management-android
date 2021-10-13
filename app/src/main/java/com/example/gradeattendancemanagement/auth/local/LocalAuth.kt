package com.example.gradeattendancemanagement.auth.local

import androidx.compose.runtime.compositionLocalOf
import com.example.gradeattendancemanagement.auth.types.UseAuthContextResult

val LocalAuth = compositionLocalOf(defaultFactory = { null as UseAuthContextResult })