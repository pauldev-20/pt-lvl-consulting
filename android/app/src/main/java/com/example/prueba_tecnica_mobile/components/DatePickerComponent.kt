package com.example.prueba_tecnica_mobile.components

import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Calendar

@Composable
fun DatePickerComponent(
    label: String,
    selectDate: String,
    onSelectDate: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var year = calendar.get(Calendar.YEAR)
    var month = calendar.get(Calendar.MONTH)
    var day = calendar.get(Calendar.DAY_OF_MONTH)

    TextField(
        value = selectDate,
        onValueChange = {},
        readOnly = true,
        label = { Text(label) },
        trailingIcon = {
            IconButton(onClick = {
                DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
                    year = selectedYear
                    month = selectedMonth
                    day = selectedDay
                    onSelectDate("$year-${if (month + 1 > 9) month + 1 else "0${month + 1}"}-${if (day > 9) day else "0$day"}")

                }, year, month, day).show()
            }) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Seleccionar fecha",
                    tint = Color.Blue
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .border(BorderStroke(1.5.dp, Color.Blue), shape = RoundedCornerShape(15.dp))
    )
}