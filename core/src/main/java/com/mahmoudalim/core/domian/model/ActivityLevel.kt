package com.mahmoudalim.core.domian.model

/**
 * Created by Mahmoud Alim on 18/05/2022.
 */

sealed class ActivityLevel(val name: String) {
    object Low : ActivityLevel(Type.LOW.value)
    object Medium : ActivityLevel(Type.MEDIUM.value)
    object High : ActivityLevel(Type.HIGH.value)

    enum class Type(val value: String) {
        LOW("low"),
        MEDIUM("medium"),
        HIGH("high")
    }

    companion object {
        fun fromString(name: Type): ActivityLevel {
            return when (name) {
                Type.LOW -> Low
                Type.MEDIUM -> Medium
                Type.HIGH -> High
            }
        }
    }
}