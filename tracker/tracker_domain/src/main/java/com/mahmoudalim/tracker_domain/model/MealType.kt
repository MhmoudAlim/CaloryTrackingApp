package com.mahmoudalim.tracker_domain.model

/**
 * @author Mahmoud Alim on 10/10/2022.
 */
sealed class MealType(val name: String) {
    object BreakFast : MealType(BREAKFAST)
    object Lunch : MealType(LUNCH)
    object Dinner : MealType(DINNER)
    object Snack : MealType(SNACK)

    companion object {
        fun fromString(name: String): MealType {
            return when (name) {
                BREAKFAST -> BreakFast
                LUNCH -> Lunch
                DINNER -> Dinner
                SNACK -> Snack
                else -> Snack
            }
        }

        const val BREAKFAST = "breakfast"
        const val LUNCH = "lunch"
        const val DINNER = "dinner"
        const val SNACK = "snack"
    }
}
