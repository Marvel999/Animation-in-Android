package com.wrapx.androidanimation.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.wrapx.androidanimation.databinding.ActivityTransitionAnimationBinding

class TransitionAnimation : AppCompatActivity() {

    private lateinit var binding: ActivityTransitionAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.enterTransition = Slide(Gravity.BOTTOM).apply {
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }
        binding = ActivityTransitionAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.avatarImg.setOnClickListener {
            val option = ActivityOptions.makeSceneTransitionAnimation(this,binding.avatarImg,"avatarAnim")
            startActivity(Intent(this,TransitionAnimation2::class.java),option.toBundle())
        }

    }

}