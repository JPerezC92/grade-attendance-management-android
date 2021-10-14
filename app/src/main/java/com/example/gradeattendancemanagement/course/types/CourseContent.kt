package com.example.gradeattendancemanagement.course.types

import com.example.gradeattendancemanagement.auth.types.User

data class CourseContent(
    val id: Int,
    val name: String,
    val instructorId: Int,
    val instructor: User,
    val course_records: List<CourseRecord>,
    val created_at: String,
    val updated_at: String
)