package com.mahmoudalim.core.domian.model

/**
 * Created by Mahmoud Alim on 18/05/2022.
 */

sealed class GoalType(val name: String) {
    object LoseWeight : GoalType(Type.LOSE.value)
    object KeepWeight : GoalType(Type.KEEP.value)
    object GainWeight : GoalType(Type.GAIN.value)

    enum class Type(val value: String) {
        LOSE("lose"),
        KEEP("keep"),
        GAIN("gain")
    }

    companion object {
        fun fromString(name: String): GoalType {
            return when (name) {
                Type.LOSE.value -> LoseWeight
                Type.KEEP.value -> KeepWeight
                Type.GAIN.value -> GainWeight
                else -> KeepWeight
            }
        }
    }
}