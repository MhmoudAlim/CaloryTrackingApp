package com.mahmoudalim.onboarding_presentation.welcome.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.mahmoudalim.core_ui.LocalSpacing

/**
 * @author Mahmoud Alim on 23/08/2022.
 */

@Composable
fun UnitTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    unit: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colors.primaryVariant,
        fontSize = 70.sp
    )
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChanged,
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .alignByBaseline()
        )
        Text(
            text = unit,
            modifier = Modifier.alignByBaseline()
        )
    }

}