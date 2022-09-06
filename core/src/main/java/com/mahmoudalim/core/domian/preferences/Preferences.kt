package com.mahmoudalim.core.domian.preferences

import com.mahmoudalim.core.domian.model.ActivityLevel
import com.mahmoudalim.core.domian.model.Gender
import com.mahmoudalim.core.domian.model.GoalType
import com.mahmoudalim.core.domian.model.UserInfo

/**
 * Created by Mahmoud Alim on 18/05/2022.
 */

interface Preferences {

    fun saveGender(gender: Gender)
    fun saveAge(age: Int)
    fun saveWeight(weight: Float)
    fun saveHeight(height: Int)
    fun saveActivityLevel(level: ActivityLevel)
    fun saveGoalType(goalType: GoalType)
    fun saveCarbRatio(ratio: Float)
    fun saveProteinRatio(ratio: Float)
    fun saveFatRatio(ratio: Float)

    fun getUserInfo(): UserInfo

    companion object {

        const val PREF_NAME = "app_shared_pref"
        const val KEY_GENDER = "gender"
        const val KEY_AGE = "age"
        const val KEY_WEIGHT = "weight"
        const val KEY_HEIGHT = "height"
        const val KEY_ACTIVITY_LEVEL = "level"
        const val KEY_GOAL_TYPE = "goal"
        const val KEY_CARB_RATIO = "carb"
        const val KEY_PROTEIN_RATIO = "protein"
        const val KEY_FAT_RATIO = "fat"
    }
}