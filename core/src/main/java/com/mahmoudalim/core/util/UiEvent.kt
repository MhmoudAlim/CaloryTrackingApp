package com.mahmoudalim.core.util

/**
 * Created by Mahmoud Alim on 06/02/2022.
 */

sealed class UiEvent {
    //TODO: will most probably cause a bug(git rid off Idle)
    object Idle : UiEvent()
    object OnNextClick : UiEvent()
    object NavigateUp : UiEvent()
    class ShowSnackBar(val message: UiText) : UiEvent()

    companion object {
        override fun toString(): String {
            return "UiEvent"
        }
    }
}
