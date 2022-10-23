package com.mahmoudalim.tracker_presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.mahmoudalim.core_ui.LocalSpacing

/**
 * @author Mahmoud Alim on 17/10/2022.
 */

@Composable
fun UnitDisplay(
    data: UnitDisplayData,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(LocalSpacing.current.spaceExtraSmall)
    ) {
        Text(
            text = data.amount.toString(),
            color = data.amountColor,
            fontSize = data.amountTextSize,
            style = MaterialTheme.typography.h1,
            modifier = Modifier.alignBy(LastBaseline)
        )

        Text(
            text = data.unit,
            color = data.unitColor,
            fontSize = data.unitTextSize,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.alignBy(LastBaseline)
        )
    }
}

data class UnitDisplayData(
    val unit: String,
    val amount: Int,
    val amountTextSize: TextUnit = 20.sp,
    val amountColor: Color,
    val unitTextSize: TextUnit = 14.sp,
    val unitColor: Color,
)