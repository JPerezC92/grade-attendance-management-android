package com.example.gradeattendancemanagement.courserecord.types

data class Student(
    val activities: List<ActivityX>,
    val attendances: Attendances,
    val courseRecordId: Int,
    val created_at: String,
    val finalScore: Int,
    val finalScoreRounded: Int,
    val firstname: String,
    val id: Int,
    val lastname: String,
    val studentCode: String,
    val updated_at: String
)