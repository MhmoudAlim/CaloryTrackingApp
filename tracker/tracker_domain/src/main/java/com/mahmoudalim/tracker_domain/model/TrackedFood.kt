package com.mahmoudalim.tracker_domain.model

import java.time.LocalDate

/**
 * @author Mahmoud Alim on 10/10/2022.
 */
data class TrackedFood(
    val name: String,
    val carbs: Int,
    val protein: Int,
    val calories: Int,
    val fat: Int,
    val imageUrl: String?,
    val mealType: MealType,
    val amount: Int,
    val date: LocalDate,
    val id: Int? = null
)
