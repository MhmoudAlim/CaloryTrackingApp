package com.mahmoudalim.onboarding_presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.mahmoudalim.core.R
import com.mahmoudalim.core_ui.LocalSpacing

/**
 * Created by Mahmoud Alim on 05/02/2022.
 */

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.next),
    iseEnabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.button,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(backgroundColor = Color.Magenta),
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = iseEnabled,
        shape = RoundedCornerShape(60.dp),
        colors = buttonColors
    ) {
        Text(
            text = text,
            style = textStyle,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(LocalSpacing.current.spaceSmall)
        )
    }
}