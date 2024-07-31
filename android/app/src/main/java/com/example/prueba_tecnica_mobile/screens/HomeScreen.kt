package com.example.prueba_tecnica_mobile.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.prueba_tecnica_mobile.R
import com.example.prueba_tecnica_mobile.components.ListProjectComponent
import com.example.prueba_tecnica_mobile.components.SearchFieldComponent
import com.example.prueba_tecnica_mobile.dialogs.FilterDialog
import com.example.prueba_tecnica_mobile.models.StateProject
import com.example.prueba_tecnica_mobile.navigation.AppScreens
import com.example.prueba_tecnica_mobile.utils.advancedQueryProject
import com.example.prueba_tecnica_mobile.views.ProjectViewModel
import com.example.prueba_tecnica_mobile.views.UserViewModel

@Composable
fun HomeScreen(navController: NavController, userViewModel: UserViewModel, projectViewModel: ProjectViewModel) {
    var showFilterProject by remember {
        mutableStateOf(false)
    }

    val projects by projectViewModel.projectsLocal.observeAsState(arrayListOf())
    var projectsFiltered by remember {
        mutableStateOf(projects)
    }
    var filterName by remember {
        mutableStateOf("")
    }

    fun handleFilterAdvance(query: advancedQueryProject){
        projectViewModel.getProjectsAdvance(query = query)
    }

    LaunchedEffect(filterName, projects ) {
        projectsFiltered = projects.filter {
            it.name.toLowerCase().trim().contains(filterName.toLowerCase().trim())
        }
    }

    LaunchedEffect(userViewModel.userSession) {
        if (userViewModel.userSession == null) {
            navController.popBackStack()
            navController.navigate(AppScreens.LoginScreen.route)
        } else {
            if(!userViewModel.rememberMe || projects.isEmpty()) {
                projectViewModel.getProjects(userViewModel.userSession!!.id)
            }
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp, 10.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if(userViewModel.userSession != null) {
                HomeHeader(
                    userImage = painterResource(id = R.drawable.pdefault),
                    userName = "${userViewModel.getNameComplete()}",
                    userDescription = "${userViewModel.getCargo()}",
                    navController = navController,
                    onLogout = {
                        projectViewModel.deleteAllProjects()
                        userViewModel.logoutUser()
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            SearchFieldComponent(
                query = filterName,
                onQueryChange = {
                    filterName = it
                }, { showFilterProject = true} )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Tableros",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.W700
            )
            if (projectsFiltered == null || projectsFiltered.isEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "No hay proyectos",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.W500
                )
            } else {
                ListProjectComponent(
                    projects = projectsFiltered
                )
            }
            if (showFilterProject) {
                FilterDialog(
                    onFilter = { query -> handleFilterAdvance(query) },
                    onDismiss =  { showFilterProject = false},
                    userId = userViewModel.userSession!!.id
                )
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate(route = AppScreens.CreateProjectScreen.route)},
            modifier = Modifier
                .padding(8.dp, 15.dp)
                .size(70.dp)
                .align(Alignment.BottomEnd),
            shape = RoundedCornerShape(20),
            backgroundColor = Color.Blue,
            elevation = FloatingActionButtonDefaults.elevation(10.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar Proyecto",
                tint = Color.White,
                modifier = Modifier.size(34.dp)
            )
        }
    }
}

@Composable
fun HomeHeader(
    userImage: Painter,
    userName: String,
    userDescription: String,
    navController: NavController,
    onLogout: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
            Image(
                painter = userImage,
                contentDescription = "Imagen de Perfil",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(70.dp)
                    .weight(1f), contentScale = ContentScale.FillWidth
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp)
                    .weight(3f),
                horizontalAlignment = Alignment.Start) {
                TextButton(
                    onClick = { navController.navigate(AppScreens.ProfileScreen.route) },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.Black,
                        backgroundColor = Color.Transparent,
                    ),
                    contentPadding = PaddingValues(0.dp),
                ){
                    Text(
                        text = userName,
                        style = MaterialTheme.typography.h4,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W700,
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                }
                Text(
                    text = userDescription,
                    style = MaterialTheme.typography.h6,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            IconButton(
                onClick = onLogout,
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
                    .border(BorderStroke(1.5.dp, Color.Gray), shape = RoundedCornerShape(50.dp))) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = "Cerrar Sesión",
                    tint = Color.Blue
                )
            }
    }
}

data class FiltersProject(
    val name: String,
    val code: String,
    val state: StateProject,
    val description: String,
    val dateStart: String,
    val dateEnd: String
)