package com.example.gradeattendancemanagement.courserecord.repositories

import com.example.gradeattendancemanagement.courserecord.endpoints.CourseRecordEndPoint
import com.example.gradeattendancemanagement.courserecord.types.CourseRecordContent
import com.example.gradeattendancemanagement.miscellaneous.types.Repository
import com.example.gradeattendancemanagement.miscellaneous.types.Resource
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import com.example.gradeattendancemanagement.miscellaneous.utils.getRetrofit

class LaravelGetCourseRecordRepository constructor(
    private val token: String,
    private val courseRecordId: String
) : Repository<Resource<SuccessfulResponse<CourseRecordContent>>> {

    override suspend fun execute(): Resource<SuccessfulResponse<CourseRecordContent>> {

        val response = try {
            getRetrofit().create(CourseRecordEndPoint::class.java)
                .call("Bearer $token", courseRecordId)
        } catch (e: Exception) {
            return Resource.Error("Error al obtener los datos del registro")
        }

        return Resource.Success(response)
    }
}