package com.mahmoudalim.core.data.preferences

import android.content.SharedPreferences
import com.mahmoudalim.core.domian.model.ActivityLevel
import com.mahmoudalim.core.domian.model.Gender
import com.mahmoudalim.core.domian.model.GoalType
import com.mahmoudalim.core.domian.model.UserInfo
import com.mahmoudalim.core.domian.preferences.Preferences
import com.mahmoudalim.core.domian.preferences.Preferences.Companion.KEY_ACTIVITY_LEVEL
import com.mahmoudalim.core.domian.preferences.Preferences.Companion.KEY_AGE
import com.mahmoudalim.core.domian.preferences.Preferences.Companion.KEY_CARB_RATIO
import com.mahmoudalim.core.domian.preferences.Preferences.Companion.KEY_FAT_RATIO
import com.mahmoudalim.core.domian.preferences.Preferences.Companion.KEY_GENDER
import com.mahmoudalim.core.domian.preferences.Preferences.Companion.KEY_GOAL_TYPE
import com.mahmoudalim.core.domian.preferences.Preferences.Companion.KEY_HEIGHT
import com.mahmoudalim.core.domian.preferences.Preferences.Companion.KEY_PROTEIN_RATIO
import com.mahmoudalim.core.domian.preferences.Preferences.Companion.KEY_SHOW_ONBOARDING
import com.mahmoudalim.core.domian.preferences.Preferences.Companion.KEY_WEIGHT

/**
 * Created by Mahmoud Alim on 19/05/2022.
 */
class DefaultPreferences(
    private val sharedPref: SharedPreferences
) : Preferences {
    override fun saveGender(gender: Gender) {
        sharedPref.edit()
            .putString(KEY_GENDER, gender.name)
            .apply()
    }

    override fun saveAge(age: Int) {
        sharedPref.edit()
            .putInt(KEY_AGE, age)
            .apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPref.edit()
            .putFloat(KEY_WEIGHT, weight)
            .apply()
    }

    override fun saveHeight(height: Int) {
        sharedPref.edit()
            .putInt(KEY_HEIGHT, height)
            .apply()
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        sharedPref.edit()
            .putString(KEY_ACTIVITY_LEVEL, level.name)
            .apply()
    }

    override fun saveGoalType(goalType: GoalType) {
        sharedPref.edit()
            .putString(KEY_GOAL_TYPE, goalType.name)
            .apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(KEY_CARB_RATIO, ratio)
            .apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(KEY_PROTEIN_RATIO, ratio)
            .apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(KEY_FAT_RATIO, ratio)
            .apply()
    }

    override fun getUserInfo(): UserInfo {
        val gender = sharedPref.getString(KEY_GENDER, null) ?: Gender.Type.MALE.value
        val activityLevel =
            sharedPref.getString(KEY_ACTIVITY_LEVEL, null) ?: ActivityLevel.Type.MEDIUM.value
        val goalType = sharedPref.getString(KEY_GOAL_TYPE, null) ?: GoalType.Type.KEEP.value
        return UserInfo(
            gender = Gender.fromString(gender),
            age = sharedPref.getInt(KEY_GENDER, 1),
            weight = sharedPref.getFloat(KEY_WEIGHT, 1f),
            height = sharedPref.getInt(KEY_HEIGHT, 1),
            activityLevel = ActivityLevel.fromString(activityLevel),
            goalType = GoalType.fromString(goalType),
            carbRatio = sharedPref.getFloat(KEY_CARB_RATIO, 1f),
            proteinRatio = sharedPref.getFloat(KEY_PROTEIN_RATIO, 1f),
            fatRatio = sharedPref.getFloat(KEY_FAT_RATIO, 1f),
        )
    }

    override fun saveShouldShowOnboarding(shouldShowOnboarding: Boolean) {
        sharedPref.edit()
            .putBoolean(KEY_SHOW_ONBOARDING, shouldShowOnboarding)
            .apply()
    }

    override fun loadShouldShowOnboarding(): Boolean {
        return sharedPref.getBoolean(KEY_SHOW_ONBOARDING, true)
    }
}