package com.example.gradeattendancemanagement.auth.endpoints

import com.example.gradeattendancemanagement.auth.types.User
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface GetUserEndpoint {
    @Headers("Accept: application/json")
    @GET("instructor")
    suspend fun call(@Header("Authorization") token: String): SuccessfulResponse<User>
}