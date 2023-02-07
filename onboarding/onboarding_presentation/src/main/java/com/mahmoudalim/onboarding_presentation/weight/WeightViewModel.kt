package com.mahmoudalim.onboarding_presentation.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudalim.core.domian.preferences.Preferences
import com.mahmoudalim.core.navigation.Route
import com.mahmoudalim.core.util.UiEvent
import com.mahmoudalim.core.util.UiText
import com.mahmoudalim.onboarding_presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Mahmoud Alim on 27/08/2022.
 */

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: Preferences,
) : ViewModel() {

    var weight by mutableStateOf("70.0")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onWeightEnter(weight: String) {
        if (weight.length <= 5) this.weight = weight
    }

    fun onNextClick() {
        viewModelScope.launch {
            val weightNumber = weight.toFloatOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(UiText.StringResources(R.string.error_weight_cant_be_empty))
                )
                return@launch
            }
            preferences.saveWeight(weightNumber)
            _uiEvent.send(
                UiEvent.OnNextClick
            )
        }
    }
}