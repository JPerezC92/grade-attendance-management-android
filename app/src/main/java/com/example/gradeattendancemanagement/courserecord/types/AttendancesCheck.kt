package com.example.gradeattendancemanagement.courserecord.types

data class AttendancesCheck(
    val attendanceId: Int,
    val attendanceStatusId: Int,
    val attendanceStatusValue: String,
    val created_at: Any,
    val id: Int,
    val studentId: Int,
    val updated_at: String
)