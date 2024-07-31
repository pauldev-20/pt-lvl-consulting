package com.example.prueba_tecnica_mobile.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.prueba_tecnica_mobile.models.Project
import com.example.prueba_tecnica_mobile.models.ProjectDao
import com.example.prueba_tecnica_mobile.models.UserDao
import com.example.prueba_tecnica_mobile.models.UserProfile

@Database(entities = [UserProfile::class,Project::class], version = 5)
abstract class DataSource : RoomDatabase(){
    abstract  fun userDao(): UserDao
    abstract fun projectDao(): ProjectDao
}