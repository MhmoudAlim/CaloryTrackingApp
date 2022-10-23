package com.mahmoudalim.tracker_presentation.screens.overview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudalim.core.domian.preferences.Preferences
import com.mahmoudalim.core.navigation.Route
import com.mahmoudalim.core.util.UiEvent
import com.mahmoudalim.tracker_domain.usecase.TrackerUseCases
import com.mahmoudalim.tracker_presentation.screens.overview.model.TrackerOverViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias Tracker = TrackerOverViewState

/**
 * @author Mahmoud Alim on 16/10/2022.
 */
@HiltViewModel
class TrackerOverViewViewModel @Inject constructor(
    private val preferences: Preferences,
    private val trackerUseCases: TrackerUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var getFoodForDateJab: Job? = null

    var uiEvent: StateFlow<UiEvent> =
        savedStateHandle.getStateFlow(UiEvent.toString(), UiEvent.Idle)
        private set


    var state: StateFlow<Tracker> =
        savedStateHandle.getStateFlow(TrackerOverViewState.toString(), Tracker())
        private set

    init {
        preferences.saveShouldShowOnboarding(false)
    }

    fun onEvent(event: TrackerOverViewEvent) {
        when (event) {
            is TrackerOverViewEvent.OnAddNewFoodClicked -> {
                viewModelScope.launch {
                    savedStateHandle[UiEvent.toString()] = UiEvent.Navigate(
                        route = Route.SEARCH
                                + "/${event.meal.mealType.name}"
                                + "/${state.value.date.dayOfMonth}"
                                + "/${state.value.date.monthValue}"
                                + "/${state.value.date.year}"
                    )
                }
            }
            is TrackerOverViewEvent.OnDeleteTrackedFood -> {
                viewModelScope.launch {
                    trackerUseCases.deleteTrackedFoodUseCase(event.trackedFood)
                    refreshFoods()
                }
            }
            TrackerOverViewEvent.OnNextDayClick -> {
                viewModelScope.launch {
                    savedStateHandle[TrackerOverViewState.toString()] =
                        state.value.copy(
                            date = state.value.date.plusDays(1)
                        )
                    refreshFoods()
                }
            }
            TrackerOverViewEvent.OnPreviousDayClick -> {
                viewModelScope.launch {
                    savedStateHandle[TrackerOverViewState.toString()] =
                        state.value.copy(
                            date = state.value.date.minusDays(1)
                        )
                    refreshFoods()
                }

            }
            is TrackerOverViewEvent.OnToggleMealClick -> {
                viewModelScope.launch {
                    savedStateHandle[TrackerOverViewState.toString()] =
                        state.value.copy(
                            meals = state.value.meals.map {
                                if (it.name == event.meal.name) {
                                    it.copy(isExpanded = !it.isExpanded)
                                } else it
                            }
                        )
                    refreshFoods()
                }
            }
        }
    }

    private fun refreshFoods() {
        cancelCurrentJob()
        trackerUseCases.getFoodForDateUseCase(state.value.date)
            .onEach { trackedFoodList ->
                val nutrientsResult = trackerUseCases.calculateMealNutrientsUseCase(trackedFoodList)
                savedStateHandle[TrackerOverViewState.toString()] =
                    state.value.copy(
                        totalCalories = nutrientsResult.totalCalories,
                        totalCarbs = nutrientsResult.totalCarbs,
                        totalProtein = nutrientsResult.totalProtein,
                        totalFat = nutrientsResult.totalFat,
                        carbsGoal = nutrientsResult.carbsGoal,
                        caloriesGoal = nutrientsResult.caloriesGoal,
                        fatGoal = nutrientsResult.fatGoal,
                        proteinGoal = nutrientsResult.proteinGoal,
                        date = state.value.date,
                        trackedFoods = trackedFoodList,
                        meals = state.value.meals.map { meal ->
                            val nutrientsForMeal =
                                nutrientsResult.mealNutrients[meal.mealType]
                                    ?: return@map meal.copy(
                                        carbs = 0,
                                        protein = 0,
                                        calories = 0,
                                        fat = 0
                                    )
                            meal.copy(
                                carbs = nutrientsForMeal.carbs,
                                fat = nutrientsForMeal.fat,
                                calories = nutrientsForMeal.calories,
                                protein = nutrientsForMeal.protein,
                            )
                        }
                    )
            }.launchIn(viewModelScope)
            .also { getFoodForDateJab = it }
    }

    private fun cancelCurrentJob() {
        getFoodForDateJab?.cancel()
    }

}