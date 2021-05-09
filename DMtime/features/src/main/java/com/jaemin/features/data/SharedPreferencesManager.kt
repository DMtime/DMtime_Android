package com.jaemin.features.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreferencesManager (private val context: Context) {
    private var pref: SharedPreferences? = null

    fun getInfo(content: String): String {
        if (pref ==null) pref = context.getSharedPreferences("DMTIME", MODE_PRIVATE)
        return if (content == "accessToken") {
            "Bearer " + pref?.getString(content,"")
        } else
            pref?.getString(content, "").toString()

    }

    fun saveInfo(info: String, content: String) {
        if (pref == null) pref = context.getSharedPreferences("DMTIME", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref!!.edit()
        editor.putString(content, info)
        editor.apply()
    }

    fun clearInfo(info: String, content: String) {
        if (pref == null) pref = context.getSharedPreferences("DMTIME", MODE_PRIVATE)
        pref!!.edit().remove(content).apply()

    }
}