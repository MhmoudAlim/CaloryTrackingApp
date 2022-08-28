package com.mahmoudalim.onboarding_presentation.nutrient_goal

/**
 * @author Mahmoud Alim on 27/08/2022.
 */

sealed class NutrientGoalEvent {
    data class OnCarbRatioEntered(val ratio: String) : NutrientGoalEvent()
    data class OnProteinRatioEntered(val ratio: String) : NutrientGoalEvent()
    data class OnFatRatioEntered(val ratio: String) : NutrientGoalEvent()
    object OnNextClick : NutrientGoalEvent()
}


data class NutrientGoalState(
    val carbRatio: String = "40",
    val proteinRatio: String = "30",
    val fatRatio: String = "30"
)
