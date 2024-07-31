package com.example.prueba_tecnica_mobile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prueba_tecnica_mobile.models.Project
import com.example.prueba_tecnica_mobile.models.StateProject

@Composable
fun ListProjectComponent(projects: List<Project>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(0.dp, 16.dp)
    ) {
        items(projects.size) { index ->
            val project = projects[index]
            CardProject(
                project = project
            )
        }
    }
}

@Composable
fun CardProject(project: Project) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .aspectRatio(1f),
        shape = RoundedCornerShape(12.dp),
        elevation = 10.dp
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.CenterStart,
            ) {
                Box(
                    modifier = Modifier
                        .padding(1.dp)
                        .aspectRatio(1f)
                        .background(Color(0xFFC3DFF7), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        imageVector = Icons.Rounded.ShoppingCart,
                        contentDescription = "Icono de proyecto",
                        modifier = Modifier
                            .padding(10.dp)
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            Column(
                modifier = Modifier.weight(2f).padding(0.dp,10.dp,0.dp,0.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = project.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = project.code,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = StateColor(project.state).getText(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier
                        .background(StateColor(project.state).getColor())
                        .padding(3.dp),
                )
            }
        }
    }
}

data class StateColor(val state : StateProject) {
    fun getColor(): Color {
        return when (state) {
            StateProject.PLANIFICACION -> Color.Gray
            StateProject.EN_REVISION -> Color.Green
            StateProject.FINALIZADO -> Color(0xFFC3DFF7)
            StateProject.EN_CURSO -> Color.Yellow
        }
    }

    fun getText(): String {
        return when (state) {
            StateProject.PLANIFICACION -> "PLANIFICACION"
            StateProject.EN_REVISION -> "EN REVISION"
            StateProject.FINALIZADO -> "FINALIZADO"
            StateProject.EN_CURSO -> "EN CURSO"
        }
    }
}