package com.example.prueba_tecnica_mobile.di

import android.content.Context
import androidx.room.Room
import com.example.prueba_tecnica_mobile.data.DataSource
import com.example.prueba_tecnica_mobile.data.ServiceApi
import com.example.prueba_tecnica_mobile.models.ProjectDao
import com.example.prueba_tecnica_mobile.models.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApiSourceModule {
    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = "http://192.168.1.42:8080/api/v1/" // Colocar la ip de la maquina donde se ejecuta el servidor

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun serviceApi(retrofit: Retrofit): ServiceApi =
        retrofit.create(ServiceApi::class.java)

    @Singleton
    @Provides
    fun dataSource(@ApplicationContext context: Context): DataSource {
        return Room.databaseBuilder(context, DataSource::class.java, "jira_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun userDao(db: DataSource): UserDao = db.userDao()

    @Singleton
    @Provides
    fun projectDao(db: DataSource): ProjectDao = db.projectDao()
}
