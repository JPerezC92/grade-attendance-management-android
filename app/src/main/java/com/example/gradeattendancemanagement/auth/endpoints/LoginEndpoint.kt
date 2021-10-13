package com.example.gradeattendancemanagement.auth.endpoints

import com.example.gradeattendancemanagement.auth.types.Credentials
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import retrofit2.http.*

interface LoginEndpoint {
    @POST("auth/login")
    suspend fun call(@Body credentials: Credentials): SuccessfulResponse<String>
}
