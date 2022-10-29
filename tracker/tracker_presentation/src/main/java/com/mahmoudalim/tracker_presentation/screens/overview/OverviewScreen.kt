package com.mahmoudalim.tracker_presentation.screens.overview

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mahmoudalim.core.util.UiEvent
import com.mahmoudalim.core_ui.LocalSpacing
import com.mahmoudalim.tracker_presentation.R
import com.mahmoudalim.tracker_presentation.components.AddButton
import com.mahmoudalim.tracker_presentation.screens.overview.components.DaySelector
import com.mahmoudalim.tracker_presentation.screens.overview.components.ExpandableMeal
import com.mahmoudalim.tracker_presentation.screens.overview.components.NutrientsHeader

/**
 * @author Mahmoud Alim on 23/10/2022.
 */

@Composable
fun OverViewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: OverViewViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.uiEvent.collect {
            when (it) {
                is UiEvent.Navigate -> onNavigate(it)
                else -> Unit
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientsHeader(state = state)
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            DaySelector(
                day = state.date,
                onPreviousDayClick = { viewModel.onEvent(OverViewEvent.OnPreviousDayClick) },
                onNextDayClick = { viewModel.onEvent(OverViewEvent.OnNextDayClick) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Divider()
        }
        items(state.meals) { meal ->
            ExpandableMeal(
                meal = meal,
                modifier = Modifier.fillMaxWidth(),
                onToggleClick = {
                    viewModel.onEvent(OverViewEvent.OnToggleMealClick(meal))
                }
            ) {
                AddButton(
                    text = "${stringResource(id = R.string.add)} ${meal.name.asString(context)}",
                    modifier = Modifier.fillMaxWidth(.7f),
                    onClick = {
                        viewModel.onEvent(
                            OverViewEvent.OnAddNewFoodClicked(meal)
                        )
                    }
                )
            }
            Divider()
        }
    }
}