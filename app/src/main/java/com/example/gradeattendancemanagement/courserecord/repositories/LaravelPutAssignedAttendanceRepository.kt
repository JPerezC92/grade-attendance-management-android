package com.example.gradeattendancemanagement.courserecord.repositories

import com.example.gradeattendancemanagement.courserecord.endpoints.PutAssignedAttendancesEndpoint
import com.example.gradeattendancemanagement.courserecord.types.assignedattendance.AttendancesCheck
import com.example.gradeattendancemanagement.miscellaneous.types.Repository
import com.example.gradeattendancemanagement.miscellaneous.types.Resource
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import com.example.gradeattendancemanagement.miscellaneous.utils.getRetrofit

class LaravelPutAssignedAttendanceRepository constructor(
    private var token: String,
    private var attendancesCheck: List<AttendancesCheck>
) : Repository<Resource<SuccessfulResponse<String>>> {

    override suspend fun execute(): Resource<SuccessfulResponse<String>> {
        val response = try {
            getRetrofit().create(PutAssignedAttendancesEndpoint::class.java)
                .call("Bearer $token", attendancesCheck)
        } catch (e: Exception) {
            return Resource.Error("Error al guardar las asistencias de los alumnos")
        }
        return Resource.Success(response)
    }
}