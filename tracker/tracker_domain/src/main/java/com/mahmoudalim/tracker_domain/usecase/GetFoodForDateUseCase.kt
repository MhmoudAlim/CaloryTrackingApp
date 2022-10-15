package com.mahmoudalim.tracker_domain.usecase

import com.mahmoudalim.tracker_domain.repository.TrackerRepo
import java.time.LocalDate

/**
 * @author Mahmoud Alim on 14/10/2022.
 */
class GetFoodForDateUseCase(private val repo: TrackerRepo) {

    operator fun invoke(date: LocalDate) = repo.getFoodForDate(date)

}