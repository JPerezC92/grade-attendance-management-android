package com.example.gradeattendancemanagement.courserecord.repositories

import com.example.gradeattendancemanagement.courserecord.endpoints.PutScoreAssignedByIdEndpoint
import com.example.gradeattendancemanagement.courserecord.endpoints.PutScoreAssignedEndpoint
import com.example.gradeattendancemanagement.courserecord.types.ScoreAssignedContent
import com.example.gradeattendancemanagement.miscellaneous.types.Repository
import com.example.gradeattendancemanagement.miscellaneous.types.Resource
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import com.example.gradeattendancemanagement.miscellaneous.utils.getRetrofit

class LaravelPutScoreAssignedByIdRepository constructor(
    private val token: String,
    private val scoreAssigned: ScoreAssignedContent
) : Repository<Resource<SuccessfulResponse<String>>> {

    override suspend fun execute(): Resource<SuccessfulResponse<String>> {
        val response = try {
            getRetrofit().create(PutScoreAssignedByIdEndpoint::class.java)
                .call("Bearer $token", scoreAssigned.id.toString(), scoreAssigned)
        } catch (e: Exception) {
            return Resource.Error("Error al actualizar la calificacion")
        }

        return Resource.Success(response)
    }
}