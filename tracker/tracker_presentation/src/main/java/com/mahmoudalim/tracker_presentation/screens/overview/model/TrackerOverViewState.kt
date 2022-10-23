package com.mahmoudalim.tracker_presentation.screens.overview.model

import com.mahmoudalim.tracker_domain.model.TrackedFood
import java.time.LocalDate

/**
 * @author Mahmoud Alim on 16/10/2022.
 */
data class TrackerOverViewState(
    val totalCarbs: Int = 0,
    val totalProtein: Int = 0,
    val totalFat: Int = 0,
    val totalCalories: Int = 0,
    val carbsGoal: Int = 0,
    val proteinGoal: Int = 0,
    val fatGoal: Int = 0,
    val caloriesGoal: Int = 0,
    val date: LocalDate = LocalDate.now(),
    val trackedFoods : List<TrackedFood> = emptyList(),
    val meals : List<Meal> = Meal.defaultMeals
){
    companion object {
        override fun toString(): String {
            return "TrackerOverViewState"
        }
    }
}