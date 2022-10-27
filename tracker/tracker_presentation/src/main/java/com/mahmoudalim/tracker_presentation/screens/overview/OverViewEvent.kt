package com.mahmoudalim.tracker_presentation.screens.overview

import com.mahmoudalim.tracker_domain.model.TrackedFood
import com.mahmoudalim.tracker_presentation.screens.overview.state.Meal

/**
 * @author Mahmoud Alim on 16/10/2022.
 */
sealed class OverViewEvent {
    object OnNextDayClick : OverViewEvent()
    object OnPreviousDayClick : OverViewEvent()
    data class OnToggleMealClick(val meal: Meal) : OverViewEvent()
    data class OnDeleteTrackedFood(val trackedFood: TrackedFood) : OverViewEvent()
    data class OnAddNewFoodClicked(val meal: Meal) : OverViewEvent()
}