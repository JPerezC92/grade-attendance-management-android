package com.example.gradeattendancemanagement.course.types

data class Period(
    val created_at: String,
    val id: Int,
    val instructorId: Int,
    val status: String,
    val updated_at: String,
    val value: String
)