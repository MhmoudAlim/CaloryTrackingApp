package com.mahmoudalim.onboarding_presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mahmoudalim.core_ui.LocalSpacing

/**
 * @author Mahmoud Alim on 26/08/2022.
 */
@Composable
fun OnBoardingScreenScaffold(
    modifier: Modifier = Modifier,
    Content: @Composable() BoxScope.() -> Unit
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Content(this@Box)
    }
}