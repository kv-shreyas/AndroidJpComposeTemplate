package com.example.jetpackComposeTemplate.provider.resource

import android.graphics.Typeface
import android.graphics.drawable.Drawable

interface ResourceProvider {
    fun getString(resId: Int): String
    fun getString(resId: Int, vararg formatArgs: Any): String
    fun getDrawable(resId: Int): Drawable?
    fun getColor(resId: Int): Int
    fun getFont(resId: Int): Typeface?
}
