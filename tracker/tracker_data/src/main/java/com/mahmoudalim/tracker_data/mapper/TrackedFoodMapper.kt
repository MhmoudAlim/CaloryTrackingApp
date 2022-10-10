package com.mahmoudalim.tracker_data.mapper

import com.mahmoudalim.tracker_data.local.entity.TrackedFoodEntity
import com.mahmoudalim.tracker_domain.model.MealType
import com.mahmoudalim.tracker_domain.model.TrackedFood
import java.time.LocalDate

/**
 * @author Mahmoud Alim on 10/10/2022.
 */

fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
    return TrackedFood(
        name = name,
        carbs = carbs,
        calories = calories,
        fat = fat,
        protein = protein,
        imageUrl = imageUrl,
        id = id,
        mealType = MealType.fromString(type),
        amount = amount,
        date = LocalDate.of(year, month, dayOfMonths)
    )
}

