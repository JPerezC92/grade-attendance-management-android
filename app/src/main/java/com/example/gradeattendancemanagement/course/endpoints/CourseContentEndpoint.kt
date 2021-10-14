package com.example.gradeattendancemanagement.course.endpoints

import com.example.gradeattendancemanagement.course.types.CourseContent
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface CourseContentEndpoint {

    @Headers("Accept: application/json")
    @GET("course/{courseId}")
    suspend fun call(
        @Header("Authorization") token: String,
        @Path("courseId") courseId: String,
    ): SuccessfulResponse<CourseContent>

}