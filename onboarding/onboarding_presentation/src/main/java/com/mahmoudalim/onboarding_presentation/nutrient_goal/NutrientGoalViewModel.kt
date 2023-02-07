package com.mahmoudalim.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudalim.core.domian.preferences.Preferences
import com.mahmoudalim.core.domian.usecase.FilterOutDigits
import com.mahmoudalim.core.navigation.Route
import com.mahmoudalim.core.util.UiEvent
import com.mahmoudalim.onboarding_domain.usecase.ValidateNutrients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Mahmoud Alim on 27/08/2022.
 */

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigitsUSeCase: FilterOutDigits,
    private val validateNutrientsUSeCase: ValidateNutrients,
) : ViewModel() {

    var state by mutableStateOf(NutrientGoalState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: NutrientGoalEvent) {
        when (event) {
            is NutrientGoalEvent.OnCarbRatioEntered -> {
                if (event.ratio.length <= 2)
                    state = state.copy(carbRatio = filterOutDigitsUSeCase(event.ratio))
            }
            is NutrientGoalEvent.OnFatRatioEntered -> {
                if (event.ratio.length <= 2)
                    state = state.copy(fatRatio = filterOutDigitsUSeCase(event.ratio))
            }
            is NutrientGoalEvent.OnProteinRatioEntered -> {
                if (event.ratio.length <= 2)
                    state = state.copy(proteinRatio = filterOutDigitsUSeCase(event.ratio))
            }
            NutrientGoalEvent.OnNextClick -> {
                val result = validateNutrientsUSeCase(
                    carbRatioText = state.carbRatio,
                    proteinRatioText = state.proteinRatio,
                    fatRatioText = state.fatRatio
                )
                validateResult(result)
            }
        }
    }

    private fun validateResult(result: ValidateNutrients.Result) {
        viewModelScope.launch {
            when (result) {
                is ValidateNutrients.Result.Failure -> {
                    _uiEvent.send(UiEvent.ShowSnackBar(result.message))
                }
                is ValidateNutrients.Result.Success -> {
                    preferences.saveCarbRatio(result.carbRatio)
                    preferences.saveProteinRatio(result.proteinRatio)
                    preferences.saveFatRatio(result.fatRatio)
                    _uiEvent.send(UiEvent.OnNextClick)
                }
            }
        }
    }
}