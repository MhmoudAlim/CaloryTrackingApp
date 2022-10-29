package com.mahmoudalim.tracker_presentation.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mahmoudalim.core.util.UiEvent
import com.mahmoudalim.core_ui.LocalSpacing
import com.mahmoudalim.tracker_domain.model.MealType
import com.mahmoudalim.tracker_presentation.R
import com.mahmoudalim.tracker_presentation.screens.search.components.SearchTextField
import com.mahmoudalim.tracker_presentation.screens.search.components.TrackableFoodItem
import com.mahmoudalim.tracker_presentation.screens.search.state.SearchState
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

/**
 * @author Mahmoud Alim on 26/10/2022.
 */
@OptIn(ExperimentalComposeUiApi::class)
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
    val keyboardController = LocalSoftwareKeyboardController.current

    ObserveUiState(
        uiEvent = viewModel.uiEvent,
        onNavigateUp = onNavigateUp,
        scaffoldState = scaffoldState,
        keyboardController = keyboardController
    )

    ScreenScaffold(state) {
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
                shouldShowHint = state.isHintVisible,
                onValueChanged = {
                    viewModel.onEvent(SearchEvent.OnQueryChange(it))
                },
                onFocusChanged = {
                    viewModel.onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))
                },
                onSearch = {
                    keyboardController?.hide()
                    viewModel.onEvent(SearchEvent.OnSearch)
                }
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = spacing.spaceSmall)
            ) {
                items(state.trackableFoods) { foodUiState ->
                    TrackableFoodItem(
                        trackableFoodUiState = foodUiState,
                        onClick = {
                            viewModel.onEvent(
                                SearchEvent.OnToggleTrackableFood(
                                    food = foodUiState.food,
                                )
                            )
                        },
                        onAmountChange = {
                            viewModel.onEvent(
                                SearchEvent.OnAmountForFoodChange(
                                    amount = it,
                                    food = foodUiState.food
                                )
                            )
                        },
                        onTrack = {
                            keyboardController?.hide()
                            viewModel.onEvent(
                                SearchEvent.OnTrackFoodClick(
                                    mealType = MealType.fromString(mealName),
                                    food = foodUiState.food,
                                    date = LocalDate.of(year, month, dayOfMonth)
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun ScreenScaffold(state: SearchState, content: @Composable() () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isSearching -> CircularProgressIndicator()
            state.trackableFoods.isEmpty() -> Text(
                text = stringResource(R.string.no_results),
                style = MaterialTheme.typography.body1
            )
        }
        content()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ObserveUiState(
    uiEvent: Flow<UiEvent>,
    onNavigateUp: () -> Unit,
    scaffoldState: ScaffoldState,
    keyboardController: SoftwareKeyboardController?
) {
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