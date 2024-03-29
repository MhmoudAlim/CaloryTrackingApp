package com.mahmoudalim.onboarding_presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.mahmoudalim.core.R
import com.mahmoudalim.core.navigation.Route
import com.mahmoudalim.core.util.UiEvent
import com.mahmoudalim.core_ui.LocalSpacing
import com.mahmoudalim.onboarding_presentation.composables.ActionButton
import com.mahmoudalim.onboarding_presentation.composables.OnBoardingScreenScaffold

/**
 * Created by Mahmoud Alim on 05/02/2022.
 */

@Composable
fun WelcomeScreen(
    onNextClick: () -> Unit
) {
    val spacing = LocalSpacing.current

    OnBoardingScreenScaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.welcome_text),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))
        }

        ActionButton(
            modifier = Modifier.align(BottomEnd)
        ) {
            onNextClick()
        }
    }
}