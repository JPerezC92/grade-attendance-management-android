package com.example.gradeattendancemanagement.auth.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.gradeattendancemanagement.auth.local.LocalAuth
import com.example.gradeattendancemanagement.auth.repositories.LaravelLoginRepository
import com.example.gradeattendancemanagement.auth.types.Credentials
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch
import com.example.gradeattendancemanagement.miscellaneous.local.LocalContext
import com.example.gradeattendancemanagement.miscellaneous.types.UseLoadingResult
import java.util.*
import kotlin.concurrent.schedule


@Composable
fun SendLoginRequest(loading: UseLoadingResult, credentials: Credentials) {
    val localAuth = LocalAuth.current
    val localContext = LocalContext.current

    val fetchLogin = useFetch(repository = LaravelLoginRepository(credentials))

    LaunchedEffect(fetchLogin.data) {
        if (fetchLogin.data?.success === true) {
            localAuth.setToken(fetchLogin.data.payload)

            loading.finishLoading()
        } else {
            Timer().schedule(3000) {
                loading.finishLoading()
            }
        }

    }


    if (fetchLogin.error is String) {
        Toast.makeText(localContext, fetchLogin.error, Toast.LENGTH_SHORT).show()
    }
}