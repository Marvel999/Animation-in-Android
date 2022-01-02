package com.wrapx.androidanimation.ui

import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.wrapx.androidanimation.R
import com.wrapx.androidanimation.databinding.ActivityAnimatorBinding
import com.wrapx.androidanimation.databinding.ActivityVectorAnimationBinding

class VectorAnimation : AppCompatActivity() {

    private var binding: ActivityVectorAnimationBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityVectorAnimationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)
        val animatedVector = ContextCompat.getDrawable(this, R.drawable.svg_animation)
        binding?.vectorImg?.setImageDrawable(animatedVector)
        (animatedVector as AnimatedVectorDrawable).start()

        var isCheck = false
        binding?.vectorImg1?.setOnClickListener {
           if (isCheck)
               close()
            else
                check()
            isCheck = !isCheck
        }

    }

    fun check(){
        binding?.vectorImg1?.setImageResource(R.drawable.check_to_close_anim)
        val checkToClose= binding?.vectorImg1?.drawable as AnimatedVectorDrawable
        checkToClose.start()
    }

    fun close(){
        binding?.vectorImg1?.setImageResource(R.drawable.close_to_check)
        val checkToClose= binding?.vectorImg1?.drawable as AnimatedVectorDrawable
        checkToClose.start()
    }
}