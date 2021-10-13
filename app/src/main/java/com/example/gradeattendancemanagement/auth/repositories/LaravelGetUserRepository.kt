package com.example.gradeattendancemanagement.auth.repositories

import com.example.gradeattendancemanagement.auth.endpoints.GetUserEndpoint
import com.example.gradeattendancemanagement.auth.types.User
import com.example.gradeattendancemanagement.miscellaneous.types.Repository
import com.example.gradeattendancemanagement.miscellaneous.types.Resource
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import com.example.gradeattendancemanagement.miscellaneous.utils.getRetrofit

class LaravelGetUserRepository constructor(
    private val token: String
) : Repository<Resource<SuccessfulResponse<User>>> {

    override suspend fun execute(): Resource<SuccessfulResponse<User>> {

        val response = try {
            getRetrofit().create(GetUserEndpoint::class.java).call("Bearer $token")
        } catch (e: Exception) {
            return Resource.Error("Error al obtener informacion del usuario")
        }

        return Resource.Success(response)
    }
}