package com.mahmoudalim.tracker_presentation.screens.search.state

/**
 * @author Mahmoud Alim on 26/10/2022.
 */
data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val trackableFoods: List<TrackableFoodUiState> = emptyList(),
)
