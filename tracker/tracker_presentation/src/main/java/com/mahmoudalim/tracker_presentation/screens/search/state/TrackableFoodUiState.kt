package com.mahmoudalim.tracker_presentation.screens.search.state

import com.mahmoudalim.tracker_domain.model.TrackableFood

/**
 * @author Mahmoud Alim on 26/10/2022.
 */
data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)