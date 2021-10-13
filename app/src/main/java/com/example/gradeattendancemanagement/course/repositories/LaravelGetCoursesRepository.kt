package com.example.gradeattendancemanagement.course.repositories

import com.example.gradeattendancemanagement.course.endpoints.CoursesEndpoint
import com.example.gradeattendancemanagement.course.types.Course
import com.example.gradeattendancemanagement.miscellaneous.types.Repository
import com.example.gradeattendancemanagement.miscellaneous.types.Resource
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import com.example.gradeattendancemanagement.miscellaneous.utils.getRetrofit


class LaravelGetCoursesRepository constructor(
    private val token: String
) : Repository<Resource<SuccessfulResponse<List<Course>>>> {


    override suspend fun execute(): Resource<SuccessfulResponse<List<Course>>> {
        val response = try {
            getRetrofit().create(CoursesEndpoint::class.java).call("Bearer $token")
        } catch (e: Exception) {
            return Resource.Error("Error al obtener los cursos")
        }

        return Resource.Success(response)
    }
}




