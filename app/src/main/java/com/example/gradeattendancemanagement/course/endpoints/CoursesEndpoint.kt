package com.example.gradeattendancemanagement.course.endpoints

import com.example.gradeattendancemanagement.course.types.Course
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface CoursesEndpoint {

    @Headers("Accept: application/json")
    @GET("course")
    suspend fun call(@Header("Authorization") token: String): SuccessfulResponse<List<Course>>
}