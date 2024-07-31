package com.example.prueba_tecnica_mobile.utils

class ApiResponse<T>(
    val data: T? = null,
    val message: String,
    val status: Boolean,
    val code: Int
)