package com.mahmoudalim.tracker_presentation.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mahmoudalim.tracker_presentation.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * @author Mahmoud Alim on 24/10/2022.
 */

enum class DateFormat(val value: String) {
    DD_MMMM_YYYY("dd MMMM yyyy"),
    DD_MMMM("dd MMMM")
}

@Composable
fun LocalDate.formatDateText(format: DateFormat = DateFormat.DD_MMMM): String {
    val today = LocalDate.now()
    return when (this) {
        today -> stringResource(id = R.string.yesterday)
        today.minusDays(1) -> stringResource(id = R.string.yesterday)
        today.plusDays(1) -> stringResource(id = R.string.tomorrow)
        else -> DateTimeFormatter.ofPattern(format.value).format(this)
    }

}