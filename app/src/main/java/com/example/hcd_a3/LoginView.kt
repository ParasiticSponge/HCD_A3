package com.example.hcd_a3

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, backStackEntry: NavBackStackEntry, viewModel: LoginViewModel) {
    //val viewModel: LoginViewModel = viewModel()
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
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .weight(4f),
            //contentAlignment = Alignment.TopCenter
        ) {
            val resPlate = painterResource(id = R.drawable.australian_plate)
            Image(
                painter = resPlate,
                contentDescription = "Plate background",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxSize(0.6f)
                    .offset(y = 160.dp)
                    .size(width = resPlate.intrinsicSize.width.dp * 0.2f,
                        height = resPlate.intrinsicSize.height.dp * 0.2f),
                contentScale = ContentScale.Fit
            )

            val resAttica = painterResource(id = R.drawable.atticanewlogo)
            Image(
                painter = resAttica,
                contentDescription = "Attica logo",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(width = resAttica.intrinsicSize.width.dp * 0.8f,
                        height = resAttica.intrinsicSize.height.dp * 0.8f)
                    .fillMaxSize(1f)
            )
//
            val resHerb1 = painterResource(id = R.drawable.herb_top)
            Image(
                painter = resHerb1,
                contentDescription = "Top herb",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    //.padding(start = 16.dp, top = 16.dp)
                    .size(width = resHerb1.intrinsicSize.width.dp * 2.2f,
                        height = resHerb1.intrinsicSize.height.dp * 2.2f)
                    .offset(x = 35.dp)
                    .fillMaxSize(0.7f)
            )
//
            val resHerb2 = painterResource(id = R.drawable.herb_bottom)
            Image(
                painter = resHerb2,
                contentDescription = "Bottom herb",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(width = resHerb2.intrinsicSize.width.dp * 2.2f,
                        height = resHerb2.intrinsicSize.height.dp * 2.2f)
                    .fillMaxSize(0.7f)
            )
        }

        //Login
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

        val scrollState = rememberScrollState()
        val scope = rememberCoroutineScope()
        var scrollOffset by remember { mutableStateOf(0f) }
        BottomAppBar(
            containerColor = viewModel.mainBackColour
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.zIndex(1f).matchParentSize()) {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = scrollState.value > 0 && viewModel.optionsVisible,
                        enter = fadeIn() + slideInHorizontally(),
                        exit = fadeOut() + slideOutHorizontally(),
                    ) {
                        Surface(
                            color = viewModel.secondBackColour,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            IconButton(onClick = {
                                scope.launch {
                                    scrollOffset -= 150f; scrollState.animateScrollTo(
                                    scrollOffset.toInt().coerceAtLeast(0)
                                )
                                }
                            }, modifier = Modifier.fillMaxHeight()) {
                                Icon(
                                    Icons.AutoMirrored.Default.ArrowBack,
                                    tint = viewModel.iconsColour,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    }
                }

                // SCROLLING MENU (underlaps the other buttons)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(scrollState),
                    //.align(Alignment.CenterStart)
                    //horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(24.dp))
                    settings(viewModel = viewModel, changeColour = true, scrollState = scrollState)
                }

                Box(modifier = Modifier.zIndex(1f).matchParentSize()) {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = scrollState.value < scrollState.maxValue && viewModel.optionsVisible,
                        enter = fadeIn() + slideInHorizontally(),
                        exit = fadeOut() + slideOutHorizontally(),
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Surface(
                            color = viewModel.secondBackColour,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            IconButton(onClick = {
                                scope.launch {
                                    scrollOffset += 150f; scrollState.animateScrollTo(
                                    scrollOffset.toInt().coerceAtLeast(0)
                                )
                                }
                            }, modifier = Modifier.fillMaxHeight()) {
                                Icon(
                                    Icons.AutoMirrored.Default.ArrowBack,
                                    tint = viewModel.iconsColour,
                                    contentDescription = "Back",
                                    modifier = Modifier.rotate(180f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun settings(viewModel: LoginViewModel, changeColour: Boolean, scrollState: ScrollState) {
    val languageOption = viewModel.languageOption
    val themeOption = viewModel.themeOption
    val optionsVisible = viewModel.optionsVisible
    val screenReader = viewModel.screenReader
    val textColour = if (changeColour) viewModel.oTextHighlightColour else viewModel.oTextColour

//    val tag = "Debug"
//    Log.w(tag, "${viewModel.optionsVisible}")

    AnimatedVisibility(visible = !optionsVisible) {
        //Settings button

        Surface(
            color = viewModel.buttonColour,
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
        Text("", fontSize = viewModel.smallText.sp, color = textColour)
    }

    //State 1: Main Settings
    AnimatedVisibility(visible = optionsVisible && !languageOption && !themeOption) {
        Row(
            //horizontalArrangement = Arrangement.spacedBy(12.dp)
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Settings button
            Column(
                modifier = Modifier
                    .padding(end = 12.dp)
            ) {
                Surface(
                    color = viewModel.buttonBackColour,
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
                Text("", fontSize = viewModel.smallText.sp, color = textColour)
            }
            //Language option
            Column(
                modifier = Modifier
                    .padding(end = 12.dp)
            ) {
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
                Text("Language", fontSize = viewModel.smallText.sp, color = textColour)
            }
            //Theme option
            Column(
                modifier = Modifier
                    .padding(end = 12.dp)
            ) {
                Surface(
                    color = viewModel.buttonColour,
                    shape = RoundedCornerShape(12.dp)
                ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                {
                    IconButton(
                        onClick = { viewModel.toggleTheme() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Build,
                            contentDescription = "Themes",
                            tint = viewModel.iconsColour
                        )
                    }
                }
                Text("Theme", fontSize = viewModel.smallText.sp, color = textColour)
            }
            //ScreenReader option
            Column(
                modifier = Modifier
                    .padding(end = 12.dp)
            ) {
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
                Text(
                    "Reader",
                    fontSize = viewModel.smallText.sp,
                    color = textColour
                )
            }
            //Increase Size option
            Column(
                modifier = Modifier
                    .padding(end = 12.dp)
            ) {
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
                Text(
                    "Size Up",
                    fontSize = viewModel.smallText.sp,
                    color = textColour
                )
            }
            //Decrease Size option
            Column(
                modifier = Modifier
                    .padding(end = 12.dp)
            ) {
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
                Text(
                    "Size Down",
                    fontSize = viewModel.smallText.sp,
                    color = textColour
                )
            }
        }
    }

    //State 2: Language Options
    AnimatedVisibility(visible = optionsVisible && languageOption) {
        Row(
            //horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Language option
            Column {
                Surface(
                    color = viewModel.buttonBackColour,
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
                Text("Language", fontSize = viewModel.smallText.sp, color = textColour)
            }
        }
    }

    //State 3: Theme Options
    AnimatedVisibility(visible = optionsVisible && themeOption) {
        Row(
            //horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Theme option
            Column(modifier = Modifier
                .padding(end = 12.dp)
            ) {
                Surface(
                    color = viewModel.buttonBackColour,
                    shape = RoundedCornerShape(12.dp)
                ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                {
                    IconButton(
                        onClick = { viewModel.toggleTheme() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Build,
                            contentDescription = "Themes",
                            tint = viewModel.iconsColour
                        )
                    }
                }
                Text("Theme", fontSize = viewModel.smallText.sp, color = textColour)
            }
            //Default
            Column(
                modifier = Modifier
                    .padding(end = 12.dp)
            ) {
                Surface(
                    color = if (screenReader) viewModel.buttonBackColour else viewModel.buttonColour,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.size(47.5.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.theme_default),
                            contentDescription = "Default Theme",
                            contentScale = ContentScale.Crop, // ensures the image fills the shape
                            modifier = Modifier.matchParentSize()
                                .clickable(onClick = { viewModel.changeScheme("default") })
                        )
                        Text(
                            text = "Tt",
                            color = Color.Black,
                            fontSize = viewModel.largeText.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                Text(
                    "Default",
                    fontSize = viewModel.smallText.sp,
                    color = textColour
                )
            }

            //Light
            Column(
                modifier = Modifier
                //.weight(1f)
                    .padding(end = 12.dp)
            ) {
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                {
                    IconButton(
                        onClick = { viewModel.changeScheme("light") }
                    ) {
                        Text("Tt", color = Color.Black)
                    }
                }
                Text("Light", fontSize = viewModel.smallText.sp, color = textColour)
            }

            //Dark
            Column(
                modifier = Modifier
                //.weight(1f)
                    .padding(end = 12.dp)
            ) {
                Surface(
                    color = Color(43, 43, 43),
                    shape = RoundedCornerShape(12.dp)
                ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                {
                    IconButton(
                        onClick = { viewModel.changeScheme("dark") }
                    ) {
                        Text("Tt", color = Color.White)
                    }
                }
                Text("Dark", fontSize = viewModel.smallText.sp, color = textColour)
            }

            //Inverted
            Column(
                modifier = Modifier
                //.weight(1f)
                    .padding(end = 12.dp)
            ) {
                Surface(
                    color = if (screenReader) viewModel.buttonBackColour else viewModel.buttonColour,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.size(47.5.dp) // or whatever size you want
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.theme_inverted),
                            contentDescription = "Inverted Theme",
                            contentScale = ContentScale.Crop, // ensures the image fills the shape
                            modifier = Modifier.matchParentSize()
                                .clickable(onClick = { viewModel.changeScheme("inverted") })
                        )
                        Text(
                            text = "Tt",
                            color = Color.White,
                            fontSize = viewModel.largeText.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                Text(
                    "Inverted",
                    fontSize = viewModel.smallText.sp,
                    color = textColour
                )
            }

            //Increase Contrast option
            Column(
                modifier = Modifier
                //.weight(1f)
                    .padding(end = 12.dp)
            ) {
                Surface(
                    color = viewModel.buttonColour,
                    shape = RoundedCornerShape(12.dp)
                ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                {
                    IconButton(
                        onClick = { /**/ }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Settings",
                            tint = viewModel.iconsColour,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
                Text(
                    "Contrast",
                    fontSize = viewModel.smallText.sp,
                    color = textColour
                )
            }

            //Decrease Contrast option
            Column(
                modifier = Modifier
                //.weight(1f)
                    .padding(end = 12.dp)
            ) {
                Surface(
                    color = viewModel.buttonColour,
                    shape = RoundedCornerShape(12.dp)
                ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                {
                    IconButton(
                        onClick = { /**/ }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Settings",
                            tint = viewModel.iconsColour,
                            modifier = Modifier.size(48.dp).rotate(180f)
                        )
                    }
                }
                Text(
                    "",
                    fontSize = viewModel.smallText.sp,
                    color = textColour
                )
            }
        }
    }
}