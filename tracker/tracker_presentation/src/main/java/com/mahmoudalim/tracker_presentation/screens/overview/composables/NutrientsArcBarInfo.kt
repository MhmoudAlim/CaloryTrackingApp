package com.mahmoudalim.tracker_presentation.screens.overview.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mahmoudalim.tracker_presentation.R
import com.mahmoudalim.tracker_presentation.components.UnitDisplay


/**
 * @author Mahmoud Alim on 23/10/2022.
 */

@Composable
fun NutrientsArcBarInfo(
    nutrientValue: Int,
    goal: Int,
    name: String,
    color: Color,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 10.dp,
    nutrientUnit: String = stringResource(R.string.grams),
) {
    val backgroundColor = MaterialTheme.colors.background
    val goalExceededColor = MaterialTheme.colors.error
    val angelRatio = remember { Animatable(0f) }
    LaunchedEffect(nutrientValue) {
        angelRatio.animateTo(
            targetValue = if (goal > 0) nutrientValue / goal.toFloat() else 0f,
            animationSpec = tween(300)
        )
    }
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        BarArc(
            nutrientValue = nutrientValue,
            goal = goal,
            goalExceededColor = goalExceededColor,
            backgroundColor = backgroundColor,
            strokeWidth = strokeWidth,
            angelRatio = angelRatio
        )
        BarTextValues(
            nutrientValue = nutrientValue,
            goal = goal,
            goalExceededColor = goalExceededColor,
            nutrientUnit = nutrientUnit,
            backgroundColor = backgroundColor,
            name = name
        )
    }
}

@Composable
private fun BarTextValues(
    nutrientValue: Int,
    goal: Int,
    goalExceededColor: Color,
    nutrientUnit: String,
    backgroundColor: Color,
    name: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val textColor =
            if (nutrientValue > goal) goalExceededColor else MaterialTheme.colors.onPrimary
        UnitDisplay(
                unit = nutrientUnit,
                amountColor = textColor,
                amount = nutrientValue,
                unitColor = backgroundColor,
        )
        Text(
            text = name,
            color = textColor,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun BarArc(
    nutrientValue: Int,
    goal: Int,
    goalExceededColor: Color,
    backgroundColor: Color,
    strokeWidth: Dp,
    angelRatio: Animatable<Float, AnimationVector1D>
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        drawArc(
            color = if (nutrientValue > goal) goalExceededColor else backgroundColor,
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            size = size,
            style = Stroke(
                width = strokeWidth.toPx(),
                cap = StrokeCap.Round
            )
        )
        if (nutrientValue > goal) {
            drawArc(
                color = backgroundColor,
                startAngle = 90f,
                sweepAngle = 360f * angelRatio.value,
                useCenter = false,
                size = size,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
    }
}