package com.mahmoudalim.onboarding_presentation.gender

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
import com.mahmoudalim.core.domian.model.Gender
import com.mahmoudalim.core.util.UiEvent
import com.mahmoudalim.core_ui.LocalSpacing
import com.mahmoudalim.onboarding_presentation.R
import com.mahmoudalim.onboarding_presentation.composables.ActionButton
import com.mahmoudalim.onboarding_presentation.composables.OnBoardingScreenScaffold
import com.mahmoudalim.onboarding_presentation.composables.SelectableButton
import kotlinx.coroutines.flow.Flow

/**
 * @author Mahmoud Alim on 26/08/2022.
 */

@Composable
fun GenderScreen(
    viewModel: GenderViewModel = hiltViewModel(),
    onNextClick: () -> Unit,
) {
    ObserveUIEvents(
        uiEvent = viewModel.uiEvent,
        onNextClick = onNextClick,
    )

    OnBoardingScreenScaffold {
        val spacing = LocalSpacing.current
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.whats_your_gender),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.height(spacing.spaceLarge))

            MaleFemaleButtonsRow(viewModel)
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
private fun MaleFemaleButtonsRow(viewModel: GenderViewModel) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(LocalSpacing.current.spaceMedium)
    ) {
        SelectableButton(
            text = stringResource(id = R.string.male),
            onClick = { viewModel.onGenderChanged(Gender.Male) },
            color = MaterialTheme.colors.primaryVariant,
            isSelected = viewModel.selectedGender is Gender.Male,
            textStyle = MaterialTheme.typography.button.copy(
                fontWeight = FontWeight.Normal
            )
        )

        SelectableButton(
            text = stringResource(id = R.string.female),
            onClick = { viewModel.onGenderChanged(Gender.Female) },
            color = MaterialTheme.colors.primaryVariant,
            isSelected = viewModel.selectedGender is Gender.Female,
            textStyle = MaterialTheme.typography.button.copy(
                fontWeight = FontWeight.Normal
            )
        )
    }
}


@Composable
private fun ObserveUIEvents(
    uiEvent: Flow<UiEvent>,
    onNextClick: () -> Unit,
) {
    LaunchedEffect(key1 = true) {
        uiEvent.collect { event ->
            when (event) {
                is UiEvent.OnNextClick -> onNextClick()
                else -> Unit
            }
        }
    }
}


