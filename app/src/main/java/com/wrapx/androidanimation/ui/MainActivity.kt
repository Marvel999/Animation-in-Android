package com.wrapx.androidanimation.ui

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.wrapx.androidanimation.BaseActivity
import com.wrapx.androidanimation.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    private var binding:ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        binding?.run {
            animatorBtn.setOnClickListener {
                showActivity(AnimationType.ANIMATOR)
            }
            vectorBtn.setOnClickListener {
                showActivity(AnimationType.VECTOR_ANIMATION)
            }
            transitionBtn.setOnClickListener {
                showActivity(AnimationType.TRANSITION_ANIMATION)
            }
            motionBtn.setOnClickListener {
                showActivity(AnimationType.MOTION_ANIMATION)
            }
            physicsBasedAnimationBtn.setOnClickListener {
                showActivity(AnimationType.PHYSICS_ANIMATION)
            }
        }
    }

   private fun showActivity(animatorType:AnimationType){
        var intent:Intent? = null;
        val animationBundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
       intent = when(animatorType){
           AnimationType.ANIMATOR -> Intent(this, AnimatorActivity::class.java)
           AnimationType.VECTOR_ANIMATION -> Intent(this, VectorAnimation::class.java)
           AnimationType.TRANSITION_ANIMATION -> Intent(this, TransitionAnimation::class.java)
           AnimationType.MOTION_ANIMATION -> Intent(this, MotionLayout::class.java)
           AnimationType.PHYSICS_ANIMATION -> Intent(this, PhysicsBasedAnimation::class.java)
       }
        startActivity(intent, animationBundle)
    }

   private enum class AnimationType{
        ANIMATOR,
        VECTOR_ANIMATION,
        TRANSITION_ANIMATION,
        MOTION_ANIMATION,
       PHYSICS_ANIMATION
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}