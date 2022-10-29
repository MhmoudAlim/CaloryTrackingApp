package com.mahmoudalim.tracker_presentation.screens.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.mahmoudalim.core_ui.LocalSpacing
import com.mahmoudalim.tracker_presentation.R
import com.mahmoudalim.tracker_presentation.components.NutrientItemInfo
import com.mahmoudalim.tracker_presentation.screens.search.state.TrackableFoodUiState

/**
 * @author Mahmoud Alim on 27/10/2022.
 */


@Composable
fun TrackableFoodItem(
    trackableFoodUiState: TrackableFoodUiState,
    onClick: () -> Unit,
    onAmountChange: (String) -> Unit,
    onTrack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val food = trackableFoodUiState.food
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .padding(spacing.spaceExtraSmall)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .background(MaterialTheme.colors.surface)
            .clickable { onClick() }
            .padding(end = spacing.spaceMedium)
    ) {
        FoodItemImage(
            name = food.name,
            imageUrl = food.imageUrl
        )
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
        ) {
            FoodItemNameAndMeta(
                name = food.name,
                caloriesPer100gm = food.caloriesPer100gm
            )
            FoodItemNutritionData(
                carbsPer100gm = food.carbsPer100gm,
                proteinPer100gm = food.proteinPer100gm,
                fatPer100gm = food.fatPer100gm
            )
        }
    }
    FoodItemAmountAndCTA(
        amount = trackableFoodUiState.amount,
        isItemExpanded = trackableFoodUiState.isExpanded,
        onAmountChange = onAmountChange,
        onTrack = onTrack
    )
}


@Composable
private fun FoodItemAmountAndCTA(
    amount: String,
    isItemExpanded: Boolean,
    onAmountChange: (String) -> Unit,
    onTrack: () -> Unit,
) {
    val spacing = LocalSpacing.current
    AnimatedVisibility(visible = isItemExpanded) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = amount,
                onValueChange = onAmountChange,
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = {
                    onTrack()
                    defaultKeyboardAction(ImeAction.Done)
                }),
                keyboardOptions = KeyboardOptions(
                    imeAction = if (amount.isNotBlank())
                        ImeAction.Done else ImeAction.Default,
                    keyboardType = KeyboardType.Number,
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colors.primarySurface.copy(alpha = .1f))
                    .padding(spacing.spaceMedium)
                    .alignBy(LastBaseline)
            )
            Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
            Text(
                text = stringResource(id = R.string.grams),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.alignBy(LastBaseline)
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = onTrack,
                enabled = amount.isNotBlank()
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(id = R.string.track)
                )
            }
        }
    }
}

@Composable
private fun FoodItemNutritionData(
    carbsPer100gm: Int,
    proteinPer100gm: Int,
    fatPer100gm: Int,
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NutrientItemInfo(
            name = stringResource(id = R.string.carbs),
            amount = carbsPer100gm,
            unit = stringResource(id = R.string.grams),
            amountTextSize = 16.sp,
            unitTextSize = 12.sp,
            nameTextStyle = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.width(spacing.spaceSmall))
        NutrientItemInfo(
            name = stringResource(id = R.string.protein),
            amount = proteinPer100gm,
            unit = stringResource(id = R.string.grams),
            amountTextSize = 16.sp,
            unitTextSize = 12.sp,
            nameTextStyle = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.width(spacing.spaceSmall))
        NutrientItemInfo(
            name = stringResource(id = R.string.fat),
            amount = fatPer100gm,
            unit = stringResource(id = R.string.grams),
            amountTextSize = 16.sp,
            unitTextSize = 12.sp,
            nameTextStyle = MaterialTheme.typography.body2
        )
    }
}

@Composable
private fun FoodItemNameAndMeta(
    name: String,
    caloriesPer100gm: Int
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Text(
            text = stringResource(
                id = R.string.kcal_per_100g,
                caloriesPer100gm
            ),
            style = MaterialTheme.typography.body2
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun FoodItemImage(
    name: String,
    imageUrl: String?
) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                crossfade(true)
                error(R.drawable.ic_dinner)
                fallback(R.drawable.ic_dinner)
            }
        ),
        contentScale = ContentScale.Crop,
        contentDescription = name,
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 20.dp))
    )
}