package com.mahmoudalim.tracker_presentation.screens.overview.composables

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
import com.mahmoudalim.core_ui.LocalSpacing
import com.mahmoudalim.core_ui.bounceClick
import com.mahmoudalim.tracker_presentation.R
import com.mahmoudalim.tracker_presentation.components.NutrientItemInfo
import com.mahmoudalim.tracker_presentation.components.UnitDisplay
import com.mahmoudalim.tracker_presentation.screens.overview.model.Meal
import com.mahmoudalim.tracker_presentation.screens.overview.model.UnitDisplayData

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
        Row(modifier = Modifier
            .fillMaxWidth()
            .bounceClick()
            .clickable { onToggleClick() }
            .padding(spacing.spaceMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = meal.drawableRes),
                contentDescription = meal.name.asString(context)
            )
            Spacer(modifier = Modifier.width(spacing.spaceMedium))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = meal.name.asString(context),
                        style = MaterialTheme.typography.h3
                    )
                    Icon(
                        imageVector = if (meal.isExpanded)
                            Icons.Default.KeyboardArrowUp
                        else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (meal.isExpanded)
                            stringResource(id = R.string.collapse)
                        else stringResource(id = R.string.extend)
                    )

                }
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    UnitDisplay(
                        data = UnitDisplayData(
                            unit = stringResource(id = R.string.kcal),
                            amount = meal.calories,
                            amountTextSize = 28.sp,
                            unitColor = MaterialTheme.colors.onPrimary,
                            amountColor = MaterialTheme.colors.onPrimary,
                        )
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
                    ) {
                        NutrientItemInfo(
                            name = stringResource(id = R.string.carbs),
                            data = UnitDisplayData(
                                unit = stringResource(id = R.string.grams),
                                amount = meal.carbs,
                                amountTextSize = 28.sp,
                                unitColor = MaterialTheme.colors.onPrimary,
                                amountColor = MaterialTheme.colors.onPrimary,
                            )
                        )
                        NutrientItemInfo(
                            name = stringResource(id = R.string.protein),
                            data = UnitDisplayData(
                                unit = stringResource(id = R.string.grams),
                                amount = meal.protein,
                                amountTextSize = 28.sp,
                                unitColor = MaterialTheme.colors.onPrimary,
                                amountColor = MaterialTheme.colors.onPrimary,
                            )
                        )
                        NutrientItemInfo(
                            name = stringResource(id = R.string.fat),
                            data = UnitDisplayData(
                                unit = stringResource(id = R.string.grams),
                                amount = meal.fat,
                                amountTextSize = 28.sp,
                                unitColor = MaterialTheme.colors.onPrimary,
                                amountColor = MaterialTheme.colors.onPrimary,
                            )
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        AnimatedVisibility(visible = meal.isExpanded) {
            content()
        }
    }
}