package com.example.gradeattendancemanagement.auth.context

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.gradeattendancemanagement.auth.types.UseAuthContextResult
import com.example.gradeattendancemanagement.auth.types.User


@Composable
fun useAuthContext(): UseAuthContextResult {
    val token = remember { mutableStateOf<String?>(null) }

    val user = remember { mutableStateOf<User?>(null) }

    val setToken = fun(newToken: String) { token.value = newToken }

    val setUser = fun(newUser: User) { user.value = newUser }
    val logout = fun() {
        user.value = null
        token.value = null
    }

    return UseAuthContextResult(
        token = token.value,
        user = user.value,
        setToken = setToken,
        setUser = setUser,
        logout = logout
    )
}