package com.example.gradeattendancemanagement.courserecord.repositories

import com.example.gradeattendancemanagement.courserecord.endpoints.CourseRecordEndPoint
import com.example.gradeattendancemanagement.courserecord.endpoints.GetAssignedAttendancesEndpoint
import com.example.gradeattendancemanagement.courserecord.types.assignedattendance.AssignedAttendance
import com.example.gradeattendancemanagement.miscellaneous.types.Repository
import com.example.gradeattendancemanagement.miscellaneous.types.Resource
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import com.example.gradeattendancemanagement.miscellaneous.utils.getRetrofit

class LaravelGetAssignedAttendanceRepository constructor(
    private val token: String,
    private val attendanceId: String
) : Repository<Resource<SuccessfulResponse<AssignedAttendance>>> {

    override suspend fun execute(): Resource<SuccessfulResponse<AssignedAttendance>> {
        val response = try {
            getRetrofit().create(GetAssignedAttendancesEndpoint::class.java)
                .call("Bearer $token", attendanceId)
        } catch (e: Exception) {
            return Resource.Error("Error al obtener las asistencias de los alumnos")
        }

        return Resource.Success(response)
    }

}