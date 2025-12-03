package com.example.hcd_a3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun AccountScreen(navController: NavController, viewModel: LoginViewModel) {
    val orderViewModel: OrderViewModel = viewModel()
    val optionsVisible = viewModel.optionsVisible
    val accountVisible = viewModel.accountVisible

    Box(modifier = Modifier
        .fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Surface(
                    tonalElevation = 4.dp
                ) {
                    Column {
                        // Space for phone's status bar
                        Spacer(         //Spacer class puts space between two UI elements. easier compared to padding when inside rows/boxes/columns
                            Modifier.windowInsetsTopHeight(WindowInsets.statusBars)
                        )

                        Box(
                            Modifier
                                .fillMaxWidth()
                                //make 48 the base height, largeText is defaulted to 16dp
                                .height((32 + viewModel.largeText).dp)
                                .background(color = viewModel.secondBackColour)
                        ) {
                        }
                    }
                }
            },
            bottomBar = {
                bottomBar(navController, viewModel, optionsVisible, accountVisible, orderViewModel)
            }
        )
        { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(color = viewModel.secondBackColour)) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center))
                {
                    Image(
                        painter = painterResource(id = R.drawable.angrykirbystop),
                        contentDescription = "kirby",
                        modifier = Modifier.size(300.dp)
                    )

                    Text("kirby says you can't access this right now")
                }
            }
        }
    }
}