package com.example.prueba_tecnica_mobile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchFieldComponent(
    query: String,
    onQueryChange: (String) -> Unit,
    onFilterClick: () -> Unit
) {
    TextField(
        value = query,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color.Blue
        ),
        placeholder = { Text(text = "Buscar en tableros") },
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Icono de Buscar",
                modifier = Modifier.size(24.dp),
                tint = Color.Blue)
        },
        trailingIcon = {
            IconButton(onClick = onFilterClick) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filter Icon",
                    tint = Color.Blue
                )
            }
        },
        label = { Text(text = "Buscar") },
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .border(BorderStroke(1.5.dp, Color.Gray), shape = RoundedCornerShape(15.dp))
    )
}
