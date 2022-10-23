package com.mahmoudalim.tracker_presentation.screens.overview

import com.mahmoudalim.tracker_domain.model.TrackedFood
import com.mahmoudalim.tracker_presentation.screens.overview.model.Meal

/**
 * @author Mahmoud Alim on 16/10/2022.
 */
sealed class TrackerOverViewEvent {
    object OnNextDayClick : TrackerOverViewEvent()
    object OnPreviousDayClick : TrackerOverViewEvent()
    data class OnToggleMealClick(val meal: Meal) : TrackerOverViewEvent()
    data class OnDeleteTrackedFood(val trackedFood: TrackedFood) : TrackerOverViewEvent()
    data class OnAddNewFoodClicked(val meal: Meal) : TrackerOverViewEvent()
}