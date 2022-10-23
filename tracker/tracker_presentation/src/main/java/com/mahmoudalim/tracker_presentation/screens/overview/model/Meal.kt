package com.mahmoudalim.tracker_presentation.screens.overview.model

import androidx.annotation.DrawableRes
import com.mahmoudalim.core.util.UiText
import com.mahmoudalim.tracker_domain.model.MealType
import com.mahmoudalim.tracker_presentation.R

/**
 * @author Mahmoud Alim on 16/10/2022.
 */
data class Meal(
    val name: UiText,
    @DrawableRes
    val drawableRes: Int,
    val mealType: MealType,
    val fat: Int = 0,
    val carbs: Int = 0,
    val protein: Int = 0,
    val calories: Int = 0,
    val isExpanded: Boolean = false,
) {
    companion object {
        val defaultMeals get() = getListOfDefaultMeals()
        private fun getListOfDefaultMeals() = listOf(
            Meal(
                name = UiText.StringResources(R.string.breakfast),
                drawableRes = R.drawable.ic_breakfast,
                mealType = MealType.BreakFast
            ),
            Meal(
                name = UiText.StringResources(R.string.lunch),
                drawableRes = R.drawable.ic_lunch,
                mealType = MealType.Lunch
            ),
            Meal(
                name = UiText.StringResources(R.string.dinner),
                drawableRes = R.drawable.ic_dinner,
                mealType = MealType.Dinner
            ),
            Meal(
                name = UiText.StringResources(R.string.snacks),
                drawableRes = R.drawable.ic_snack,
                mealType = MealType.Snack
            )
        )
    }
}



