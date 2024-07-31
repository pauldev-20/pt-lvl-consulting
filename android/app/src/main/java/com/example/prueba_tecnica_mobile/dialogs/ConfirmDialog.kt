package com.example.prueba_tecnica_mobile.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.prueba_tecnica_mobile.components.TextFieldComponent

@Composable
fun ConfirmDialog(onDismiss: () -> Unit, onConfirm: (email: String) -> Unit){
    Dialog(onDismissRequest = onDismiss) {
        var email by remember { mutableStateOf("") }
        Surface(
            shape = MaterialTheme.shapes.medium,
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = "Restablecer contraseña", style = MaterialTheme.typography.h6)
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Cerrar",
                            tint = Color.Black
                        )
                    }
                }
                TextFieldComponent(
                    value = email,
                    onValueChange = { email = it },
                    label = "Correo electrónico",
                    placeHolder = "Ingrese su correo electrónico",
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick =  {
                        onConfirm(email)
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp)
                        .clip(RoundedCornerShape(15.dp))
                ) {
                    Text(
                        text = "Solicitar",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(0.dp, 10.dp)
                    )
                }
            }
        }
    }
}