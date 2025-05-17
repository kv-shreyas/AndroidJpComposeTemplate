package com.example.jetpackComposeTemplate.provider.sharedpreference.encrypted

interface EncryptedPreferencesProvider {

    fun saveString(key: String, value: String)
    fun getString(key: String, defaultValue: String): String

    fun saveBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    fun saveInt(key: String, value: Int)
    fun getInt(key: String, defaultValue: Int): Int

    fun saveLong(key: String, value: Long)
    fun getLong(key: String, defaultValue: Long): Long

    fun clear()
}