package com.mahmoudalim.onboarding_presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.mahmoudalim.core_ui.LocalSpacing
import com.mahmoudalim.core_ui.bounceClick
import com.mahmoudalim.core_ui.clickableNoRipple

/**
 * @author Mahmoud Alim on 26/08/2022.
 */

@Composable
fun SelectableButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    color: Color,
    selectedTextColor: Color = Color.White,
    textStyle: TextStyle = MaterialTheme.typography.button
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .bounceClick()
            .clickableNoRipple { onClick() }
            .background(
                color = if (isSelected) color else Color.Transparent,
                shape = RoundedCornerShape(100.dp)
            )
            .border(
                width = 2.dp,
                color = color,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(LocalSpacing.current.spaceMedium)
    ) {
        Text(
            text = text,
            style = textStyle,
            color = if (isSelected) selectedTextColor else color,
        )

    }

}