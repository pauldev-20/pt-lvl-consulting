package com.example.prueba_tecnica_mobile.dialogs

import androidx.compose.foundation.background
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
import com.example.prueba_tecnica_mobile.utils.advancedQueryProject

@Composable
fun FilterDialog(onDismiss: () -> Unit, onFilter: (query: advancedQueryProject) -> Unit, userId: Int) {
    var codigo by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var dateStart by remember { mutableStateOf("") }
    var dateEnd by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
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
                    Text(text = "Búsqueda Avanzada", style = MaterialTheme.typography.h6)
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

                Spacer(modifier = Modifier.height(8.dp))
                TextFieldComponent(
                    value = codigo,
                    onValueChange = { codigo = it },
                    label = "Codido del proyecto",
                    placeHolder = "Código"
                )
                Spacer(modifier = Modifier.height(12.dp))

                TextFieldComponent(
                    value = name,
                    onValueChange = { name = it },
                    label = "Nombre del proyecto",
                    placeHolder = "Nombre"
                )
                Spacer(modifier = Modifier.height(12.dp))

                TextFieldComponent(
                    value = state,
                    onValueChange = { state = it },
                    label = "Estado del proyecto",
                    placeHolder = "Estado"
                )
                Spacer(modifier = Modifier.height(12.dp))

                TextFieldComponent(
                    value = dateStart,
                    onValueChange = { dateStart = it },
                    label = "Fecha de inicio",
                    placeHolder = "Fecha de inicio"
                )
                Spacer(modifier = Modifier.height(12.dp))

                TextFieldComponent(
                    value = dateEnd,
                    onValueChange = { dateEnd = it },
                    label = "Fecha de finalización",
                    placeHolder = "Fecha de finalización"
                )
                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Button(
                        onClick =  {
                            onFilter(
                                advancedQueryProject(
                                    userId = userId,
                                    code = codigo.trim(),
                                    name = name.trim(),
                                    state = state.trim(),
                                    dateStart = dateStart.trim(),
                                    dateEnd = dateEnd.trim()
                                )
                            )
                            onDismiss()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 10.dp)
                            .clip(RoundedCornerShape(15.dp))
                    ) {
                        Text(
                            text = "Buscar",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(0.dp, 10.dp)
                        )
                    }
                    Button(
                        onClick =  {
                            codigo = ""
                            name = ""
                            state = ""
                            dateStart = ""
                            dateEnd = ""
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 10.dp)
                            .clip(RoundedCornerShape(15.dp))
                    ) {
                        Text(
                            text = "Limpiar",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(0.dp, 10.dp)
                        )
                    }
                }
            }
        }
    }
}