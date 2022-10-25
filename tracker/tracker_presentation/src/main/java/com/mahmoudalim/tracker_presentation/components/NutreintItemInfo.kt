package com.mahmoudalim.tracker_presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.mahmoudalim.tracker_presentation.screens.overview.model.UnitDisplayData

/**
 * @author Mahmoud Alim on 24/10/2022.
 */
@Composable
fun NutrientItemInfo(
    name: String,
    data: UnitDisplayData,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UnitDisplay(data = data)
        Text(
            text = name,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Light
        )
    }
}