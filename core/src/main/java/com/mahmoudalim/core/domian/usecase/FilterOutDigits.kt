package com.mahmoudalim.core.domian.usecase

/**
 * @author Mahmoud Alim on 25/08/2022.
 */
class FilterOutDigits {

    operator fun invoke(text: String): String {
        return text.filter { it.isDigit() }
    }

    fun maxNumberIsEqualTo(number: Int, validator: Int): Boolean {
        return number >= validator
    }
}