package com.example.inpark.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inpark.R
import com.example.inpark.outfitFamily
import com.example.inpark.utils.getLocationText
import com.example.inpark.viewModels.LocationViewModel

@Composable
fun LocationInfo(viewModel:LocationViewModel){
    val location by viewModel.location.observeAsState()
    val context = LocalContext.current
    Row{
        Image(painter = painterResource(id = R.drawable.unselected_maps), modifier = Modifier.size(30.dp), contentDescription = "location")
        Column {
            Text(text="Location", style = TextStyle(fontFamily = outfitFamily, color = Color(0x7fFFFFf0), fontSize = 12.sp))
            if(location!= null){
                Text(text= getLocationText(location!!.latitude,location!!.longitude,context)!!, style = TextStyle(fontFamily = outfitFamily, color = Color(0xffFFFFF0), fontSize = 12.sp))
            } else {
                Text(
                    text = "Loading location",
                    style = TextStyle(
                        fontFamily = outfitFamily,
                        color = Color(0xffFFFFF0),
                        fontSize = 12.sp
                    )
                )
            }
            }
}
}