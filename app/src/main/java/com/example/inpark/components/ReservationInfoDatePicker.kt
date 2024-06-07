package com.example.inpark.components

import android.icu.util.Calendar
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inpark.R
import com.example.inpark.outfitFamily
import java.time.LocalDate
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationInfoDatePicker(
    label: String?,
    date: MutableState<String>
) {
    val year: Int
    var month: Int
    val day: Int
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/${month+1}/$year"
        }, year, month+1, day
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {datePickerDialog.show()},modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
            border = BorderStroke(1.dp,Color(0xffA0F000)),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xff143C3C)) ) {
            Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start){
                Icon(painter = painterResource(id = R.drawable.calendar),  modifier = Modifier.size(20.dp), tint = Color(0xffA0F000),contentDescription = "agenda")
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = label!!,
                    color = Color(0xffA0F000),
                    style = TextStyle(fontSize = 16.sp, fontFamily = outfitFamily, fontWeight = FontWeight.Medium)
                )
                Spacer(modifier = Modifier.width(100.dp))
                Text(
                    text = "${date.value}",
                    color = Color(0xffA0F000),
                    style = TextStyle(fontSize = 16.sp, fontFamily = outfitFamily, fontWeight = FontWeight.Medium)
                )
            }

        }
    }
}