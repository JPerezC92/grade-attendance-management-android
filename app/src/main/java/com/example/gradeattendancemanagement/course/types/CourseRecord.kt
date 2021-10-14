package com.example.gradeattendancemanagement.course.types

data class CourseRecord(
    val career: String,
    val courseId: Int,
    val created_at: String,
    val group: String,
    val id: Int,
    val instructorId: Int,
    val period: Period,
    val periodId: Int,
    val semester: String,
    val turn: String,
    val updated_at: String
)