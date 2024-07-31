package com.example.prueba_tecnica_mobile.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.prueba_tecnica_mobile.data.ServiceApi
import com.example.prueba_tecnica_mobile.models.Project
import com.example.prueba_tecnica_mobile.models.ProjectCreate
import com.example.prueba_tecnica_mobile.models.ProjectDao
import com.example.prueba_tecnica_mobile.models.UserProfile
import javax.inject.Inject

interface ProjectRepository {
    suspend fun getProjects(userId: Int): List<Project>?
    fun getProjectsLocal(): LiveData<List<Project>>
    suspend fun deleteAllProjects(): Unit
    suspend fun createProject(project: ProjectCreate): Project?
    suspend fun getProjectsAdvance(userId: Int, code: String?, name: String?, state: String?, dateStart: String?, dateEnd: String?): Unit
}

class ProjectRepositoryImp @Inject constructor(
    private val apiSource: ServiceApi,
    private val projectDao: ProjectDao
) : ProjectRepository {

    override suspend fun getProjects(userId: Int): List<Project>? {
            try {
                val response = apiSource.getProjectsRequest(userId)
                response.data?.also {
                    for (project in response.data) {
                        projectDao.saveProject(project)
                    }
                    return response.data
                }
                return null
            } catch (e: Exception) {
                Log.d("ProjectRepository", "Error: ${e.message}")
                return null
            }
    }

    override fun getProjectsLocal(): LiveData<List<Project>> {
        return projectDao.getProjects()
    }

    override suspend fun createProject(project: ProjectCreate): Project? {
        try {
            val response = apiSource.createProjectRequest(project)
            return response.data?.also {
                projectDao.saveProject(response.data)
            }
        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun getProjectsAdvance(userId: Int, code: String?, name: String?, state: String?, dateStart: String?, dateEnd: String?): Unit {
        try {
            val response = apiSource.getProjectsAdvanceRequest(userId, code, name, state, dateStart, dateEnd)
            response.data?.also {
                projectDao.deleteAllProjects()
                for (project in response.data) {
                    projectDao.saveProject(project)
                }
            }
        } catch (e: Exception) {
            Log.d("ProjectRepository", "Error: ${e.message}")
        }
    }

    override suspend fun deleteAllProjects() {
        projectDao.deleteAllProjects()
    }

}