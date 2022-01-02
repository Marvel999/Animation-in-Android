package com.wrapx.androidanimation.utils

import androidx.dynamicanimation.animation.FlingAnimation
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce

fun <K> K.flingAnimationOf(property: FloatPropertyCompat<K>): FlingAnimation {
    return FlingAnimation(this, property)
}

fun <K> K.springAnimationOf(
        property: FloatPropertyCompat<K>,
        finalPosition: Float = Float.NaN
): SpringAnimation {
    return if (finalPosition.isNaN()) {
        SpringAnimation(this, property)
    } else {
        SpringAnimation(this, property, finalPosition)
    }
}

inline fun SpringAnimation.withSpringForceProperties(func: SpringForce.() -> Unit): SpringAnimation {
    if (spring == null) {
        spring = SpringForce()
    }
    spring.func()
    return this
}