package no.advokat.identer.utils


import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys


class SecureStorage(context: Context) {

    private val sharedPreferences = EncryptedSharedPreferences.create(
        "secure_prefs",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveAccessToken(token: String) {
        sharedPreferences.edit().putString("access_token", token).apply()
    }
    fun clearAccessToken() {
        sharedPreferences.edit().remove("access_token").apply()
    }
    fun getAccessToken(): String? {
        return sharedPreferences.getString("access_token", null)
    }
}