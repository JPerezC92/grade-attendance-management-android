package com.example.gradeattendancemanagement.courserecord.repositories

import com.example.gradeattendancemanagement.courserecord.endpoints.GetScoreAssignedEndpoint
import com.example.gradeattendancemanagement.courserecord.types.ScoreAssignedContent
import com.example.gradeattendancemanagement.miscellaneous.types.Repository
import com.example.gradeattendancemanagement.miscellaneous.types.Resource
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import com.example.gradeattendancemanagement.miscellaneous.utils.getRetrofit

class LaravelGetScoreAssignedRepository constructor(
    val token: String,
    val scoreId: String,
    val courseRecordId: String
) :
    Repository<Resource<SuccessfulResponse<List<ScoreAssignedContent>>>> {
    override suspend fun execute(): Resource<SuccessfulResponse<List<ScoreAssignedContent>>> {
        val response = try {
            getRetrofit().create(GetScoreAssignedEndpoint::class.java)
                .call("Bearer $token", scoreId, courseRecordId)
        } catch (e: Exception) {
            return Resource.Error("Error al obtener las calificaciones de los alumnos")
        }

        return Resource.Success(response)
    }
}