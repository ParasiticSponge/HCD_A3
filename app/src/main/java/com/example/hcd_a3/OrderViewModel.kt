package com.example.hcd_a3

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import android.util.Log

class OrderViewModel : ViewModel() {
    val desserts = listOf(
        FoodItem("Pukeko's Egg", "chocolate shell, caramel filling", 10),
        FoodItem("Whipped Emu Egg", "emu eggshell, custard, fruit, spices", 7))
    val meals = listOf(
        FoodItem("Crocodile Meat", "crocodile meat, peas, herbs, gravysauce", 12),
        FoodItem("Salted Red Kangaroo", "red kangaroo, red peppers, garlic", 15),
        FoodItem("Vegetarian", "cauliflower, corn, snow crab", 15))
    val entrees = listOf(
        FoodItem("Greem Tree Ants", "green tree ants", 10),
        FoodItem("Meat Pie", "beef, pastry", 7))
    val drinks = listOf(
        FoodItem("Wine", "grapes", 20),
        FoodItem("Coca-Cola", "", 5))
    val menu = listOf("Entrees", "Meals", "Desserts", "Drinks", "Recommended")

    private val _dessertCount = mutableStateListOf(*Array(desserts.size) { 0 })
    val dessertCount: SnapshotStateList<Int> get() = _dessertCount
    private val _mealCount = mutableStateListOf(*Array(meals.size) { 0 })
    val mealCount: SnapshotStateList<Int> get() = _mealCount
    private val _drinkCount = mutableStateListOf(*Array(drinks.size) { 0 })
    val drinkCount: SnapshotStateList<Int> get() = _drinkCount
    private val _entreeCount = mutableStateListOf(*Array(entrees.size) { 0 })
    val entreeCount: SnapshotStateList<Int> get() = _entreeCount
    val totalOrderCount = derivedStateOf { dessertCount.sum() + mealCount.sum() + drinkCount.sum() + entreeCount.sum()}
    val totalAmountCount = derivedStateOf { multiplyArray(dessertCount, getPrice(desserts)).sum() + multiplyArray(mealCount, getPrice(meals)).sum() + multiplyArray(drinkCount, getPrice(drinks)).sum() + multiplyArray(entreeCount, getPrice(entrees)).sum() }

    var selectedCategory by mutableStateOf("Entrees")
    private set

    fun setCategory(newValue: String) {
        selectedCategory = newValue
    }

    fun multiplyArray(arr1: List<Int>, arr2: List<Int>): List<Int> {
        // one‑liner to extract all prices
        //val prices = desserts.map { it.price }

        val arrFinal = List(arr1.size) {0}.toMutableList()
        if (arr1.size != arr2.size) return arrFinal

        for (i in 0 until arr1.size) {
            arrFinal[i] = arr1[i] * arr2[i]
        }
        val tag = "Debug"
        arrFinal.forEach { item ->  Log.w(tag, "multiply: $item")}

        return arrFinal
    }

    fun getPrice(arr1: List<FoodItem>): List<Int> {
        val arrFinal = List(arr1.size) {0}.toMutableList()
        for (i in 0 until arr1.size) {
            arrFinal[i] = arr1[i].price
        }
        val tag = "Debug"
        arrFinal.forEach { item ->  Log.w(tag, "getPrice: $item")}

        return arrFinal
    }
}