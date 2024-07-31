package com.example.prueba_tecnica_mobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.prueba_tecnica_mobile.R
import com.example.prueba_tecnica_mobile.components.TextFieldComponent
import com.example.prueba_tecnica_mobile.models.UserProfile
import com.example.prueba_tecnica_mobile.navigation.AppScreens
import com.example.prueba_tecnica_mobile.views.UserViewModel

@Composable
fun ProfileScreen(navController: NavController, viewModel: UserViewModel){

    var names by remember { mutableStateOf("Juan") }
    var lastNames by remember { mutableStateOf("Perez") }
    var nameCompany by remember { mutableStateOf("Empresa") }
    var post by remember { mutableStateOf("Cargo") }
    var phoneNumber by remember { mutableStateOf("123456789") }
    var email by remember { mutableStateOf("juan@gmail.com") }

    LaunchedEffect(viewModel.userSession) {
        if (viewModel.userSession != null) {
            names = viewModel.userSession!!.names
            lastNames = viewModel.userSession!!.lastNames
            nameCompany = viewModel.userSession!!.nameCompany
            post = viewModel.userSession!!.post
            phoneNumber = viewModel.userSession!!.phoneNumber
            email = viewModel.userSession!!.email
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        HeaderProfile(
            onBackClick = { navController.popBackStack() },
            title = "Mi Perfil")
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = { navController.navigate(AppScreens.CameraScreen.route)},
            shape = CircleShape,
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
        ) {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pdefault),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar",
                    modifier = Modifier
                        .size(34.dp)
                        .background(Color.White, CircleShape)
                        .padding(4.dp)
                        .align(Alignment.BottomEnd)
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        ProfileForm(
            names = names,
            onNamesChange = { names = it },
            lastNames = lastNames,
            onLastNamesChange = { lastNames = it },
            nameCompany = nameCompany,
            onNameCompanyChange = { nameCompany = it },
            position = post,
            onPositionChange = { post = it },
            phoneNumber = phoneNumber,
            onPhoneNumberChange = { phoneNumber = it },
            email = email,
            onEmailChange = { email = it },
            onSubmitClick = {
                viewModel.updateUser(
                    UserProfile(
                        names = names,
                        photo = null,
                        lastNames = lastNames,
                        nameCompany = nameCompany,
                        post = post,
                        phoneNumber = phoneNumber,
                        email = email,
                        id = viewModel.userSession!!.id
                    )
                )
            }
        )
    }
}

@Composable
fun ProfileForm(
    names: String,
    onNamesChange: (String) -> Unit,
    lastNames: String,
    onLastNamesChange: (String) -> Unit,
    nameCompany: String,
    onNameCompanyChange: (String) -> Unit,
    position: String,
    onPositionChange: (String) -> Unit,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    onSubmitClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        TextFieldComponent(
            value = names,
            onValueChange = onNamesChange,
            label = "Nombres",
            placeHolder = "Nombres"
        )
        TextFieldComponent(
            value = lastNames,
            onValueChange = onLastNamesChange,
            label = "Apellidos",
            placeHolder = "Apellidos"
        )
        TextFieldComponent(
            value = nameCompany,
            onValueChange = onNameCompanyChange,
            label = "Nombre de la empresa",
            placeHolder = "Nombre de la empresa"
        )
        TextFieldComponent(
            value = position,
            onValueChange = onPositionChange,
            label = "Cargo en la empresa",
            placeHolder = "Cargo en la empresa"
        )
        TextFieldComponent(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            label = "Teléfono",
            placeHolder = "Teléfono"
        )
        TextFieldComponent(
            value = email,
            onValueChange = onEmailChange,
            label = "Correo electrónico",
            placeHolder = "Correo electrónico"
        )
        Button(
            onClick =  {
                onSubmitClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp)
                .clip(RoundedCornerShape(15.dp))
        ) {
            Text(
                text = "Guardar",
                fontSize = 20.sp,
                modifier = Modifier.padding(0.dp, 10.dp)
            )
        }
    }
}

@Composable
fun HeaderProfile(onBackClick: () -> Unit, title: String){
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