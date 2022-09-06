package com.mahmoudalim.core.util

/**
 * Created by Mahmoud Alim on 06/02/2022.
 */

sealed class UiEvent {
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
    class ShowSnackBar(val message: UiText) : UiEvent()
}
