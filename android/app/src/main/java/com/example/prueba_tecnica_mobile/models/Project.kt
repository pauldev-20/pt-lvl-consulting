package com.example.prueba_tecnica_mobile.models

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

@Entity(tableName = "projects")
data class Project (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "code")
    val code: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "dateStart")
    val dateStart: String,
    @ColumnInfo(name = "dateEnd")
    val dateEnd: String,
    @ColumnInfo(name = "share")
    val share: Boolean,
    @ColumnInfo(name = "state")
    val state: StateProject
    )

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects ORDER BY id")
    fun getProjects(): LiveData<List<Project>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProject(project: Project): Unit

    @Query("DELETE FROM projects")
    fun deleteAllProjects(): Unit

    @RawQuery(observedEntities = [Project::class])
    fun getProjects(query: SupportSQLiteQuery): LiveData<List<Project>>
}