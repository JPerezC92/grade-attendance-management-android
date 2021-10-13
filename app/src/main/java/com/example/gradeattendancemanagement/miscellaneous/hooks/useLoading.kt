package com.example.gradeattendancemanagement.miscellaneous.hooks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.gradeattendancemanagement.miscellaneous.types.UseLoadingResult


@Composable
fun useLoading(): UseLoadingResult {
    val isLoading = remember { mutableStateOf(false) }

    val startLoading = { isLoading.value = true }
    val finishLoading = { isLoading.value = false }


    return UseLoadingResult(
        isLoading = isLoading.value,
        startLoading = startLoading,
        finishLoading = finishLoading
    )
}
