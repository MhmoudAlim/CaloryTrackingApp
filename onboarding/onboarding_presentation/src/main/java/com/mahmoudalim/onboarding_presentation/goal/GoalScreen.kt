package com.mahmoudalim.onboarding_presentation.goal

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.mahmoudalim.core.domian.model.ActivityLevel
import com.mahmoudalim.core.domian.model.GoalType
import com.mahmoudalim.core.util.UiEvent
import com.mahmoudalim.core_ui.LocalSpacing
import com.mahmoudalim.onboarding_presentation.R
import com.mahmoudalim.onboarding_presentation.composables.ActionButton
import com.mahmoudalim.onboarding_presentation.composables.OnBoardingScreenScaffold
import com.mahmoudalim.onboarding_presentation.composables.SelectableButton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

/**
 * @author Mahmoud Alim on 27/08/2022.
 */

@Composable
fun GoalScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: GoalViewModel = hiltViewModel()
) {
    ObserveUIEvents(
        uiEvent = viewModel.uiEvent,
        onNavigate = onNavigate,
    )

    OnBoardingScreenScaffold {
        val spacing = LocalSpacing.current
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.your_goal),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.height(spacing.spaceLarge))

            ActivityLevelButtonsRow(viewModel)
        }

        ActionButton(
            text = stringResource(id = R.string.next),
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = viewModel::onNextClick,
            iseEnabled = true,
        )
    }
}

@Composable
private fun ActivityLevelButtonsRow(viewModel: GoalViewModel) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(LocalSpacing.current.spaceMedium)
    ) {
        SelectableButton(
            text = stringResource(id = R.string.lose),
            onClick = { viewModel.onGoalChanged(GoalType.LoseWeight) },
            color = MaterialTheme.colors.primaryVariant,
            isSelected = viewModel.selectedGoal is GoalType.LoseWeight,
            textStyle = MaterialTheme.typography.button.copy(
                fontWeight = FontWeight.Normal
            )
        )

        SelectableButton(
            text = stringResource(id = R.string.keep),
            onClick = { viewModel.onGoalChanged(GoalType.KeepWeight) },
            color = MaterialTheme.colors.primaryVariant,
            isSelected = viewModel.selectedGoal is GoalType.KeepWeight,
            textStyle = MaterialTheme.typography.button.copy(
                fontWeight = FontWeight.Normal
            )
        )

        SelectableButton(
            text = stringResource(id = R.string.gain),
            onClick = { viewModel.onGoalChanged(GoalType.GainWeight) },
            color = MaterialTheme.colors.primaryVariant,
            isSelected = viewModel.selectedGoal is GoalType.GainWeight,
            textStyle = MaterialTheme.typography.button.copy(
                fontWeight = FontWeight.Normal
            )
        )
    }
}


@Composable
private fun ObserveUIEvents(
    uiEvent: Flow<UiEvent>,
    onNavigate: (UiEvent.Navigate) -> Unit,
) {
    LaunchedEffect(key1 = true) {
        uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
}


