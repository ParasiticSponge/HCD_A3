package com.example.hcd_a3

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*

class OrderViewModel : ViewModel() {
    val desserts = listOf(
        FoodItem("cake", "chocolate base, chocolate icing", 10),
        FoodItem("ice cream", "chocolate", 7))
    val meals = listOf(
        FoodItem("ramen", "egg, ramen noodles", 12),
        FoodItem("pork bowl", "pork, seasoning", 15))
    val entrees = listOf(
        FoodItem("cake", "chocolate base, chocolate icing", 10),
        FoodItem("ice cream", "chocolate", 7))
    val drinks = listOf(
        FoodItem("wine", "grapes", 20),
        FoodItem("Coca-Cola", "", 5))
    val menu = listOf("Entrees", "Meals", "Desserts", "Drinks")

    private val _dessertCount = mutableStateListOf(*Array(desserts.size) { 0 })
    val dessertCount: SnapshotStateList<Int> get() = _dessertCount
    private val _mealCount = mutableStateListOf(*Array(meals.size) { 0 })
    val mealCount: SnapshotStateList<Int> get() = _mealCount
    private val _drinkCount = mutableStateListOf(*Array(drinks.size) { 0 })
    val drinkCount: SnapshotStateList<Int> get() = _drinkCount
    private val _entreeCount = mutableStateListOf(*Array(entrees.size) { 0 })
    val entreeCount: SnapshotStateList<Int> get() = _entreeCount
    val totalOrderCount = derivedStateOf { dessertCount.sum() + mealCount.sum() + drinkCount.sum() + entreeCount.sum()}
    var selectedCategory by mutableStateOf("Entrees")
    private set

    fun setCategory(newValue: String) {
        selectedCategory = newValue
    }
}