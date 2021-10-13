package com.example.gradeattendancemanagement.miscellaneous.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun getRetrofit(): Retrofit {
    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://grade-attendance-management.herokuapp.com/api/")
        .build()
}