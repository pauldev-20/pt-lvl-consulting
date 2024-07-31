package com.example.prueba_tecnica_mobile.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.prueba_tecnica_mobile.models.StateProject

data class advancedQueryProject(
    val userId : Int,
    val name : String? = null,
    val code : String? = null,
    val state : String? = null,
    val dateStart : String? = null,
    val dateEnd : String? = null,
    val description : String? = null
)