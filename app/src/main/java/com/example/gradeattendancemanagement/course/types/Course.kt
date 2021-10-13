package com.example.gradeattendancemanagement.course.types

data class Course(
    val created_at: String,
    val id: Int,
    val instructorId: Int,
    val name: String,
    val updated_at: String
)