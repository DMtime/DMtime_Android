package com.jaemin.features.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import timber.log.Timber

class SharedPreferencesManager (private val context: Context) {
    private var pref: SharedPreferences? = null

    fun getToken(): String {
        if (pref ==null) pref = context.getSharedPreferences("DMTIME", MODE_PRIVATE)
        return pref!!.getString("accessToken","empty")!!
    }

    fun getAutoLoginState(): Boolean {
        if (pref ==null) pref = context.getSharedPreferences("DMTIME", MODE_PRIVATE)
        return pref!!.getBoolean("autoLogin",false)
    }


    fun saveToken(info: String) {
        if (pref == null) pref = context.getSharedPreferences("DMTIME", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref!!.edit()
        editor.putString("accessToken", info)
        editor.apply()
    }

    fun saveAutoLoginState(info: Boolean) {
        if (pref == null) pref = context.getSharedPreferences("DMTIME", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref!!.edit()
        editor.putBoolean("autoLogin", info)
        editor.apply()
    }
    fun clearAutoLoginState() {
        if (pref == null) pref = context.getSharedPreferences("DMTIME", MODE_PRIVATE)
        pref!!.edit().remove("autoLogin").apply()

    }
    fun clearToken() {
        if (pref == null) pref = context.getSharedPreferences("DMTIME", MODE_PRIVATE)
        pref!!.edit().remove("accessToken").apply()
    }
}