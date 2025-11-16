package com.example.hcd_a3

//get and set for 'by' operation
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var background = Color(255, 243, 200)
    var yellow = Color(255, 205, 0)
    var orange = Color(224, 72, 21)
    var brown = Color(103, 52, 25)

    var optionsVisible by mutableStateOf(false)
        private set
    var screenReader by mutableStateOf(false)
        private set
    var languageOption by mutableStateOf(false)
        private set
    var languageSelected by mutableStateOf("English")
        private set
    var largeText by mutableStateOf(16)
        private set
    var smallText by mutableStateOf(12)
        private set

    fun toggleOptions() {
        optionsVisible = !optionsVisible
    }
    fun toggleReader() {
        screenReader = !screenReader
    }
    fun toggleLanguage() {
        languageOption = !languageOption
    }
    fun increaseTextSize() {
        largeText += 1
        smallText += 1
    }
    fun decreaseTextSize() {
        largeText -= 1
        smallText -= 1
    }
}