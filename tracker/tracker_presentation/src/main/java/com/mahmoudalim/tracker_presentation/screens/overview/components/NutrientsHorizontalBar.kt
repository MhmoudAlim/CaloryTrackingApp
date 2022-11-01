package com.mahmoudalim.tracker_presentation.screens.overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import com.mahmoudalim.core.NutrientsFacts.CARBS_CALORY_PER_GRAM
import com.mahmoudalim.core.NutrientsFacts.FAT_CALORY_PER_GRAM
import com.mahmoudalim.core.NutrientsFacts.PROTEIN_CALORY_PER_GRAM
import com.mahmoudalim.core_ui.CarbColor
import com.mahmoudalim.core_ui.FatColor
import com.mahmoudalim.core_ui.ProteinColor

/**
 * @author Mahmoud Alim on 17/10/2022.
 */

@Composable
fun NutrientsHorizontalBar(
    carbs: Int,
    fat: Int,
    protein: Int,
    calories: Int,
    caloriesGoal: Int,
    modifier: Modifier = Modifier
) {
    val background = MaterialTheme.colors.background
    val caloryExceededColor = MaterialTheme.colors.error
    val cornerRadiusValue = CornerRadius(40f)
    val carbWithRatio = remember { Animatable(0f) }
    val proteinWithRatio = remember { Animatable(0f) }
    val fatWithRatio = remember { Animatable(0f) }
    LaunchedEffect(carbs) {
        carbWithRatio.animateTo(targetValue = (carbs * CARBS_CALORY_PER_GRAM) / caloriesGoal)
        proteinWithRatio.animateTo(targetValue = (protein * PROTEIN_CALORY_PER_GRAM) / caloriesGoal)
        fatWithRatio.animateTo(targetValue = (fat * FAT_CALORY_PER_GRAM) / caloriesGoal)
    }
    Canvas(
        modifier = modifier
    ) {
        if (calories > caloriesGoal) {
            drawRoundRect(
                color = caloryExceededColor,
                size = size,
                cornerRadius = cornerRadiusValue
            )
        } else {
            val fatWidth = proteinWithRatio.value * size.width
            val carbsWidth = carbWithRatio.value * size.width
            val proteinWidth = fatWithRatio.value * size.width
            drawRoundRect(
                color = background,
                size = size,
                cornerRadius = cornerRadiusValue
            )
            drawRoundRect(
                color = FatColor,
                size = Size(width = fatWidth + carbsWidth + proteinWidth, height = size.height),
                cornerRadius = cornerRadiusValue
            )
            drawRoundRect(
                color = ProteinColor,
                size = Size(width = carbsWidth + proteinWidth, height = size.height),
                cornerRadius = cornerRadiusValue
            )
            drawRoundRect(
                color = CarbColor,
                size = Size(width = carbsWidth, height = size.height),
                cornerRadius = cornerRadiusValue
            )
        }
    }
}



