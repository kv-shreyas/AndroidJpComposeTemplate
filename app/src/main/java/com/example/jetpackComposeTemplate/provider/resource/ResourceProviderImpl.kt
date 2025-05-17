package com.example.jetpackComposeTemplate.provider.resource

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }

    override fun getDrawable(resId: Int): Drawable? {
        return ContextCompat.getDrawable(context, resId)
    }

    override fun getColor(resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

    override fun getFont(resId: Int): Typeface? {
        return ResourcesCompat.getFont(context, resId)
    }
}
