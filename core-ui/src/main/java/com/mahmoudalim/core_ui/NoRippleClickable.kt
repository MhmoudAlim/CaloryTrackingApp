package com.mahmoudalim.core_ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

/**
 * @author Mahmoud Alim on 25/10/2022.
 */

fun Modifier.clickableNoRipple(onClick:() -> Unit) = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = { onClick() }
    )
}