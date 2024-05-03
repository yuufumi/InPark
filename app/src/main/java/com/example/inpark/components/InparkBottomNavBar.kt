package com.example.inpark.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inpark.R

data class BottomNavigationItem(
    val title: String,
    val selectedIcon:Int,
    val unselectedIcon:Int,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InparkBottomNavBar(navController: NavController,color: Color){
    val items = listOf(

        BottomNavigationItem(
            title = "home",
            selectedIcon = R.drawable.selected_home,
            unselectedIcon = R.drawable.unselected_home,
            hasNews = true,
            badgeCount = null,
        ),
        BottomNavigationItem(
            title = "maps",
            selectedIcon =  R.drawable.selected_maps,
            unselectedIcon = R.drawable.unselected_maps,
            hasNews = true,
            badgeCount = null,
        ),
        BottomNavigationItem(
            title = "bookings",
            selectedIcon =  R.drawable.selected_booking,
            unselectedIcon =  R.drawable.unselected_booking,
            hasNews = true,
            badgeCount = null,
        ),
        BottomNavigationItem(
            title = "profile",
            selectedIcon = R.drawable.selected_profile,
            unselectedIcon = R.drawable.unselected_profile ,
            //unselectedIcon = Icon(painter = painterResource(id = R.drawable.unselected_profile), contentDescription = "profile"),
            hasNews = true,
            badgeCount = null,
        ),
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    NavigationBar(containerColor = color, contentColor = color) {
        items.forEachIndexed{index, item ->
            NavigationBarItem(selected = selectedItemIndex == index,
            onClick = {
                selectedItemIndex = index
                navController.navigate(item.title)
            }, modifier = Modifier.background(color = color),
            icon = {
                    if(selectedItemIndex == index) {
                        Icon(painter = painterResource(id = item.selectedIcon), modifier = Modifier.size(26.dp), tint = Color(0xff002020), contentDescription = item.title)
                    }else{
                        Icon(painter = painterResource(id = item.unselectedIcon), modifier = Modifier.size(26.dp), tint = Color(0xfffffff0), contentDescription = item.title)
                    }
                }
        )
        }

    }

}