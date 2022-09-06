package com.mahmoudalim.onboarding_domain.usecase

import com.mahmoudalim.core.util.UiText
import com.mahmoudalim.onboarding_domain.R

/**
 * @author Mahmoud Alim on 27/08/2022.
 */
class ValidateNutrients {

    operator fun invoke(
        carbRatioText: String,
        proteinRatioText: String,
        fatRatioText: String
    ): Result {
        val carbRatio = carbRatioText.toIntOrNull()
        val proteinRatio = proteinRatioText.toIntOrNull()
        val fatRatio = fatRatioText.toIntOrNull()
        if (carbRatio == null || proteinRatio == null || fatRatio == null)
            return Result.Failure(
                UiText.StringResources(R.string.error_invalid_values)
            )
        if (carbRatio + proteinRatio + fatRatio != 100) {
            return Result.Failure(UiText.StringResources(R.string.error_not_100_percent))
        }

        return Result.Success(
            carbRatio = carbRatio / 100f,
            proteinRatio = proteinRatio / 100f,
            fatRatio = fatRatio / 100f
        )
    }

    sealed class Result {
        data class Success(val carbRatio: Float, val proteinRatio: Float, val fatRatio: Float) :
            Result()

        data class Failure(val message: UiText) : Result()
    }
}