package com.example.jetpackComposeTemplate.provider.sharedpreference.encrypted


import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class EncryptedPreferencesManagerImpl(context: Context) : EncryptedPreferencesProvider {

    // Generate or get the master key for encryption
    private val masterKeyAlias = MasterKeys.getOrCreate(
        MasterKeys.AES256_GCM_SPEC // AES encryption scheme
    )

    // Create an instance of EncryptedSharedPreferences
    private val encryptedSharedPreferences = EncryptedSharedPreferences.create(
        "encrypted_prefs", // Name of the shared preferences file
        masterKeyAlias,
        context,// Master key for encryption
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // Key encryption
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // Value encryption
    )

    override fun saveString(key: String, value: String) {
        encryptedSharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String, defaultValue: String): String {
        return encryptedSharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    override fun saveBoolean(key: String, value: Boolean) {
        encryptedSharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return encryptedSharedPreferences.getBoolean(key, defaultValue)
    }

    override fun saveInt(key: String, value: Int) {
        encryptedSharedPreferences.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return encryptedSharedPreferences.getInt(key, defaultValue)
    }

    override fun saveLong(key: String, value: Long) {
        encryptedSharedPreferences.edit().putLong(key, value).apply()
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return encryptedSharedPreferences.getLong(key, defaultValue)
    }

    override fun clear() {
        encryptedSharedPreferences.edit().clear().apply()
    }
}
