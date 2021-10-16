package com.example.gradeattendancemanagement.auth.endpoints

import com.example.gradeattendancemanagement.auth.types.Register
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterEndpoint {
    @POST("auth/register")
    suspend fun call(@Body register: Register): SuccessfulResponse<String>
}