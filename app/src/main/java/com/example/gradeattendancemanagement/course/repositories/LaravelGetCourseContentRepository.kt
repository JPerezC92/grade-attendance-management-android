package com.example.gradeattendancemanagement.course.repositories

import com.example.gradeattendancemanagement.course.endpoints.CourseContentEndpoint
import com.example.gradeattendancemanagement.course.types.CourseContent
import com.example.gradeattendancemanagement.miscellaneous.types.Repository
import com.example.gradeattendancemanagement.miscellaneous.types.Resource
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import com.example.gradeattendancemanagement.miscellaneous.utils.getRetrofit

class LaravelGetCourseContentRepository constructor(
    private val token: String,
    private val courseId: String
) :
    Repository<Resource<SuccessfulResponse<CourseContent>>> {
    override suspend fun execute(): Resource<SuccessfulResponse<CourseContent>> {

        val response = try {
            getRetrofit().create(CourseContentEndpoint::class.java).call("Bearer $token", courseId)
        } catch (e: Exception) {
            return Resource.Error("Error al obtener los cursos")
        }

        return Resource.Success(response)
    }
}