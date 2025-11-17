package com.example.hcd_a3

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hcd_a3.ui.theme.HCD_A3Theme
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
                NavHost(navController = navController, startDestination = "Login") {
                    composable("login") { LoginScreen(navController)}
                    composable ("menu") { MenuScreen(navController)}
                    composable ("test") { Tester(navController)}
                }
            }
        }
    }
}

@Composable
fun Tester(navController: NavController) {
    Column {
        // Surface with default (0dp) tonal elevation
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background
        ) {
            Text("Background Surface")
        }

        // Surface with 3dp tonal elevation (e.g., a Navigation Bar)
        Surface(
            modifier = Modifier,
            tonalElevation = 3.dp
        ) {
            Text("Elevated Surface (e.g., Navigation Bar)")
        }

        // Surface with higher tonal elevation (e.g., a Card)
        Surface(
            modifier = Modifier,
            tonalElevation = 8.dp
        ) {
            Text("Even More Elevated Surface (e.g., Card)")
        }
    }
}
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
            fontSize = viewModel.largeText.sp
        )
        Text(
            text = item.ingredients,
            fontSize = viewModel.smallText.sp
        )
        Text(
            text = "$${item.price}",
            fontSize = viewModel.smallText.sp
        )
    }
}

@Composable
fun MenuScreen(navController: NavController)/*desserts: List<FoodItem>, menu: List<String>)*/ {
    val viewModel: LoginViewModel = viewModel()

    val desserts = listOf(
        FoodItem("cake", "chocolate base, chocolate icing", 10),
        FoodItem("ice cream", "chocolate", 7))
    val meals = listOf(
        FoodItem("ramen", "egg, ramen noodles", 12),
        FoodItem("pork bowl", "pork, seasoning", 15))
    val drinks = listOf(
        FoodItem("wine", "grapes", 20),
        FoodItem("Coca-Cola", "", 5))
    val menu = listOf("Meals", "Dessert", "Drinks")

    val dessertCount = remember { mutableStateListOf(*Array(desserts.size) { 0 }) }
    val mealCount = remember { mutableStateListOf(*Array(desserts.size) { 0 }) }
    val drinkCount = remember { mutableStateListOf(*Array(desserts.size) { 0 }) }
    val totalOrderCount = remember { derivedStateOf { dessertCount.sum() + mealCount.sum() + drinkCount.sum() } }

    val selectedCategory = remember { mutableStateOf("Meals") }
    val displayedItems = when (selectedCategory.value) {
        "Dessert" -> desserts
        "Meals" -> meals
        else -> drinks
    }

    fun getActiveCount(): SnapshotStateList<Int> {
        return when (selectedCategory.value) {
            "Dessert" -> dessertCount
            "Meals" -> mealCount
            "Drinks" -> drinkCount
            else -> dessertCount // fallback
        }
    }
    val activeCount = getActiveCount()

    val mainColour = Color(0, 162, 232)
    val highlightColour = Color.White

    val configuration: Configuration = Resources.getSystem().configuration
    val screenWidth = configuration.screenWidthDp.dp

    val cartVisible = totalOrderCount.value > 0
    val cartExpanded = remember { mutableStateOf(false) }

    //change the amount of inset the order items have to shift by to display the cart icon when adding items
    var inset = 0.dp
    val rightInset by animateDpAsState(
        targetValue = if (cartVisible) inset else 0.dp
    )
    val offsetX by animateDpAsState(
        targetValue = if (cartExpanded.value) (-screenWidth * 0.8f) else 0.dp
    )

    Box(modifier = Modifier
        .offset(x = offsetX)
        .fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Surface(
                    tonalElevation = 4.dp
                ) {
                    Column {
                        // Respect status bar
                        Spacer(         //Spacer class puts space between two UI elements. easier compared to padding when inside rows/boxes/columns
                            Modifier.windowInsetsTopHeight(WindowInsets.statusBars)
                        )

                        Box(
                            Modifier
                                .fillMaxWidth()
                                //make 48 the base height, largeText is defaulted to 16dp
                                .height((32 + viewModel.largeText).dp)
                                .background(color = mainColour)
                        ) {

                            Surface(color = mainColour,
                                modifier = Modifier
                                    .zIndex(1f)) // stays above the scroll
                            {
                                // BACK BUTTON (fixed position)
                                IconButton(
                                    onClick = { navController.popBackStack() }, // navigate to home screen by going back in the stack
                                    modifier = Modifier
                                        .align(Alignment.CenterStart)
                                        .fillMaxHeight()
                                ) {
                                    Icon(
                                        Icons.AutoMirrored.Default.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }

                            // SCROLLING MENU (underlaps the back button)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState())
                                    .align(Alignment.CenterStart)
                                    .padding(start = 0.dp, end = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Spacer(modifier = Modifier.width(48.dp)) // space behind back icon
                                menu.forEach { item ->
                                    val isSelected = item == selectedCategory.value
                                    val backgroundColour =
                                        if (isSelected) highlightColour else mainColour
                                    val animatedColor by animateColorAsState(targetValue = backgroundColour)

                                    Box(
                                        modifier = Modifier
                                            //.fillMaxSize()
                                            //make to 36 as base
                                            .height((20 + viewModel.largeText).dp)
                                            .background(
                                                color = animatedColor,
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                            .clickable {
                                                selectedCategory.value = item
                                            },
                                        //.padding(horizontal = 12.dp, vertical = 8.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = item,
                                            modifier = Modifier
                                                .padding(horizontal = 12.dp, vertical = 8.dp),
                                            textAlign = TextAlign.Center,
                                            fontSize = viewModel.largeText.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            },
            bottomBar = {
                // SCROLLING MENU (underlaps the back button)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .align(Alignment.CenterStart)
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    settings()
                }
            }
        )
        { innerPadding ->
            //I've wrapped it up in a row class to allow for the adjusting of space
            Row(modifier = Modifier.fillMaxWidth()) {
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(end = rightInset)
                        .weight(1f)
                ) {
                    itemsIndexed(displayedItems) { index, item ->
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 0.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(IntrinsicSize.Min)
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                //COUNT for item
                                Box(
                                    modifier = Modifier
                                        .weight(0.5f) // Distribute available width equally among boxes
                                        .fillMaxHeight(), // Fixed height for each box
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(activeCount[index].toString(), fontSize = viewModel.largeText.sp)
                                }
                                //DESCRIPTION and PRICE for item
                                Box(
                                    modifier = Modifier
                                        .weight(4f) // Distribute available width equally among boxes
                                        .fillMaxHeight() // Fixed height for each box
                                ) {
                                    //                                    items(desserts) {
                                    //                                        ListItem(it)
                                    //                                    }
                                    ListItem(item)
                                }
                                //ACTION button ADD
                                Box(
                                    modifier = Modifier
                                        .weight(0.5f) // Distribute available width equally among boxes
                                        .fillMaxHeight() // Fixed height for each box
                                ) {
                                    IconButton(
                                        onClick = { activeCount[index]++ }
                                    ) {
                                        Icon(Icons.Default.Add, contentDescription = "Add")
                                    }
                                }
                                //ACTION button REMOVE
                                Box(
                                    modifier = Modifier
                                        .weight(0.5f) // Distribute available width equally among boxes
                                        .fillMaxHeight() // Fixed height for each box
                                ) {
                                    IconButton(
                                        onClick = {
                                            if (activeCount[index] > 0) activeCount[index]--
                                        }
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = "Remove")
                                    }
                                }
                            }
                        }
                    }
                }

                AnimatedVisibility(visible = cartVisible) {
                    Column(
                        modifier = Modifier
                            .width(56.dp)
                            .fillMaxHeight()
                            .background(Color.Transparent),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CartButton(count = totalOrderCount.value) {
                            cartExpanded.value = true
                        }
                    }
                }
            }
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

@Composable
fun CartButton(count: Int, onClick: () -> Unit) {
    val viewModel: LoginViewModel = viewModel()
//    Box(
//        modifier = Modifier
//            .padding(4.dp)
//            .clickable { onClick() }
//            .background(Color.White, RoundedCornerShape(8.dp))
//    ) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
//            if (count > 0) {
//                Text(count.toString(), color = Color.Red, fontSize = viewModel.largeText.sp)
//            }
//        }
//    }
    Surface(
        modifier = Modifier
            .clickable { onClick() },
            //.background(Color.White, RoundedCornerShape(8.dp))
        shape = RoundedCornerShape(8.dp),
        color = Color.White
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
            if (count > 0) {
                Text(count.toString(), color = Color.Red, fontSize = viewModel.largeText.sp)
            }
        }
    }
}

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


