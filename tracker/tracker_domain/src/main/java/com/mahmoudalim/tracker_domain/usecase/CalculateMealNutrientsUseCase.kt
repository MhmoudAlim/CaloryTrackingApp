package com.mahmoudalim.tracker_domain.usecase

import com.mahmoudalim.core.NutrientsFacts.CARBS_CALORY_PER_GRAM
import com.mahmoudalim.core.NutrientsFacts.FAT_CALORY_PER_GRAM
import com.mahmoudalim.core.NutrientsFacts.PROTEIN_CALORY_PER_GRAM
import com.mahmoudalim.core.domian.model.ActivityLevel
import com.mahmoudalim.core.domian.model.BmrFormula.*
import com.mahmoudalim.core.domian.model.Gender
import com.mahmoudalim.core.domian.model.GoalType
import com.mahmoudalim.core.domian.model.UserInfo
import com.mahmoudalim.core.domian.preferences.Preferences
import com.mahmoudalim.tracker_domain.model.MealType
import com.mahmoudalim.tracker_domain.model.TrackedFood
import kotlin.math.roundToInt


/**
 * @author Mahmoud Alim on 14/10/2022.
 */
class CalculateMealNutrientsUseCase(private val preferences: Preferences) {

    operator fun invoke(trackedFoods: List<TrackedFood>): NutrientResult {
        val allNutrients = mapToMealNutrients(trackedFoods)
        val totalCarbs = allNutrients.values.sumOf { it.carbs }
        val totalProtein = allNutrients.values.sumOf { it.protein }
        val totalFat = allNutrients.values.sumOf { it.fat }
        val totalCalories = allNutrients.values.sumOf { it.calories }

        val userInfo = preferences.getUserInfo()

        val caloryGoal = calculateDailyCaloryRequirements(userInfo)
        val carbsGoal = (caloryGoal * userInfo.carbRatio / CARBS_CALORY_PER_GRAM).roundToInt()
        val proteinGoal =
            (caloryGoal * userInfo.proteinRatio / PROTEIN_CALORY_PER_GRAM).roundToInt()
        val fatGoal = (caloryGoal * userInfo.fatRatio / FAT_CALORY_PER_GRAM).roundToInt()

        return NutrientResult(
            carbsGoal = carbsGoal,
            proteinGoal = proteinGoal,
            fatGoal = fatGoal,
            caloriesGoal = caloryGoal,
            totalCarbs = totalCarbs,
            totalProtein = totalProtein,
            totalFat = totalFat,
            totalCalories = totalCalories,
            mealNutrients = allNutrients
        )
    }

    private fun mapToMealNutrients(trackedFoods: List<TrackedFood>): Map<MealType, MealNutrients> {
        val allNutrients = trackedFoods
            .groupBy {
                it.mealType
            }.mapValues { entry ->
                val type = entry.key
                val foods = entry.value
                MealNutrients(
                    carbs = foods.sumOf { it.carbs },
                    protein = foods.sumOf { it.protein },
                    fat = foods.sumOf { it.fat },
                    calories = foods.sumOf { it.calories },
                    mealType = type
                )
            }
        return allNutrients
    }

    /*
        The Basal Metabolic Rate (BMR) Calculator estimates your basal metabolic rate. the amount of
         energy expended while at rest in a neutrally temperate environment, and in a post-absorptive
         state (meaning that the digestive system is inactive, which requires about 12 hours of fasting).
    */
    //TODO: add an Optional screen on ONBOARDING to get the bmrFormula from user
    private fun calculateBMR(
        userInfo: UserInfo
    ): Int {
        return when (userInfo.gender) {
            is Gender.Male -> {
                when (userInfo.bmrFormula) {
                    ORIGINAL_HARRIS_BENEDICT_1919 ->
                        (66.47f + 13.75f * userInfo.weight + 5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
                    HARRIS_BENEDICT_BY_ROZA_1984 ->
                        (88.362f + 13.397f * userInfo.weight + 4.799f * userInfo.height - 5.677f * userInfo.age).roundToInt()
                }
            }
            is Gender.Female -> {
                when (userInfo.bmrFormula) {
                    ORIGINAL_HARRIS_BENEDICT_1919 ->
                        (665.09f + 9.56f * userInfo.weight + 1.84f * userInfo.height - 4.67f * userInfo.height).roundToInt()
                    HARRIS_BENEDICT_BY_ROZA_1984 ->
                        (447.593 + 9.247 * userInfo.weight + 3.098 * userInfo.height - 4.330 * userInfo.height).roundToInt()
                }
            }
        }
    }


    private fun calculateDailyCaloryRequirements(userInfo: UserInfo): Int {
        val activityFactor = when (userInfo.activityLevel) {
            ActivityLevel.High -> 1.65f
            ActivityLevel.Low -> 1.20f
            ActivityLevel.Medium -> 1.45f
        }

        val caloryExtraFactor = when (userInfo.goalType) {
            GoalType.GainWeight -> -500
            GoalType.KeepWeight -> 0
            GoalType.LoseWeight -> 500
        }

        val bmr = calculateBMR(userInfo)

        return (bmr * activityFactor + caloryExtraFactor).roundToInt()
    }


    data class NutrientResult(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val fatGoal: Int,
        val caloriesGoal: Int,
        val totalCarbs: Int,
        val totalProtein: Int,
        val totalFat: Int,
        val totalCalories: Int,
        val mealNutrients: Map<MealType, MealNutrients>
    )

    data class MealNutrients(
        val carbs: Int,
        val protein: Int,
        val fat: Int,
        val calories: Int,
        val mealType: MealType
    )

}