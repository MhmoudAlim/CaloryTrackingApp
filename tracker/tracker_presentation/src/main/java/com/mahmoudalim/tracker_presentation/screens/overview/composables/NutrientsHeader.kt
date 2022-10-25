package com.mahmoudalim.tracker_presentation.screens.overview.composables

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmoudalim.core_ui.CarbColor
import com.mahmoudalim.core_ui.FatColor
import com.mahmoudalim.core_ui.LocalSpacing
import com.mahmoudalim.core_ui.ProteinColor
import com.mahmoudalim.tracker_presentation.R
import com.mahmoudalim.tracker_presentation.components.UnitDisplay
import com.mahmoudalim.tracker_presentation.screens.overview.model.TrackerOverViewState
import com.mahmoudalim.tracker_presentation.screens.overview.model.UnitDisplayData

/**
 * @author Mahmoud Alim on 23/10/2022.
 */

@Composable
fun NutrientsHeader(
    state: TrackerOverViewState,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 42.dp, bottomStart = 42.dp))
            .background(MaterialTheme.colors.primary)
            .padding(horizontal = spacing.spaceLarge, vertical = spacing.spaceExtraLarge)
    ) {
        CaloriesCounterView(state.totalCalories)
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        NutrientsHorizontalBar(
            carbs = state.totalCarbs,
            fat = state.totalFat,
            protein = state.totalProtein,
            calories = state.totalCalories,
            caloriesGoal = state.caloriesGoal,
            modifier = Modifier
                .fillMaxWidth()
                .height(spacing.spaceLarge)
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        NutrientsArcBarInfoRow(state)
    }
}

@Composable
private fun NutrientsArcBarInfoRow(state: TrackerOverViewState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NutrientsArcBarInfo(
            nutrientValue = state.totalCarbs,
            goal = state.carbsGoal,
            name = stringResource(id = R.string.carbs),
            color = CarbColor,
            modifier = Modifier.size(90.dp)
        )
        NutrientsArcBarInfo(
            nutrientValue = state.totalProtein,
            goal = state.proteinGoal,
            name = stringResource(id = R.string.protein),
            color = ProteinColor,
            modifier = Modifier.size(90.dp)
        )
        NutrientsArcBarInfo(
            nutrientValue = state.totalFat,
            goal = state.fatGoal,
            name = stringResource(id = R.string.fat),
            color = FatColor,
            modifier = Modifier.size(90.dp)
        )
    }
}

@Composable
private fun CaloriesCounterView(totalCalories: Int) {
    val animatedCaloriesCount = animateIntAsState(targetValue = totalCalories)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        UnitDisplay(
            data = UnitDisplayData(
                amount = animatedCaloriesCount.value,
                unit = stringResource(id = R.string.kcal),
                amountColor = MaterialTheme.colors.onPrimary,
                amountTextSize = 32.sp,
                unitColor = MaterialTheme.colors.onPrimary
            ),
            modifier = Modifier.align(Bottom)
        )
        Column {
            Text(
                text = stringResource(id = R.string.your_goal),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onPrimary,
            )
            UnitDisplay(
                data = UnitDisplayData(
                    amount = animatedCaloriesCount.value,
                    unit = stringResource(id = R.string.kcal),
                    amountColor = MaterialTheme.colors.onPrimary,
                    amountTextSize = 32.sp,
                    unitColor = MaterialTheme.colors.onPrimary,
                )
            )
        }
    }
}