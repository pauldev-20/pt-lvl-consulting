package com.example.prueba_tecnica_mobile.views;

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba_tecnica_mobile.models.Project
import com.example.prueba_tecnica_mobile.repository.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject;
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.example.prueba_tecnica_mobile.models.ProjectCreate
import com.example.prueba_tecnica_mobile.models.StateProject
import com.example.prueba_tecnica_mobile.screens.LoginHeader
import com.example.prueba_tecnica_mobile.utils.advancedQueryProject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ProjectViewModel @Inject constructor(
        private val projectRepository: ProjectRepository
): ViewModel(){


        val projectsLocal: LiveData<List<Project>> by lazy {
                projectRepository.getProjectsLocal()
        }

        fun getProjectsAdvance(query: advancedQueryProject){
                val queryFormat = advancedQueryProject(
                        userId = query.userId,
                        name = if (query.name == "") null else query.name,
                        code = if (query.code == "") null else query.code,
                        state = if (query.state == "") null else query.state,
                        dateStart = if (query.dateStart == "") null else query.dateStart,
                        dateEnd = if (query.dateEnd == "") null else query.dateEnd,
                        description = if (query.description == "") null else query.description
                )
                viewModelScope.launch(Dispatchers.IO){
                        projectRepository.getProjectsAdvance(
                                queryFormat.userId,
                                queryFormat.code,
                                queryFormat.name,
                                queryFormat.state,
                                queryFormat.dateStart,
                                queryFormat.dateEnd,
                        )
                }
        }

        fun getProjects(userId: Int){
                viewModelScope.launch(Dispatchers.IO){
                        projectRepository.getProjects(userId)
                }
        }

        fun createProject(project: ProjectCreate){
                viewModelScope.launch(Dispatchers.IO){
                        projectRepository.createProject(project)
                }
        }

        fun deleteAllProjects(){
                viewModelScope.launch(Dispatchers.IO){
                        projectRepository.deleteAllProjects()
                }
        }
}