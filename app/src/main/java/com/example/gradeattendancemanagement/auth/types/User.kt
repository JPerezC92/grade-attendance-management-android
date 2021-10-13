package com.example.gradeattendancemanagement.auth.types

data class User(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val email: String,
    val created_at: String,
    val updated_at: String
)