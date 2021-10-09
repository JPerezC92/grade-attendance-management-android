package com.example.gradeattendancemanagement

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

//data class Elevations(val card: Dp = 0.dp, val default: Dp = 0.dp)

// Define a CompositionLocal global object with a default
// This instance can be accessed by all composables in the app
val LocalElevations = compositionLocalOf<Int>(defaultFactory = { 2 })


@Composable
fun useRouter() {

    return

}