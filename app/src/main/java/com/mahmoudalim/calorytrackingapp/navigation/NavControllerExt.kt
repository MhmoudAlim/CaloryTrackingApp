package com.mahmoudalim.calorytrackingapp.navigation

import androidx.navigation.NavController
import com.mahmoudalim.core.util.UiEvent

/**
 * Created by Mahmoud Alim on 06/02/2022.
 */

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}