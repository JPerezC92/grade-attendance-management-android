package com.example.gradeattendancemanagement.miscellaneous.types

data class UseLoadingResult(
    val isLoading: Boolean,
    val startLoading: () -> Unit,
    val finishLoading: () -> Unit
)
