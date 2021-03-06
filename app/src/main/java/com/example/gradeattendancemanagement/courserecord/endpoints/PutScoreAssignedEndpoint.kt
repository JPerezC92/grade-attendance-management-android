package com.example.gradeattendancemanagement.courserecord.endpoints

import com.example.gradeattendancemanagement.courserecord.types.ScoreAssignedContent
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PUT

interface PutScoreAssignedEndpoint {
    @Headers("Accept: application/json")
    @PUT("scores-assigned")
    suspend fun call(
        @Header("Authorization") token: String,
        @Body scoresAssignedContent: List<ScoreAssignedContent>,
    ): SuccessfulResponse<String>
}