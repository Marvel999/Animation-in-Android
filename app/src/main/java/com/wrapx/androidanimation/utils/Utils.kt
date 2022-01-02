@file:JvmName("Utils")

package com.wrapx.androidanimation.utils

import android.view.View
import android.view.ViewTreeObserver

inline fun <T: View> T.afterMeasured(crossinline func: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            func()
            viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
    })
}

