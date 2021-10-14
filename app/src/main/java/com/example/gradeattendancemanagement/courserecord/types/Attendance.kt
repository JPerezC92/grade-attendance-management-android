package com.example.gradeattendancemanagement.courserecord.types

data class Attendance(
    val courseRecordId: Int,
    val created_at: String,
    val date: String,
    val id: Int,
    val updated_at: String
)