package com.example.gradeattendancemanagement.auth.types


typealias SetUser = (newUser: User) -> Unit

data class UseAuthContextResult(
    val token: String?,
    val user: User?,
    val setToken: (newToken: String) -> Unit,
    val setUser: SetUser
)