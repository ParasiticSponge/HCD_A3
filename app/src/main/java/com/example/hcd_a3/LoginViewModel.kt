package com.example.hcd_a3

//get and set for 'by' operation
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var designScheme by mutableStateOf("default") // Use 'by' for easier access
        private set // Keep the setter private to the ViewModel

    val mainBackColour by derivedStateOf {
        when (designScheme) {
            "default" -> Color(93, 109, 91)
            "light" -> Color(245, 235, 214)
            "dark" -> Color(43, 43, 43)
            else -> Color.White
        }
    }
    val oButtonColour by derivedStateOf {
        when (designScheme) {
            "default" -> Color(199, 107, 47)
            "light" -> Color(235, 165, 56)
            "dark" -> Color(176, 106, 81)
            else -> Color.White
        }
    }
    val iconsColour by derivedStateOf {
        when (designScheme) {
            "default" -> Color(59, 59, 59)
            "light" -> Color(59, 59, 59)
            "dark" -> Color(255, 255, 255)
            else -> Color.White
        }
    }
    val topColour by derivedStateOf {
        when (designScheme) {
            "default" -> Color(93, 109, 91)
            "light" -> Color(178, 186, 141)
            "dark" -> Color(59, 59, 59)
            else -> Color.White
        }
    }
    val scrollColour by derivedStateOf {
        when (designScheme) {
            "default" -> Color(93, 109, 91)
            "light" -> Color(178, 186, 141)
            "dark" -> Color(102, 96, 96)
            else -> Color.White
        }
    }
    val scrollButtonColour by derivedStateOf {
        when (designScheme) {
            "default" -> Color(255, 217, 90)
            "light" -> Color(255, 217, 90)
            "dark" -> Color(43, 43, 43)
            else -> Color.White
        }
    }
    val buttonColour by derivedStateOf {
        when (designScheme) {
            "default" -> Color(255, 217, 90)
            "light" -> Color(255, 217, 90)
            "dark" -> Color(102, 96, 96)
            else -> Color.White
        }
    }
    val buttonBackColour by derivedStateOf {
        when (designScheme) {
            "default" -> Color(235, 165, 56)
            "light" -> Color(235, 165, 56)
            "dark" -> Color(168, 159, 159)
            else -> Color.White
        }
    }
    val bottomColour by derivedStateOf {
        when (designScheme) {
            "default" -> Color(255, 217, 90)
            "light" -> Color(255, 255, 255)
            "dark" -> Color(102, 96, 96)
            else -> Color.White
        }
    }
    val secondBackColour by derivedStateOf {
        when (designScheme) {
            "default" -> Color(245, 235, 214)
            "light" -> Color(245, 235, 214)
            "dark" -> Color(43, 43, 43)
            else -> Color.White
        }
    }
    val leaves by derivedStateOf {
        when (designScheme) {
            "default" -> Color(59, 59, 59)
            "light" -> Color(131, 138, 104)
            "dark" -> Color(0, 0, 0)
            else -> Color.White
        }
    }
    val oButtonHighlightColour by derivedStateOf {
        when (designScheme) {
            "default" -> Color(255, 217, 90)
            "light" -> Color(255, 255, 255)
            "dark" -> Color(168, 159, 159)
            else -> Color.White
        }
    }
    val oTextColour by derivedStateOf {
        when (designScheme) {
            "default" -> Color(0, 0, 0)
            "light" -> Color(0, 0, 0)
            "dark" -> Color(255, 255, 255)
            else -> Color.White
        }
    }
    val oTextHighlightColour by derivedStateOf {
        when (designScheme) {
            "default" -> Color(255, 255, 255)
            "light" -> Color(0, 0, 0)
            "dark" -> Color(255, 255, 255)
            else -> Color.White
        }
    }
    val oUnderlineTextColour by derivedStateOf {
        when (designScheme) {
            "default" -> Color(255, 217, 90)
            "light" -> Color(0, 162, 232)
            "dark" -> Color(0, 162, 232)
            else -> Color.White
        }
    }
    val content by derivedStateOf {
        when (designScheme) {
            "default" -> Color(255, 255, 255)
            "light" -> Color(255, 255, 255)
            "dark" -> Color(102, 96, 96)
            else -> Color.White
        }
    }
    val arrowColour by derivedStateOf {
        when (designScheme) {
            "default" -> Color(255, 217, 90)
            "light" -> Color(59, 59, 59)
            "dark" -> Color(255, 255, 255)
            else -> Color.White
        }
    }
//    var logo = when (designScheme.value) {
//        "default" -> Color.White
//        "light" -> Color()
//        "dark" -> Color()
//        else -> Color()
//    }

    var optionsVisible by mutableStateOf(false)
        private set
    var screenReader by mutableStateOf(false)
        private set
    var languageOption by mutableStateOf(false)
        private set
    var themeOption by mutableStateOf(false)
        private set
    var languageSelected by mutableStateOf("English")
        private set
    var themeSeleted by mutableStateOf("Default")
        private set
    var accountVisible by mutableStateOf(false)
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
    fun toggleTheme() {
        themeOption = !themeOption
    }
    fun increaseTextSize() {
        largeText += 1
        smallText += 1
    }
    fun decreaseTextSize() {
        largeText -= 1
        smallText -= 1
    }
    fun toggleAccount() {
        accountVisible = !accountVisible
    }
    fun changeScheme(newScheme: String) {
        designScheme = newScheme
    }
}