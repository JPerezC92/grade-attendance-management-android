package com.example.gradeattendancemanagement.miscellaneous.local

import androidx.activity.ComponentActivity
import androidx.compose.runtime.compositionLocalOf



val LocalContext = compositionLocalOf(defaultFactory = { null as ComponentActivity })

