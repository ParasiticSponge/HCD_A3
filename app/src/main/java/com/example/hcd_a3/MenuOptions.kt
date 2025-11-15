package com.example.hcd_a3

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    val optionsVisible = remember { mutableStateOf(false) }
    val screenReader = remember { mutableStateOf(false) }
    val languageOption = remember { mutableStateOf(false) }
    val languageSelected = remember { mutableStateOf("English") }
    val size = remember { mutableStateOf(0) }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(255, 243, 200))){
        Box(modifier = Modifier
                .fillMaxSize()
                .weight(4f),
            //contentAlignment = Alignment.TopCenter
        ) {
            val res_plate = painterResource(id = R.drawable.australian_plate)
            Image(
                painter = res_plate,
                contentDescription = "Plate background",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    //.fillMaxSize(0.6f)
                    .offset(y = 160.dp)
                    .size(width = res_plate.intrinsicSize.width.dp * 0.2f,
                        height = res_plate.intrinsicSize.height.dp * 0.2f),
                contentScale = ContentScale.Fit
            )

            val res_attica = painterResource(id = R.drawable.atticanewlogo)
            Image(
                painter = res_attica,
                contentDescription = "Attica logo",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    //.offset()
                    .size(width = res_attica.intrinsicSize.width.dp * 0.8f,
                        height = res_attica.intrinsicSize.height.dp * 0.8f)
                    //.fillMaxSize(1f)
            )
//
            val res_herb1 = painterResource(id = R.drawable.herb_top)
            Image(
                painter = res_herb1,
                contentDescription = "Top herb",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    //.padding(start = 16.dp, top = 16.dp)
                    .size(width = res_herb1.intrinsicSize.width.dp * 2.2f,
                        height = res_herb1.intrinsicSize.height.dp * 2.2f)
                    .offset(x = 35.dp)
            )
//
            val res_herb2 = painterResource(id = R.drawable.herb_bottom)
            Image(
                painter = res_herb2,
                contentDescription = "Bottom herb",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(width = res_herb2.intrinsicSize.width.dp * 2.2f,
                        height = res_herb2.intrinsicSize.height.dp * 2.2f)
            )
        }
        Box(modifier = Modifier
            .fillMaxSize()
                .weight(3f),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Log in as:", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                Button(onClick = { navController.navigate("menu") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(255, 205, 0), // Sets the background color for the enabled state
                    disabledContainerColor = Color.Gray // Sets the background color for the disabled state) {
                ))
                {
                    Text("Account", color = Color.Black)
                }
                Row {
                    Text("Don't have an account? ", style = MaterialTheme.typography.bodySmall)
                    Text("Create one!", style = MaterialTheme.typography.bodySmall, textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable{ navController.navigate("") })
                }
                Row {
                    Text("See the ", style = MaterialTheme.typography.bodySmall)
                    Text("benefits ", style = MaterialTheme.typography.bodySmall, textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable{ navController.navigate("")})
                    Text("of an account", style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.width(48.dp))
                Button(onClick = { navController.navigate("menu") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(255, 205, 0),
                        disabledContainerColor = Color.Gray
                    )) {
                    Text("Guest", color = Color.Black)
                }
            }

        }
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Spacer(modifier = Modifier.width(12.dp))

                AnimatedVisibility(visible = !languageOption.value) {
                    //Settings button
                    Surface(
                        color = Color(255, 205, 0),
                        shape = RoundedCornerShape(12.dp)
                    ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                    {
                        IconButton(
                            onClick = { optionsVisible.value = !optionsVisible.value }
                        ) {
                            Icon(
                                Icons.Default.Settings,
                                contentDescription = "Settings",
                                tint = Color(103, 52, 25),
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }
                }

                AnimatedVisibility(visible = optionsVisible.value) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

                        //Language option
                        Surface(
                            color = Color(255, 205, 0),
                            shape = RoundedCornerShape(12.dp)
                        ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                        {
                            IconButton(
                                onClick = { languageOption.value = !languageOption.value }
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.globe),
                                    contentDescription = "Language",
                                    tint = Color(103, 52, 25)
                                )
                            }
                        }

                        //ScreenReader option
                        Surface(
                            color = Color(255, 205, 0),
                            shape = RoundedCornerShape(12.dp)
                        ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                        {
                            IconButton(
                                onClick = { /**/ }
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.lips4),
                                    contentDescription = "Screen Reader",
                                    tint = Color(103, 52, 25)
                                )
                            }
                        }

                        //Increase Size option
                        Surface(
                            color = Color(255, 205, 0),
                            shape = RoundedCornerShape(12.dp)
                        ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                        {
                            IconButton(
                                onClick = { /**/ }
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.size_up),
                                    contentDescription = "Settings",
                                    tint = Color(103, 52, 25)
                                )
                            }
                        }

                        //Decrease Size option
                        Surface(
                            color = Color(255, 205, 0),
                            shape = RoundedCornerShape(12.dp)
                        ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                        {
                            IconButton(
                                onClick = { /**/ }
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.size_down),
                                    contentDescription = "Settings",
                                    tint = Color(103, 52, 25)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}