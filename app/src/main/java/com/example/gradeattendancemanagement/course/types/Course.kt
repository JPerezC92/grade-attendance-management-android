package com.example.gradeattendancemanagement.course.types

data class Course(
    val id: Int,
    val name: String,
    val instructorId: Int,
    val created_at: String,
    val updated_at: String
)