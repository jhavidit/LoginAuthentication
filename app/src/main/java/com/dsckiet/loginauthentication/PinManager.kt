package com.dsckiet.loginauthentication

import android.content.Context
import android.content.SharedPreferences

class PinManager(context : Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val PIN= "pin_code"
    }

    fun savePin(token: String) {
        val editor = prefs.edit()
        editor.putString(PIN, token)
        editor.apply()
    }

    fun getPin(): String? {
        return prefs.getString(PIN, null)
    }

    fun deletePin(){
        val editor =prefs.edit()
        editor.putString(PIN,null)
        editor.apply()
    }
}