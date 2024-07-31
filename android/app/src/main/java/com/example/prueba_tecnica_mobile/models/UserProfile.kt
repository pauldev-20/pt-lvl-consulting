package com.example.prueba_tecnica_mobile.models

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

@Entity(tableName = "user_profile")
data class UserProfile (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "names")
    val names: String,
    @ColumnInfo(name = "lastNames")
    val lastNames: String,
    @ColumnInfo(name = "nameCompany")
    val nameCompany: String,
    @ColumnInfo(name = "position")
    val post: String,
    @ColumnInfo(name = "phoneNumber")
    val phoneNumber: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "photo")
    val photo: String?,
    @ColumnInfo(name = "recordar")
    val recordar: Boolean? = false
)

@Dao
interface UserDao {
    @Query("SELECT * FROM user_profile LIMIT 1")
    fun getUserProfile(): UserProfile

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserProfile(userProfile: UserProfile)

    @Delete
    fun deleteUserProfile(userProfile: UserProfile)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUserProfile(userProfile: UserProfile)
}