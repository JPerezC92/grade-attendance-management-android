package com.example.gradeattendancemanagement.auth.types

import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse

data class UseFetchResult<T>(
    val isLoading: Boolean,
    val data: SuccessfulResponse<T>?,
    val error: String?,
)