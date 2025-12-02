package com.example.hcd_a3

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hcd_a3.ui.theme.HCD_A3Theme
import kotlinx.coroutines.launch
import kotlin.collections.forEach

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //val optionsVisible = remember { mutableStateOf(false) }

        setContent {
            HCD_A3Theme {
                val navController = rememberNavController()
                val orderViewModel: OrderViewModel = viewModel()

                NavHost(navController = navController, startDestination = "Login") {
                    composable("login") { backStackEntry -> LoginScreen(navController, backStackEntry)}
                    composable ("menu") { MenuScreen(navController,orderViewModel)}
                    composable ("account") { AccountScreen(navController)}
                    //composable ("test") { Tester(navController)}
                }
            }
        }
    }
}

//@Composable
//fun Tester(navController: NavController) {
//    Column {
//        // Surface with default (0dp) tonal elevation
//        Surface(
//            modifier = Modifier,
//            color = MaterialTheme.colorScheme.background
//        ) {
//            Text("Background Surface")
//        }
//
//        // Surface with 3dp tonal elevation (e.g., a Navigation Bar)
//        Surface(
//            modifier = Modifier,
//            tonalElevation = 3.dp
//        ) {
//            Text("Elevated Surface (e.g., Navigation Bar)")
//        }
//
//        // Surface with higher tonal elevation (e.g., a Card)
//        Surface(
//            modifier = Modifier,
//            tonalElevation = 8.dp
//        ) {
//            Text("Even More Elevated Surface (e.g., Card)")
//        }
//    }
//}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HCD_A3Theme {
        Greeting("Android")
    }
}

@Composable
fun ListItem(item: FoodItem) {
    val viewModel: LoginViewModel = viewModel()
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = item.name,
            fontSize = viewModel.largeText.sp,
            color = viewModel.oTextColour
        )
        Text(
            text = item.ingredients,
            fontSize = viewModel.smallText.sp,
            color = viewModel.oTextColour
        )
        Text(
            text = "$${item.price}",
            fontSize = viewModel.smallText.sp,
            color = viewModel.oTextColour
        )
    }
}

@Composable
fun MenuScreen(navController: NavController, persistent: OrderViewModel) { //backStackEntry: NavBackStackEntry
    //    Log.w(tag, persistent.selectedCategory)
//
//    val aaa = listOf(
//        FoodItem("cake", "chocolate base, chocolate icing", 10),
//        FoodItem("ice cream", "chocolate", 7))
//    val arrFinal = List(aaa.size, {0}).toMutableList()
//    for (i in 0 until arrFinal.size) {
//        arrFinal[i] = aaa[i].price
//    }
//    val arrFinal = List(7, {0})
//    arrFinal.forEach { item ->  Log.w(tag, item.toString())}
    //optional: get rid of order button when account is clicked
//    LaunchedEffect(backStackEntry) {
//        if (viewModel.accountVisible) viewModel.toggleAccount()
//    }

    val viewModel: LoginViewModel = viewModel()
    val optionsVisible = viewModel.optionsVisible
    val accountVisible = viewModel.accountVisible

    val displayedItems = when (persistent.selectedCategory) {
        "Desserts" -> persistent.desserts
        "Meals" -> persistent.meals
        "Entrees" -> persistent.entrees
        else -> persistent.drinks
    }
    val tag = "Debug"
    val itemList = when (persistent.selectedCategory) {
        "Desserts" -> persistent.dessertCount
        "Meals" -> persistent.mealCount
        "Entrees" -> persistent.entreeCount
        else -> persistent.drinkCount // fallback
    }
    Log.w(tag, "$itemList")
    Log.w(tag, "${persistent.entreeCount}")
    val configuration: Configuration = Resources.getSystem().configuration
    val screenWidth = configuration.screenWidthDp.dp
    val cartVisible = persistent.totalOrderCount.value > 0
    val cartExpanded = remember { mutableStateOf(false) }
    //change the amount of inset the order items have to shift by to display the cart icon when adding items
    val inset = 0.dp
    val rightInset by animateDpAsState(targetValue = if (cartVisible) inset else 0.dp)
    val offsetX by animateDpAsState(targetValue = if (cartExpanded.value) (-screenWidth * 0.8f) else 0.dp)

    Box(modifier = Modifier.offset(x = offsetX).fillMaxSize()) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                Surface(tonalElevation = 4.dp) {
                    Column {
                        // Space for phone's status bar
                        //Spacer class puts space between two UI elements. easier compared to padding when inside rows/boxes/columns
                        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
                        //TODO
                        val scrollState = rememberScrollState()
                        val scope = rememberCoroutineScope()
                        val width = if (scrollState.value > 0) 48.dp else 8.dp
                        var scrollOffset by remember { mutableStateOf(0f) }

                        //.height: make 48 the base height, largeText is defaulted to 16dp
                        //for surface, color = viewModel.topColour: idk why i coloured this, probably in case i decide to change it later :P
                        //z-index: // stays above the scroll
                        //The row contains the SCROLLING MENU (underlaps the back button)
                        //The spacer add space behind the button icons
                        //.height: //make to 36 as base

                        Box(
                            Modifier.fillMaxWidth().height((32 + viewModel.largeText).dp)
                                .background(color = viewModel.topColour)
                        ) {
                            //alternatively use this@Column.Animated...
                            androidx.compose.animation.AnimatedVisibility(visible = scrollState.value > 0, enter = fadeIn() + slideInHorizontally(), exit = fadeOut() + slideOutHorizontally(), modifier = Modifier.zIndex(1f)) {
                                Surface(color = viewModel.topColour, modifier = Modifier.align(Alignment.CenterStart)) {
                                    IconButton(onClick = { scope.launch { scrollOffset -= 150f; scrollState.animateScrollTo(scrollOffset.toInt().coerceAtLeast(0)) } }, modifier = Modifier.fillMaxHeight()) {
                                        Icon(Icons.AutoMirrored.Default.ArrowBack, tint = viewModel.arrowColour, contentDescription = "Back")
                                    }
                                }
                            }

                            Surface(color = viewModel.topColour, modifier = Modifier.align(Alignment.CenterEnd).zIndex(1f)) {
                                IconButton(onClick = { scope.launch { scrollOffset += 150f; scrollState.animateScrollTo(scrollOffset.toInt()) } }, modifier = Modifier.fillMaxHeight()) {
                                    Icon(Icons.AutoMirrored.Default.ArrowBack, modifier = Modifier.rotate(180f), tint = viewModel.arrowColour, contentDescription = "Back")
                                }
                            }

                            Row(modifier = Modifier.fillMaxWidth().horizontalScroll(scrollState).align(Alignment.CenterStart).padding(start = 0.dp, end = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp),) {
                                Spacer(modifier = Modifier.width(width))

                                persistent.menu.forEach {
                                        item ->
                                    val isSelected = item == persistent.selectedCategory
                                    val backgroundColour =
                                        if (isSelected) viewModel.buttonBackColour else viewModel.buttonColour
                                    val animatedColor by animateColorAsState(targetValue = backgroundColour)

                                    Box(modifier = Modifier.height((20 + viewModel.largeText).dp).background(color = animatedColor, shape = RoundedCornerShape(8.dp)).clickable { persistent.setCategory(item) }, contentAlignment = Alignment.Center) {
                                        Text(text = item, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp), textAlign = TextAlign.Center, fontSize = viewModel.largeText.sp, color = viewModel.oTextColour)
                                    }
                                }

                                Spacer(modifier = Modifier.width(width))
                            }
                        }
                    }
                }
            },
            bottomBar = {
                bottomBar(navController, viewModel, optionsVisible, accountVisible, persistent)
            },
            //I've wrapped it up in a row class to allow for the adjusting of space
            content = { innerPadding ->
                Row(modifier = Modifier.background(viewModel.secondBackColour).fillMaxWidth()) {
                    LazyColumn(modifier = Modifier.padding(innerPadding).padding(end = rightInset).weight(1f)) {
                        itemsIndexed(displayedItems) { index, item ->
                            Card(modifier = Modifier.fillMaxSize().padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 0.dp), colors = CardDefaults.cardColors(containerColor = viewModel.content, contentColor = viewModel.oTextColour)) {
                                Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min).padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    //COUNT for item
                                    // Distribute available width equally among boxes
                                    // Fixed height for each box
                                    Box(modifier = Modifier.weight(0.5f).fillMaxHeight(), contentAlignment = Alignment.Center) {
                                        Text(itemList[index].toString(), fontSize = viewModel.largeText.sp, color = viewModel.oTextColour)
                                    }
                                    //DESCRIPTION and PRICE for item
                                    Box(modifier = Modifier.weight(4f).fillMaxHeight()) {
            //                                    items(desserts) {
            //                                        ListItem(it)
            //                                    }
                                        ListItem(item)
                                    }
                                    //ACTION button ADD
                                    Box(modifier = Modifier.weight(1f).fillMaxHeight().clickable(onClick = { itemList[index]++ }), contentAlignment = Alignment.Center) {
            //                                    IconButton(
            //                                        onClick = { }
            //                                    ) {
                                        Icon(Icons.Default.Add, contentDescription = "Add")
            //                                    }
                                    }
                                    //ACTION button REMOVE
                                    Box(modifier = Modifier.weight(1f).fillMaxHeight().clickable(onClick = { if (itemList[index] > 0) itemList[index]-- }), contentAlignment = Alignment.Center) {
            //                                    IconButton(
            //                                        onClick = {
            //                                            if (activeCount[index] > 0) activeCount[index]--
            //                                        }
            //                                    ) {
                                        Icon(ImageVector.vectorResource(id = R.drawable.subtract), contentDescription = "Remove", modifier = Modifier.size(24.dp))
            //                                    }
                                    }
                                }
                            }
                        }
                    }

            //                AnimatedVisibility(visible = cartVisible) {
            //                    Column(
            //                        modifier = Modifier
            //                            .width(56.dp)
            //                            .fillMaxHeight()
            //                            .background(Color.Transparent),
            //                        verticalArrangement = Arrangement.Center,
            //                        horizontalAlignment = Alignment.CenterHorizontally
            //                    ) {
            //                        CartButton(count = totalOrderCount.value) {
            //                            cartExpanded.value = true
            //                        }
            //                    }
            //                }

                    val scrollState = rememberScrollState()

                    //scrollable column
                    Column(modifier = Modifier.width(36.dp).fillMaxHeight().background(Color.Transparent).padding(innerPadding)) {

                        Spacer(modifier = Modifier.height(4.dp))
                        //UP ARROW button
                        Box(modifier = Modifier.height(36.dp).fillMaxWidth()) {
                            Surface(color = viewModel.scrollButtonColour, shape = RoundedCornerShape(12.dp)) {
                                IconButton(onClick = { /**/ }) {
                                    Icon(modifier = Modifier.rotate(90f), imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Scroll Up", tint = viewModel.iconsColour)
                                }
                            }
                        }


                        Box(modifier = Modifier.fillMaxSize().weight(1f)) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(600.dp) // The total height of the scrollbar area
                                    .padding(horizontal = 6.dp, vertical = 4.dp) // Align with the padded Row above
                                    .background(viewModel.scrollColour)
                            ) {
                            }
                        }

                        //DOWN ARROW button
                        Box(modifier = Modifier.height(36.dp).fillMaxWidth()) {
                            Surface(color = viewModel.scrollButtonColour, shape = RoundedCornerShape(12.dp)) {
                                IconButton(onClick = { /**/ }) {
                                    Icon(modifier = Modifier.rotate(-90f), imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Scroll Down", tint = viewModel.iconsColour)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }

                //Order Screen
                if (cartExpanded.value) {
                    Box(
                        Modifier
                            //.align(Alignment.CenterEnd)
                            .fillMaxHeight()
                            .width(screenWidth * 0.8f)
                            .background(Color.White)
                    ) {
                        CartContent(
                            onClose = { cartExpanded.value = false }
                        )
                    }
                }
            }
        )
    }
}

//content = { innerPadding ->
//    //I've wrapped it up in a row class to allow for the adjusting of space
//    Row(modifier = Modifier.background(viewModel.secondBackColour).fillMaxWidth()) {
//        LazyColumn(modifier = Modifier.padding(innerPadding).padding(end = rightInset).weight(1f)) {
//            itemsIndexed(displayedItems) { index, item ->
//                Card(modifier = Modifier.fillMaxSize().padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 0.dp), colors = CardDefaults.cardColors(containerColor = viewModel.content, contentColor = viewModel.oTextColour)) {
//                    Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min).padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                        //COUNT for item
//                        // Distribute available width equally among boxes
//                        // Fixed height for each box
//                        Box(modifier = Modifier.weight(0.5f).fillMaxHeight(), contentAlignment = Alignment.Center) {
//                            Text(itemList[index].toString(), fontSize = viewModel.largeText.sp, color = viewModel.oTextColour)
//                        }
//                        //DESCRIPTION and PRICE for item
//                        Box(modifier = Modifier.weight(4f).fillMaxHeight()) {
////                                    items(desserts) {
////                                        ListItem(it)
////                                    }
//                            ListItem(item)
//                        }
//                        //ACTION button ADD
//                        Box(modifier = Modifier.weight(1f).fillMaxHeight().clickable(onClick = { itemList[index]++ }), contentAlignment = Alignment.Center) {
////                                    IconButton(
////                                        onClick = { }
////                                    ) {
//                            Icon(Icons.Default.Add, contentDescription = "Add")
////                                    }
//                        }
//                        //ACTION button REMOVE
//                        Box(modifier = Modifier.weight(1f).fillMaxHeight().clickable(onClick = { if (itemList[index] > 0) itemList[index]-- }), contentAlignment = Alignment.Center) {
////                                    IconButton(
////                                        onClick = {
////                                            if (activeCount[index] > 0) activeCount[index]--
////                                        }
////                                    ) {
//                            Icon(ImageVector.vectorResource(id = R.drawable.subtract), contentDescription = "Remove", modifier = Modifier.size(24.dp))
////                                    }
//                        }
//                    }
//                }
//            }
//        }
//
////                AnimatedVisibility(visible = cartVisible) {
////                    Column(
////                        modifier = Modifier
////                            .width(56.dp)
////                            .fillMaxHeight()
////                            .background(Color.Transparent),
////                        verticalArrangement = Arrangement.Center,
////                        horizontalAlignment = Alignment.CenterHorizontally
////                    ) {
////                        CartButton(count = totalOrderCount.value) {
////                            cartExpanded.value = true
////                        }
////                    }
////                }
//        //scrollable column
//        Column(modifier = Modifier.width(36.dp).fillMaxHeight().background(Color.Transparent).padding(innerPadding)) {
//            Spacer(modifier = Modifier.height(4.dp))
//            //UP ARROW button
//            Box(modifier = Modifier.height(36.dp).fillMaxWidth()) {
//                Surface(color = viewModel.buttonColour, shape = RoundedCornerShape(12.dp)) {
//                    IconButton(onClick = { /**/ }) {
//                        Icon(modifier = Modifier.rotate(90f), imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Scroll Up", tint = viewModel.iconsColour)
//                    }
//                }
//            }
//            Box(modifier = Modifier.fillMaxSize().weight(1f)) {
//            }
//
//            //DOWN ARROW button
//            Box(modifier = Modifier.height(36.dp).fillMaxWidth()) {
//                Surface(color = viewModel.buttonColour, shape = RoundedCornerShape(12.dp)) {
//                    IconButton(onClick = { /**/ }) {
//                        Icon(modifier = Modifier.rotate(-90f), imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Scroll Down", tint = viewModel.iconsColour)
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.height(4.dp))
//        }
//    }
//}

//    //Order Screen
//    if (cartExpanded.value) {
//        Box(
//            Modifier
//                //.align(Alignment.CenterEnd)
//                .fillMaxHeight()
//                .width(screenWidth * 0.8f)
//                .background(Color.White)
//        ) {
//            CartContent(
//                onClose = { cartExpanded.value = false }
//            )
//        }
//    }


//@Composable
//fun CartButton(count: Int, onClick: () -> Unit) {
//    val viewModel: LoginViewModel = viewModel()
////    Box(
////        modifier = Modifier
////            .padding(4.dp)
////            .clickable { onClick() }
////            .background(Color.White, RoundedCornerShape(8.dp))
////    ) {
////        Column(horizontalAlignment = Alignment.CenterHorizontally) {
////            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
////            if (count > 0) {
////                Text(count.toString(), color = Color.Red, fontSize = viewModel.largeText.sp)
////            }
////        }
////    }
//    Surface(
//        modifier = Modifier
//            .clickable { onClick() },
//            //.background(Color.White, RoundedCornerShape(8.dp))
//        shape = RoundedCornerShape(8.dp),
//        color = Color.White
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.padding(12.dp)) {
//            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
//            if (count > 0) {
//                Text(count.toString(), color = Color.Red, fontSize = viewModel.largeText.sp)
//            }
//        }
//    }
//}

@Composable
fun CartContent(onClose: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Your Order", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))

        // TODO: list chosen items here

        Spacer(Modifier.height(16.dp))
        Text("Swipe right or tap back to close")
        Spacer(Modifier.height(8.dp))

        IconButton(onClick = onClose) {
            Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Close Cart")
        }
    }
}

@Composable
fun bottomBar(navController: NavController, viewModel: LoginViewModel, optionsVisible: Boolean, accountVisible: Boolean, persistent: OrderViewModel) {
    Box(modifier = Modifier.background(viewModel.bottomColour).fillMaxWidth()) {
        //order button, separated from the row of other icons
        //place the button in the middle
        AnimatedVisibility(modifier = Modifier.align(Alignment.Center), visible = !optionsVisible && !accountVisible) {
            //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
            Surface(modifier = Modifier.zIndex(1f).offset(y = -(30).dp), color = viewModel.oButtonColour, shape = RoundedCornerShape(50.dp)) {
                //order
                IconButton(onClick = { /**/ }, modifier = Modifier.size(100.dp)) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = persistent.totalOrderCount.value.toString(), fontSize = viewModel.largeText.sp, color = viewModel.oTextHighlightColour, modifier = Modifier.zIndex(1f).offset(y = (-20).dp),)
                        Column(modifier = Modifier.align(Alignment.Center)) {
                            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Order", tint = viewModel.iconsColour, modifier = Modifier.size(50.dp))
                            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                                Text(text = "$${persistent.totalAmountCount.value}", fontSize = viewModel.largeText.sp, color = viewModel.oTextHighlightColour)
                            }
                        }
                    }
                }
            }
        }

        val scrollState = rememberScrollState()
        val scope = rememberCoroutineScope()
        var scrollOffset by remember { mutableStateOf(0f) }

        Box(modifier = Modifier.zIndex(1f).matchParentSize()) {
            AnimatedVisibility(
                visible = scrollState.value > 0,
                enter = fadeIn() + slideInHorizontally(),
                exit = fadeOut() + slideOutHorizontally(),
            ) {
                Surface(
                    color = viewModel.topColour,
                    modifier = Modifier.align(Alignment.CenterStart).padding(4.dp),
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
                            tint = viewModel.arrowColour,
                            contentDescription = "Back"
                        )
                    }
                }
            }
        }

        //begin at start
        Row(
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.Center)
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            settings(changeColour = false, scrollState = scrollState)
            AnimatedVisibility(visible = !optionsVisible) {
                Row {
                    Spacer(modifier = Modifier.width(6.dp))
                    //Home
                    Surface(
                        color = viewModel.buttonColour,
                        shape = RoundedCornerShape(12.dp)
                    ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                    {
                        IconButton(
                            onClick = { navController.navigate("login") } // navigate to home
                        ) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Home",
                                tint = viewModel.iconsColour,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }

                    //make space for order button outside of row
                    Spacer(modifier = Modifier.width(152.dp))

                    Surface(
                        color = viewModel.buttonColour,
                        shape = RoundedCornerShape(12.dp)
                    ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                    {
                        IconButton(
                            onClick = { navController.navigate("menu") }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.menu),
                                contentDescription = "Menu",
                                tint = viewModel.iconsColour,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Surface(
                        color = viewModel.buttonColour,
                        shape = RoundedCornerShape(12.dp)
                    ) //acts as a visual container for other UI elements and automatically handles aspects like background color, elevation, shape, and content color
                    {
                        IconButton(
                            onClick = { navController.navigate("account") }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Account",
                                tint = viewModel.iconsColour,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                }
            }
        }

        Box(modifier = Modifier.zIndex(1f).matchParentSize()) {
            AnimatedVisibility(
                visible = scrollState.value <= scrollState.maxValue - 10 && (viewModel.themeOption || viewModel.languageOption),
                enter = fadeIn() + slideInHorizontally(),
                exit = fadeOut() + slideOutHorizontally(),
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Surface(
                    color = viewModel.topColour,
                    modifier = Modifier.padding(4.dp),
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
                            tint = viewModel.arrowColour,
                            contentDescription = "Back",
                            modifier = Modifier.rotate(180f)
                        )
                    }
                }
            }
        }
    }
}

