package com.example.gradeattendancemanagement.auth.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.auth.repositories.LaravelRegisterRepository
import com.example.gradeattendancemanagement.auth.types.Register
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch
import com.example.gradeattendancemanagement.miscellaneous.types.UseLoadingResult

@Composable
fun SendRegisterRequest(register: Register, loading: UseLoadingResult) {

    val localAuth = LocalAuth.current
//    val localContext = LocalContext.current

    val fetchLogin = useFetch(repository = LaravelRegisterRepository(register))

    LaunchedEffect(fetchLogin.data) {
        if (fetchLogin.data?.success === true) {
            localAuth.setToken(fetchLogin.data.payload)

            loading.finishLoading()
        }

        if (fetchLogin.error is String) {
            loading.finishLoading()
        }
    }
}