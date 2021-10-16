package com.example.gradeattendancemanagement.auth.types

data class Register(
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String
)
