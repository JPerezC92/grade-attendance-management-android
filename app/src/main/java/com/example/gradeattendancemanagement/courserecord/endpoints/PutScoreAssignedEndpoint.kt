package com.example.gradeattendancemanagement.courserecord.endpoints

import com.example.gradeattendancemanagement.courserecord.types.ScoreAssignedContent
import com.example.gradeattendancemanagement.miscellaneous.types.Resource
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import retrofit2.http.*

interface PutScoreAssignedEndpoint {
    @Headers("Accept: application/json")
    @PUT("scores-assigned")
    suspend fun call(
        @Header("Authorization") token: String,
        @Body scoresAssignedContent: List<ScoreAssignedContent>,
    ): SuccessfulResponse<String>
}