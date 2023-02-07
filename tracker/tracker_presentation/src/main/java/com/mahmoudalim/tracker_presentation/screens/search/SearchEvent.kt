package com.mahmoudalim.tracker_presentation.screens.search

import com.mahmoudalim.tracker_domain.model.MealType
import com.mahmoudalim.tracker_domain.model.TrackableFood
import java.time.LocalDate

/**
 * @author Mahmoud Alim on 26/10/2022.
 */
sealed class SearchEvent {
    data class OnQueryChange(val query: String) : SearchEvent()
    object OnSearch : SearchEvent()
    data class OnToggleTrackableFood(val food: TrackableFood) : SearchEvent()
    data class OnAmountForFoodChange(val amount: String, val food: TrackableFood) : SearchEvent()
    data class OnTrackFoodClick(val mealType: MealType, val food: TrackableFood , val date: LocalDate) : SearchEvent()
    data class OnSearchFocusChange(val isInFocus: Boolean) : SearchEvent()

}
