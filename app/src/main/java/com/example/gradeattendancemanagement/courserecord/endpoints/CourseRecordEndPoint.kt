package com.example.gradeattendancemanagement.courserecord.endpoints

import com.example.gradeattendancemanagement.courserecord.types.CourseRecordContent
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface CourseRecordEndPoint {


    @Headers("Accept: application/json")
    @GET("course-record/{courseRecordId}")
    suspend fun call(
        @Header("Authorization") token: String,
        @Path("courseRecordId") courseRecordId: String,
    ): SuccessfulResponse<CourseRecordContent>
}