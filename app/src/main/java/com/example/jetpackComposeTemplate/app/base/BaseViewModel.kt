package com.example.jetpackComposeTemplate.app.base

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel() {

    protected suspend fun toast(context: Context, message: String) {
        withContext(Dispatchers.Main) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    protected fun logI( tag: String,message: String) {
        Log.i(tag, "log: $message")
    }

    protected fun logE(message: String, tag: String) {
        Log.e(tag, "log: $message")
    }

}