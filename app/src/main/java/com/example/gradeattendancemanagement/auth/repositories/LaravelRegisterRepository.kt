package com.example.gradeattendancemanagement.auth.repositories

import com.example.gradeattendancemanagement.auth.endpoints.RegisterEndpoint
import com.example.gradeattendancemanagement.auth.types.Register
import com.example.gradeattendancemanagement.miscellaneous.types.Repository
import com.example.gradeattendancemanagement.miscellaneous.types.Resource
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import com.example.gradeattendancemanagement.miscellaneous.utils.getRetrofit

class LaravelRegisterRepository constructor(private val register: Register) :
    Repository<Resource<SuccessfulResponse<String>>> {
    override suspend fun execute(): Resource<SuccessfulResponse<String>> {
        val response = try {
            getRetrofit().create(RegisterEndpoint::class.java).call(register)
        } catch (e: Exception) {
            return Resource.Error("Error al iniciar sesion")
        }

        return Resource.Success(response)
    }
}