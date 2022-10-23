package com.mahmoudalim.tracker_presentation.screens.overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.mahmoudalim.core.util.UiEvent
import com.mahmoudalim.core_ui.LocalSpacing
import com.mahmoudalim.tracker_presentation.screens.overview.composables.NutrientsHeader

/**
 * @author Mahmoud Alim on 23/10/2022.
 */

@Composable
fun TrackerOverViewViewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TrackerOverViewViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
        //TODO: revest state type (savedStateHandle)
    val state = viewModel.state
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientsHeader(state = state.value)
        }

    }

}