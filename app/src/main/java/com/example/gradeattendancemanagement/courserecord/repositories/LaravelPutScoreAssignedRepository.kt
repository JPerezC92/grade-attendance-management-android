package com.example.gradeattendancemanagement.courserecord.repositories

import com.example.gradeattendancemanagement.courserecord.endpoints.PutScoreAssignedEndpoint
import com.example.gradeattendancemanagement.courserecord.types.ScoreAssignedContent
import com.example.gradeattendancemanagement.miscellaneous.types.Repository
import com.example.gradeattendancemanagement.miscellaneous.types.Resource
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import com.example.gradeattendancemanagement.miscellaneous.utils.getRetrofit

class LaravelPutScoreAssignedRepository constructor(
    private val token: String,
    private val scoreAssignedContent: List<ScoreAssignedContent>
) : Repository<Resource<SuccessfulResponse<String>>> {

    override suspend fun execute(): Resource<SuccessfulResponse<String>> {
        val response = try {
            getRetrofit().create(PutScoreAssignedEndpoint::class.java)
                .call("Bearer $token", scoreAssignedContent)
        } catch (e: Exception) {
            return Resource.Error("Error al actualizar las calificaciones de los alumnos")
        }

        return Resource.Success(response)
    }
}