package com.mahmoudalim.tracker_presentation.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mahmoudalim.core.util.UiEvent
import com.mahmoudalim.core_ui.LocalSpacing
import com.mahmoudalim.tracker_presentation.R
import com.mahmoudalim.tracker_presentation.screens.search.components.SearchTextField
import kotlinx.coroutines.flow.Flow

/**
 * @author Mahmoud Alim on 26/10/2022.
 */
@Composable
fun SearchScreen(
    scaffoldState: ScaffoldState,
    viewModel: SearchViewModel = hiltViewModel(),
    mealName: String,
    dayOfMonth: Int,
    month: Int,
    year: Int,
    onNavigateUp: () -> Unit
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state

    ObserveUiState(viewModel.uiEvent, onNavigateUp, scaffoldState)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.add_meal, mealName),
            style = MaterialTheme.typography.h3
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        SearchTextField(
            text = state.query,
            onValueChanged = {
                viewModel.onEvent(SearchEvent.OnQueryChange(it))
            },
            onFocusChanged = {
                viewModel.onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))
            },
            onSearch = {
                viewModel.onEvent(SearchEvent.OnSearch)
            }
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ObserveUiState(
    uiEvent: Flow<UiEvent>,
    onNavigateUp: () -> Unit,
    scaffoldState: ScaffoldState,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    LaunchedEffect(key1 = keyboardController) {
        uiEvent.collect {
            when (it) {
                UiEvent.NavigateUp -> onNavigateUp()
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = it.message.asString(context)
                    )
                    keyboardController?.hide()
                }
                else -> Unit
            }
        }
    }
}