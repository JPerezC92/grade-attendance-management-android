package com.example.gradeattendancemanagement.courserecord.endpoints

import com.example.gradeattendancemanagement.courserecord.types.assignedattendance.AttendancesCheck
import com.example.gradeattendancemanagement.miscellaneous.types.SuccessfulResponse
import retrofit2.http.*

interface PutAssignedAttendancesEndpoint {
    @Headers("Accept: application/json")
    @PUT("attendance-check")
    suspend fun call(
        @Header("Authorization") token: String,
        @Body attendancesCheck: List<AttendancesCheck>,
    ): SuccessfulResponse<String>
}