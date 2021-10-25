package com.example.gradeattendancemanagement.auth.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.gradeattendancemanagement.auth.repositories.LaravelGetUserRepository
import com.example.gradeattendancemanagement.auth.types.SetUser
import com.example.gradeattendancemanagement.miscellaneous.hooks.useFetch
import com.example.gradeattendancemanagement.miscellaneous.local.LocalRouter

@Composable
fun SendGetUserRequest(token: String, setUser: SetUser) {
    val router = LocalRouter.current
    val fetchGetUser = useFetch(repository = LaravelGetUserRepository(token))

    LaunchedEffect(fetchGetUser.data) {
        if (fetchGetUser.data?.success === true) {
            setUser(fetchGetUser.data.payload)
//            router.navToCourses()
        }
    }
}