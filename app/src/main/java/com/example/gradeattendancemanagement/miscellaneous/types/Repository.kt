package com.example.gradeattendancemanagement.miscellaneous.types

interface Repository<T> {
    suspend fun execute(): T
}

