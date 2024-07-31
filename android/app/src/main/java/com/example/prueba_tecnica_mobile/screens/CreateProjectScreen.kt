package com.example.prueba_tecnica_mobile.screens

import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.prueba_tecnica_mobile.components.DatePickerComponent
import com.example.prueba_tecnica_mobile.components.SelectComponent
import com.example.prueba_tecnica_mobile.components.SwitchComponent
import com.example.prueba_tecnica_mobile.components.TextFieldComponent
import com.example.prueba_tecnica_mobile.models.ProjectCreate
import com.example.prueba_tecnica_mobile.models.StateProject
import com.example.prueba_tecnica_mobile.models.UserId
import com.example.prueba_tecnica_mobile.navigation.AppScreens
import com.example.prueba_tecnica_mobile.views.ProjectViewModel
import com.example.prueba_tecnica_mobile.views.UserViewModel
import kotlin.streams.toList

@RequiresApi(34)
@Composable
fun CreateProjectScreen(navController: NavController, userViewModel: UserViewModel, projectViewModel: ProjectViewModel){
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var state by remember { mutableStateOf(StateProject.PLANIFICACION) }
    var dateStart by remember { mutableStateOf("") }
    var dateEnd by remember { mutableStateOf("") }
    var share by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(10.dp)) {
        HeaderCreateProject(
            onBackClick = { navController.popBackStack() },
            title = "Nuevo Proyecto")
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Icono del proyecto",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(16.dp,0.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        IconComponentRefresh(
            icon = Icons.Default.Share,
            onRefresh = {}
        )
        Spacer(modifier = Modifier.height(15.dp))
        CreateProjectForm(
            name = name,
            onNameChange = { name = it },
            code = code,
            onCodeChange = { code = it },
            description = description,
            onDescriptionChange = { description = it },
            state = state,
            onStateChange = { state = it },
            dateStart = dateStart,
            onDateStartChange = { dateStart = it },
            dateEnd = dateEnd,
            onDateEndChange = { dateEnd = it },
            share = share,
            onShareChange = { share = it },
            onSubmitClick = {
                projectViewModel.createProject(
                    ProjectCreate(
                        name = name,
                        description = description,
                        code = code,
                        state = state,
                        dateStart = dateStart,
                        dateEnd = dateEnd,
                        share = share,
                        user = UserId(userViewModel.userSession!!.id)
                    )
                )
            },
            onBackClick = fun () {navController.navigate(AppScreens.HomeScreen.route) }
        )
    }
}

@Composable
fun HeaderCreateProject(onBackClick: () -> Unit, title: String){
    TopAppBar(
        title = { Text(text = title, fontSize = 30.sp) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Volver")
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}

@Composable
fun IconComponentRefresh(icon: ImageVector, onRefresh: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            imageVector = icon,
            contentDescription = "Icono",
            modifier = Modifier
                .size(45.dp)
                .padding(1.dp)
                .clip(
                    RoundedCornerShape(10.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(12.dp)
                )
        )
        Text(
            text = "Cambiar icono aleatorio",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            textAlign = TextAlign.End,
            modifier = Modifier.align(Alignment.CenterVertically)

        )
        IconButton(onClick = onRefresh) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Cambiar icono")
        }
    }
}

@RequiresApi(34)
@Composable
fun CreateProjectForm(
    name: String,
    onNameChange: (String) -> Unit,
    code: String,
    onCodeChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    state: StateProject,
    onStateChange: (StateProject) -> Unit,
    dateStart: String,
    onDateStartChange: (String) -> Unit,
    dateEnd: String,
    onDateEndChange: (String) -> Unit,
    share: Boolean,
    onShareChange: (Boolean) -> Unit,
    onSubmitClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        TextFieldComponent(
            label = "Nombre del proyecto",
            value = name,
            onValueChange = onNameChange,
            placeHolder = "Ingrese el nombre del proyecto"
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextFieldComponent(
            label = "Código",
            value = code,
            onValueChange = onCodeChange,
            placeHolder = "Ingrese el código del proyecto"
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextFieldComponent(
            label = "Descripción",
            value = description,
            onValueChange = onDescriptionChange,
            placeHolder = "Ingrese la descripción del proyecto"
        )
        Spacer(modifier = Modifier.height(12.dp))
        SelectComponent(
            label = "Estado",
            options = StateProject.values().map { it.name },
            selectOption = state.name,
            onSelectOption = { onStateChange(StateProject.valueOf(it)) }
        )
        Spacer(modifier = Modifier.height(12.dp))
        DatePickerComponent(
            label = "Fecha de inicio",
            selectDate = dateStart,
            onSelectDate = onDateStartChange
        )
        Spacer(modifier = Modifier.height(12.dp))
        DatePickerComponent(
            label = "Fecha de fin",
            selectDate = dateEnd,
            onSelectDate = onDateEndChange
        )
        Spacer(modifier = Modifier.height(12.dp))
        SwitchComponent(
            label = "¿Compartir con otros miembros?",
            isChecked = share,
            onChange = onShareChange
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick =  {
                onSubmitClick()
                onBackClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp,10.dp)
                .clip(RoundedCornerShape(15.dp))
        ) {
            Text(
                text = "Crear proyecto",
                fontSize = 20.sp,
                modifier = Modifier.padding(0.dp, 10.dp)
            )
        }
    }
}