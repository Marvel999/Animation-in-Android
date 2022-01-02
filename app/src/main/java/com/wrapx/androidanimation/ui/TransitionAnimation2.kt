package com.wrapx.androidanimation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wrapx.androidanimation.databinding.ActivityTransitionAnimation2Binding

class TransitionAnimation2 : AppCompatActivity() {

    private var binding:ActivityTransitionAnimation2Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTransitionAnimation2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)
    }
}