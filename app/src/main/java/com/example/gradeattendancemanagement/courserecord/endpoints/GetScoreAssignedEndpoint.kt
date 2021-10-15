package com.example.gradeattendancemanagement.courserecord.endpoints

import com.example.gradeattendancemanagement.courserecord.types.ScoreAssignedContent
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface GetScoreAssignedEndpoint {
    @Headers("Accept: application/json")
    @GET("scores/{scoreId}")
    suspend fun call(
        @Header("Authorization") token: String,
        @Path("scoreId") scoreId: String,
    ): SuccessfulResponse<List<ScoreAssignedContent>>
}