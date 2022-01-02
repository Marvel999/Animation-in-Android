package com.wrapx.androidanimation.ui

import android.animation.*
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.wrapx.androidanimation.BaseActivity
import com.wrapx.androidanimation.R
import com.wrapx.androidanimation.databinding.ActivityAnimatorBinding

class AnimatorActivity : BaseActivity() {
    var binding: ActivityAnimatorBinding? = null
    var valueAnimator: ValueAnimator? = null
    var objectAnimator: ValueAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAnimatorBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        valueAnimator = ValueAnimator.ofObject(
            ArgbEvaluator(),
            ContextCompat.getColor(this, R.color.purple_500),
            ContextCompat.getColor(this, R.color.teal_200)
        ).apply {
            duration = 1000
            repeatCount = 100
            repeatMode = ValueAnimator.REVERSE
            addUpdateListener {
                binding?.view?.setBackgroundColor(it.animatedValue as Int)
            }
        }

        val translationPropertyValuesHolder =
            PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, -100F, 0F)
        val rotateAnimationPropertyValuesHolder =
            PropertyValuesHolder.ofFloat(View.ROTATION, 0F, 360F)

        objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
            binding?.view,
            translationPropertyValuesHolder,
            rotateAnimationPropertyValuesHolder
        ).apply {
            duration = 1000
            repeatCount = 2000
        }

    }

    override fun onStart() {
        super.onStart()

        AnimatorSet().apply {
            playTogether(valueAnimator, objectAnimator)
        }.start()
     }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}