package com.example.gradeattendancemanagement.courserecord.types.assignedattendance

data class AttendancesCheck(
    val attendanceId: Int,
    var attendanceStatusId: Int?,
    val courseRecordId: Int,
    val created_at: Any,
    val date: String,
    val firstname: String,
    val id: Int,
    val lastname: String,
    val studentId: Int,
    val updated_at: Any
)