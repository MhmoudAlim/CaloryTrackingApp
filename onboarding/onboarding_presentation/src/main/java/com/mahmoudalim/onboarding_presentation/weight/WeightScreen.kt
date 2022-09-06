package com.mahmoudalim.onboarding_presentation.weight

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.mahmoudalim.core.R
import com.mahmoudalim.core.util.UiEvent
import com.mahmoudalim.core_ui.LocalSpacing
import com.mahmoudalim.onboarding_presentation.composables.ActionButton
import com.mahmoudalim.onboarding_presentation.composables.OnBoardingScreenScaffold
import com.mahmoudalim.onboarding_presentation.composables.UnitTextField
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

/**
 * @author Mahmoud Alim on 27/08/2022.
 */

@Composable
fun WeightScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: WeightViewModel = hiltViewModel(),
) {
    ObserveUIEvents(
        uiEvent = viewModel.uiEvent,
        onNavigate = onNavigate,
        scaffoldState = scaffoldState
    )

    OnBoardingScreenScaffold {
        val spacing = LocalSpacing.current
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.whats_your_weight),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            UnitTextField(
                value = viewModel.weight,
                onValueChanged = viewModel::onWeightEnter,
                unit = stringResource(id = R.string.kg)
            )
        }

        ActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = viewModel::onNextClick
        )

    }
}


@Composable
private fun ObserveUIEvents(
    uiEvent: Flow<UiEvent>,
    onNavigate: (UiEvent.Navigate) -> Unit,
    scaffoldState: ScaffoldState,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                UiEvent.NavigateUp -> Unit
            }
        }
    }
}