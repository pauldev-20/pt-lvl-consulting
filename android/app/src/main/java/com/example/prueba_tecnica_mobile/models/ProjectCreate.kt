package com.example.prueba_tecnica_mobile.models

import java.util.Objects

class ProjectCreate(
    //val id: Int,
    val name: String,
    val code: String,
    val description: String,
    val dateStart: String,
    val dateEnd: String,
    val share: Boolean,
    val state: StateProject,
    val user: UserId
    )

class UserId(
    val id: Int
    )