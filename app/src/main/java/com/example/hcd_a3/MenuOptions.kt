package com.example.hcd_a3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    Column {


        Box(modifier = Modifier
                .fillMaxSize()
                .weight(4f)
        ) {

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
                Button(onClick = { navController.navigate("menu") }) {
                    Text("Account")
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
                Button(onClick = { navController.navigate("menu") }) {
                    Text("Guest")
                }
            }

        }
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f)
        ) {

        }
    }
}