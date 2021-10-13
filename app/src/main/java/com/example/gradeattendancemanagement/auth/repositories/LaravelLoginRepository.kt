package com.example.gradeattendancemanagement.auth.repositories

import com.example.gradeattendancemanagement.auth.endpoints.LoginEndpoint
import com.example.gradeattendancemanagement.auth.types.Credentials
import com.example.gradeattendancemanagement.miscellaneous.types.Repository
import com.example.gradeattendancemanagement.miscellaneous.types.Resource
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import com.example.gradeattendancemanagement.miscellaneous.utils.getRetrofit

class LaravelLoginRepository constructor(private val credentials: Credentials) :
    Repository<Resource<SuccessfulResponse<String>>> {

    override suspend fun execute(): Resource<SuccessfulResponse<String>> {
        val response = try {
            getRetrofit().create(LoginEndpoint::class.java).call(credentials)
        } catch (e: Exception) {
            return Resource.Error("Error al iniciar sesion")
        }

        return Resource.Success(response)
    }
}