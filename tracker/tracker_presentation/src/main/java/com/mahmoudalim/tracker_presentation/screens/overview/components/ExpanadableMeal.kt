package com.mahmoudalim.tracker_presentation.screens.overview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.mahmoudalim.core.util.UiText
import com.mahmoudalim.core_ui.LocalSpacing
import com.mahmoudalim.core_ui.pressClickEffect
import com.mahmoudalim.tracker_presentation.R
import com.mahmoudalim.tracker_presentation.components.NutrientItemInfo
import com.mahmoudalim.tracker_presentation.components.UnitDisplay
import com.mahmoudalim.tracker_presentation.screens.overview.state.Meal

/**
 * @author Mahmoud Alim on 24/10/2022.
 */
@Composable
fun ExpandableMeal(
    meal: Meal,
    modifier: Modifier = Modifier,
    onToggleClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    Column(
        modifier = modifier
    ) {
        MealHeaderTitle(meal.name)
        Row(modifier = Modifier
            .fillMaxWidth()
            .pressClickEffect()
            .clickable { onToggleClick() }
            .padding(spacing.spaceMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MealImage(meal.drawableRes, meal.name)
            Spacer(modifier = Modifier.width(spacing.spaceMedium))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                MealInfoExpandIcon(meal.isExpanded)
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    UnitDisplay(
                        unit = stringResource(id = R.string.kcal),
                        amount = meal.calories,
                        amountTextSize = 28.sp,
                        unitColor = MaterialTheme.colors.onPrimary,
                        amountColor = MaterialTheme.colors.onPrimary,
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
                    ) {
                        NutrientItemInfo(
                            name = stringResource(id = R.string.carbs),
                            unit = stringResource(id = R.string.grams),
                            amount = meal.carbs,
                            amountTextSize = 28.sp,
                            unitColor = MaterialTheme.colors.onPrimary,
                            amountColor = MaterialTheme.colors.onPrimary,
                        )
                        NutrientItemInfo(
                            name = stringResource(id = R.string.protein),
                            unit = stringResource(id = R.string.grams),
                            amount = meal.protein,
                            amountTextSize = 28.sp,
                            unitColor = MaterialTheme.colors.onPrimary,
                            amountColor = MaterialTheme.colors.onPrimary,

                            )
                        NutrientItemInfo(
                            name = stringResource(id = R.string.fat),
                            unit = stringResource(id = R.string.grams),
                            amount = meal.fat,
                            amountTextSize = 28.sp,
                            unitColor = MaterialTheme.colors.onPrimary,
                            amountColor = MaterialTheme.colors.onPrimary,
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        AnimatedVisibility(
            visible = meal.isExpanded,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            content()
        }
        if (meal.isExpanded) Spacer(modifier = Modifier.height(spacing.spaceMedium))

    }
}

@Composable
private fun MealInfoExpandIcon(isExpanded: Boolean) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = if (isExpanded)
                Icons.Default.KeyboardArrowUp
            else Icons.Default.KeyboardArrowDown,
            contentDescription = if (isExpanded)
                stringResource(id = R.string.collapse)
            else stringResource(id = R.string.extend)
        )
    }
}

@Composable
private fun MealImage(
    mealDrawable: Int,
    mealName: UiText
) {
    val context = LocalContext.current
    Image(
        painterResource(id = mealDrawable),
        contentDescription = mealName.asString(context)
    )
}

@Composable
private fun MealHeaderTitle(
    mealName: UiText,
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = spacing.spaceSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = mealName.asString(context),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onBackground.copy(alpha = .6f)
        )
    }
}