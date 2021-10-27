package com.example.gradeattendancemanagement.courserecord.endpoints

import com.example.gradeattendancemanagement.courserecord.types.ScoreAssignedContent
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import retrofit2.http.*

interface PutScoreAssignedByIdEndpoint {
    @Headers("Accept: application/json")
    @PUT("scores-assigned/{scoresAssignedId}")
    suspend fun call(
        @Header("Authorization") token: String,
        @Path("scoresAssignedId") scoreId: String,
        @Body scoreAssigned: ScoreAssignedContent,
    ): SuccessfulResponse<String>

}