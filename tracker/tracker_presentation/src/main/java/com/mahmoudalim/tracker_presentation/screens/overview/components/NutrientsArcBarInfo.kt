package com.mahmoudalim.tracker_presentation.screens.overview.components

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
    strokeWidth: Dp = 8.dp,
) {
    val background = MaterialTheme.colors.background
    val goalExceededColor = MaterialTheme.colors.error
    val angleRatio = remember { Animatable(0f) }
    LaunchedEffect(key1 = nutrientValue) {
        angleRatio.animateTo(
            targetValue = if (goal > 0) {
                nutrientValue / goal.toFloat()
            } else 0f,
            animationSpec = tween(
                durationMillis = 300
            )
        )
    }
    ArcChartView(
        modifier = modifier,
        nutrientValue = nutrientValue,
        goal = goal,
        background = background,
        goalExceededColor = goalExceededColor,
        strokeWidth = strokeWidth,
        color = color,
        angleRatio = angleRatio,
    ) {
        ArcInsideData(
            nutrientValue = nutrientValue,
            goal = goal,
            goalExceededColor = goalExceededColor,
            name = name
        )
    }
}

@Composable
private fun ArcChartView(
    modifier: Modifier,
    nutrientValue: Int,
    goal: Int,
    background: Color,
    goalExceededColor: Color,
    strokeWidth: Dp,
    color: Color,
    angleRatio: Animatable<Float, AnimationVector1D>,
    content: @Composable() () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
        ) {
            drawArc(
                color = if (nutrientValue <= goal) background else goalExceededColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                size = size,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
            if (nutrientValue <= goal) {
                drawArc(
                    color = color,
                    startAngle = 90f,
                    sweepAngle = 360f * angleRatio.value,
                    useCenter = false,
                    size = size,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            }
        }
        content()
    }
}

@Composable
private fun ArcInsideData(
    nutrientValue: Int,
    goal: Int,
    goalExceededColor: Color,
    name: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UnitDisplay(
            amount = nutrientValue,
            unit = stringResource(id = R.string.grams),
            amountColor = if (nutrientValue <= goal) {
                MaterialTheme.colors.onPrimary
            } else goalExceededColor,
            unitColor = if (nutrientValue <= goal) {
                MaterialTheme.colors.onPrimary
            } else goalExceededColor
        )
        Text(
            text = name,
            color = if (nutrientValue <= goal) {
                MaterialTheme.colors.onPrimary
            } else goalExceededColor,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Light
        )
    }
}