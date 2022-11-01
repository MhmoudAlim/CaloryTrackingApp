package com.mahmoudalim.tracker_presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudalim.core.domian.usecase.FilterOutDigits
import com.mahmoudalim.core.util.UiEvent
import com.mahmoudalim.core.util.UiText
import com.mahmoudalim.tracker_domain.usecase.TrackerUseCases
import com.mahmoudalim.tracker_presentation.R
import com.mahmoudalim.tracker_presentation.screens.search.state.SearchState
import com.mahmoudalim.tracker_presentation.screens.search.state.TrackableFoodUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Mahmoud Alim on 26/10/2022.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: TrackerUseCases,
    private val filterOutDigits: FilterOutDigits,
) : ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnAmountForFoodChange -> {
                state = state.copy(
                    trackableFoods = state.trackableFoods.map {
                        if (it.food == event.food) {
                            it.copy(amount = filterOutDigits(event.amount))
                        } else it
                    })
            }
            is SearchEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }
            SearchEvent.OnSearch -> {
                searchForFood()

            }
            is SearchEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isInFocus && state.query.isBlank()
                )
            }
            is SearchEvent.OnToggleTrackableFood -> {
                state = state.copy(
                    trackableFoods = state.trackableFoods.map {
                        if (it.food == event.food) {
                            it.copy(isExpanded = !it.isExpanded)
                        } else it
                    })
            }
            is SearchEvent.OnTrackFoodClick -> {
                trackFood(event)

            }
        }
    }

    private fun searchForFood() {
        viewModelScope.launch {
            state = state.copy(
                isSearching = true,
                trackableFoods = emptyList()
            )
            useCases.searchForFoodUseCase(
                query = state.query
            ).apply {
                onSuccess { foods ->
                    state = state.copy(
                        trackableFoods = foods.map {
                            TrackableFoodUiState(food = it)
                        },
                        isSearching = false,
                        query = ""
                    )
                }
                onFailure {
                    state = state.copy(isSearching = false)
                    _uiEvent.send(
                        UiEvent.ShowSnackBar(
                            UiText.StringResources(R.string.error_something_went_wrong)
                        )
                    )
                }
            }
        }
    }

    private fun trackFood(event: SearchEvent.OnTrackFoodClick) {
        viewModelScope.launch {
            val uiState = state.trackableFoods.find { it.food == event.food }
            useCases.trackedFoodUseCase(
                food = uiState?.food ?: return@launch,
                amount = uiState.amount.toIntOrNull() ?: return@launch,
                mealType = event.mealType,
                date = event.date
            )
            _uiEvent.send(UiEvent.NavigateUp)

        }
    }

}