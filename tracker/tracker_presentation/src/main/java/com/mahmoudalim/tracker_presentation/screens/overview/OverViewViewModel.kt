package com.mahmoudalim.tracker_presentation.screens.overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudalim.core.domian.preferences.Preferences
import com.mahmoudalim.core.navigation.Route
import com.mahmoudalim.core.util.UiEvent
import com.mahmoudalim.tracker_domain.usecase.TrackerUseCases
import com.mahmoudalim.tracker_presentation.screens.overview.state.OverViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Mahmoud Alim on 16/10/2022.
 */
@HiltViewModel
class OverViewViewModel @Inject constructor(
    private val preferences: Preferences,
    private val trackerUseCases: TrackerUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var getFoodForDateJab: Job? = null

    //TODO: revest adding savedStateHandle
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var state by mutableStateOf(OverViewState())
        private set

    init {
        preferences.saveShouldShowOnboarding(false)
    }

    fun onEvent(event: OverViewEvent) {
        when (event) {
            is OverViewEvent.OnAddNewFoodClicked -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Navigate(
                            route = Route.SEARCH
                                    + "/${event.meal.mealType.name}"
                                    + "/${state.date.dayOfMonth}"
                                    + "/${state.date.monthValue}"
                                    + "/${state.date.year}"
                        )
                    )
                }
            }
            is OverViewEvent.OnDeleteTrackedFood -> {
                viewModelScope.launch {
                    trackerUseCases.deleteTrackedFoodUseCase(event.trackedFood)
                    refreshFoods()
                }
            }
            OverViewEvent.OnNextDayClick -> {
                viewModelScope.launch {
                    state = state.copy(
                        date = state.date.plusDays(1)
                    )
                    refreshFoods()
                }
            }
            OverViewEvent.OnPreviousDayClick -> {
                viewModelScope.launch {
                    state = state.copy(
                        date = state.date.minusDays(1)
                    )
                    refreshFoods()
                }

            }
            is OverViewEvent.OnToggleMealClick -> {
                viewModelScope.launch {
                    state = state.copy(
                        meals = state.meals.map {
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
        trackerUseCases.getFoodForDateUseCase(state.date)
            .onEach { trackedFoodList ->
                val nutrientsResult = trackerUseCases.calculateMealNutrientsUseCase(trackedFoodList)
                state = state.copy(
                    totalCalories = nutrientsResult.totalCalories,
                    totalCarbs = nutrientsResult.totalCarbs,
                    totalProtein = nutrientsResult.totalProtein,
                    totalFat = nutrientsResult.totalFat,
                    carbsGoal = nutrientsResult.carbsGoal,
                    caloriesGoal = nutrientsResult.caloriesGoal,
                    fatGoal = nutrientsResult.fatGoal,
                    proteinGoal = nutrientsResult.proteinGoal,
                    date = state.date,
                    trackedFoods = trackedFoodList,
                    meals = state.meals.map { meal ->
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