package com.mahmoudalim.tracker_data.mapper

import com.mahmoudalim.tracker_data.local.entity.TrackedFoodEntity
import com.mahmoudalim.tracker_domain.model.TrackedFood

/**
 * @author Mahmoud Alim on 10/10/2022.
 */

fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        name = name,
        carbs = carbs,
        calories = calories,
        fat = fat,
        protein = protein,
        imageUrl = imageUrl,
        id = id,
        type = mealType.name,
        amount = amount,
        dayOfMonths = date.dayOfMonth,
        month = date.monthValue,
        year = date.year
    )
}