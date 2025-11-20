package com.example.hcd_a3

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController, backStackEntry: NavBackStackEntry) {
    val viewModel: LoginViewModel = viewModel()
    val largeText = viewModel.largeText
    val smallText = viewModel.smallText

    LaunchedEffect(backStackEntry) {
        if (viewModel.optionsVisible) {
            viewModel.toggleOptions()
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(viewModel.mainBackColour)){
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
                Text(text = "Log in as:", fontSize = largeText.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), color=viewModel.oTextHighlightColour)
                Button(onClick = { navController.navigate("menu") },
                    colors = ButtonDefaults.buttonColors(containerColor = viewModel.buttonColour, // Sets the background color for the enabled state
                    disabledContainerColor = Color.Gray // Sets the background color for the disabled state) {
                ))
                {
                    Text("Account", color = viewModel.oTextColour, fontSize = largeText.sp)
                }
                Row {
                    Text("Don't have an account? ", fontSize = smallText.sp, color = viewModel.oTextHighlightColour)
                    Text("Create one!", fontSize = smallText.sp, textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable{ navController.navigate("") }, color = viewModel.oUnderlineTextColour)
                }
                Row {
                    Text("See the ", fontSize = smallText.sp, color = viewModel.oTextHighlightColour)
                    Text("benefits ", fontSize = smallText.sp, textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable{ navController.navigate("")}, color = viewModel.oUnderlineTextColour)
                    Text("of an account", fontSize = smallText.sp, color = viewModel.oTextHighlightColour)
                }
                Spacer(modifier = Modifier.width(48.dp))
                Button(onClick = { navController.navigate("menu") },
                    colors = ButtonDefaults.buttonColors(containerColor = viewModel.buttonColour,
                        disabledContainerColor = Color.Gray
                    )) {
                    Text("Guest", color = viewModel.oTextColour, fontSize = largeText.sp)
                }
            }

        }
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f),
            contentAlignment = Alignment.CenterStart
        ){}
        BottomAppBar(
            containerColor = viewModel.mainBackColour) {
            // SCROLLING MENU (underlaps the back button)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                    //.align(Alignment.CenterStart)
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Spacer(modifier = Modifier.width(24.dp))
                settings()
            }
        }
    }
}

@Composable
fun settings(viewModel: LoginViewModel = viewModel()) {
    val languageOption = viewModel.languageOption
    val optionsVisible = viewModel.optionsVisible
    val screenReader = viewModel.screenReader

//    val tag = "Debug"
//    Log.w(tag, "${viewModel.optionsVisible}")

    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        AnimatedVisibility(visible = !languageOption) {
            //Settings button
            Surface(
                color = if (optionsVisible) viewModel.buttonBackColour else viewModel.buttonColour,
                shape = RoundedCornerShape(12.dp)
            ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
            {
                IconButton(
                    onClick = { viewModel.toggleOptions() }
                ) {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = viewModel.iconsColour,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
        }

        AnimatedVisibility(visible = optionsVisible) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

                //Language option
                Surface(
                    color = viewModel.buttonColour,
                    shape = RoundedCornerShape(12.dp)
                ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                {
                    IconButton(
                        onClick = { viewModel.toggleLanguage() }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.globe),
                            contentDescription = "Language",
                            tint = viewModel.iconsColour
                        )
                    }
                }

                AnimatedVisibility(visible = !languageOption) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        //ScreenReader option
                        Surface(
                            color = if (screenReader) viewModel.buttonBackColour else viewModel.buttonColour,
                            shape = RoundedCornerShape(12.dp)
                        ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                        {
                            IconButton(
                                onClick = { viewModel.toggleReader() }
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.lips4),
                                    contentDescription = "Screen Reader",
                                    tint = viewModel.iconsColour
                                )
                            }
                        }

                        //Increase Size option
                        Surface(
                            color = viewModel.buttonColour,
                            shape = RoundedCornerShape(12.dp)
                        ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                        {
                            IconButton(
                                onClick = { if (viewModel.largeText < 24) viewModel.increaseTextSize() }
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.size_up),
                                    contentDescription = "Settings",
                                    tint = viewModel.iconsColour
                                )
                            }
                        }

                        //Decrease Size option
                        Surface(
                            color = viewModel.buttonColour,
                            shape = RoundedCornerShape(12.dp)
                        ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                        {
                            IconButton(
                                onClick = { if (viewModel.largeText > 16) viewModel.decreaseTextSize() }
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.size_down),
                                    contentDescription = "Settings",
                                    tint = viewModel.iconsColour
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}