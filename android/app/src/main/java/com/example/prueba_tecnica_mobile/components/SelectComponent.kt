package com.example.prueba_tecnica_mobile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties

@Composable
fun SelectComponent(
    label: String,
    options: List<String>,
    selectOption: String,
    onSelectOption: (String) -> Unit
) {
    var open by remember { mutableStateOf(false) }

    Column() {
        Text(text = label, style = MaterialTheme.typography.subtitle1)
        Box() {
            OutlinedTextField(
                value = selectOption,
                onValueChange = { },
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Seleccionar opción") },
                trailingIcon = {
                    IconButton(onClick = { open = true }) {
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                }
            )
            DropdownMenu(
                expanded = open,
                onDismissRequest = { open = false },
                modifier = Modifier.fillMaxWidth().border(BorderStroke(1.5.dp, Color.Blue), shape = RoundedCornerShape(15.dp))
            ) {
                options.forEach { option ->
                    DropdownMenuItem(onClick = {
                        onSelectOption(option)
                        open = false
                    }) {
                        Text(text = option)
                    }
                }
            }
        }
    }
}