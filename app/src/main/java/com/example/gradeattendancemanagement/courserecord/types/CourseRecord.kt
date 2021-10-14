package com.example.gradeattendancemanagement.courserecord.types

data class CourseRecord(
    val career: String,
    val courseId: Int,
    val created_at: String,
    val group: String,
    val id: Int,
    val instructorId: Int,
    val periodId: Int,
    val semester: String,
    val turn: String,
    val updated_at: String
)