package com.mahmoudalim.tracker_domain.usecase

import com.mahmoudalim.tracker_domain.model.MealType
import com.mahmoudalim.tracker_domain.model.TrackableFood
import com.mahmoudalim.tracker_domain.model.TrackedFood
import com.mahmoudalim.tracker_domain.repository.TrackerRepo
import java.time.LocalDate
import kotlin.math.roundToInt

/**
 * @author Mahmoud Alim on 14/10/2022.
 */
class TrackFoodUseCase(private val repo: TrackerRepo) {

    suspend operator fun invoke(
        food: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate
    ) {
        repo.insertTrackedFood(
            food = TrackedFood(
                name = food.name,
                carbs = getNutrientOfAmount(food.carbsPer100gm, amount),
                calories = getNutrientOfAmount(food.caloriesPer100gm, amount),
                fat = getNutrientOfAmount(food.fatPer100gm, amount),
                protein = getNutrientOfAmount(food.proteinPer100gm, amount),
                imageUrl = food.imageUrl,
                mealType = mealType,
                date = date,
                amount = amount
            )
        )
    }


    private fun getNutrientOfAmount(
        nutrient: Int,
        amount: Int
    ): Int {
        val nutrientPer1Gram = nutrient / 100f
        val nutrientOfAmount = nutrientPer1Gram * amount
        return nutrientOfAmount.roundToInt()
    }

}