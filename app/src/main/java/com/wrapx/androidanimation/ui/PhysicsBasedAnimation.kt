package com.wrapx.androidanimation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
import androidx.dynamicanimation.animation.SpringForce.STIFFNESS_MEDIUM
import com.wrapx.androidanimation.databinding.ActivityPhysicsBasedAnimationBinding
import com.wrapx.androidanimation.utils.springAnimationOf
import com.wrapx.androidanimation.utils.withSpringForceProperties

class PhysicsBasedAnimation : AppCompatActivity() {

    private var binding:ActivityPhysicsBasedAnimationBinding? = null

    private val firstSpringAnimationX by lazy(LazyThreadSafetyMode.NONE) {
        createSpringAnimation(binding?.androidBot1!!, DynamicAnimation.TRANSLATION_X)
    }

    private val firstSpringAnimationY by lazy(LazyThreadSafetyMode.NONE) {
        createSpringAnimation(binding?.androidBot1!!, DynamicAnimation.TRANSLATION_Y)
    }

    private val secondSpringAnimationX by lazy(LazyThreadSafetyMode.NONE) {
        createSpringAnimation(binding?.androidBot2!!, DynamicAnimation.TRANSLATION_X)
    }

    private val secondSpringAnimationY by lazy(LazyThreadSafetyMode.NONE) {
        createSpringAnimation(binding?.androidBot2!!, DynamicAnimation.TRANSLATION_Y)
    }

    var lastX: Float = 0f
    var lastY: Float = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhysicsBasedAnimationBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupOnTouchListener()
    }

    private fun setupOnTouchListener() {

        firstSpringAnimationX.addUpdateListener { _, value, _ ->
            secondSpringAnimationX.animateToFinalPosition(value)
        }

        firstSpringAnimationY.addUpdateListener { _, value, _ ->
            secondSpringAnimationY.animateToFinalPosition(value)
        }

        binding?.androidBot?.setOnTouchListener { view, motionEvent ->

            if(motionEvent.action == MotionEvent.ACTION_MOVE) {

                val deltaX = motionEvent.rawX - lastX
                val deltaY = motionEvent.rawY - lastY

                view.translationX = deltaX + view.translationX
                view.translationY = deltaY + view.translationY

                firstSpringAnimationX.animateToFinalPosition(view.translationX)
                firstSpringAnimationY.animateToFinalPosition(view.translationY)

            }

            lastX = motionEvent.rawX
            lastY = motionEvent.rawY

            return@setOnTouchListener true
        }
    }

    private fun <K : View> createSpringAnimation(view: K, property: FloatPropertyCompat<K>): SpringAnimation {
        return view.springAnimationOf(property).withSpringForceProperties {
            stiffness = STIFFNESS_MEDIUM
            dampingRatio = DAMPING_RATIO_MEDIUM_BOUNCY
        }
    }
}