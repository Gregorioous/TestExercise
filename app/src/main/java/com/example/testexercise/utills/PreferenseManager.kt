package com.example.testexercise.utills

import android.content.SharedPreferences
import com.google.gson.Gson

class PreferenseManager(val pref: SharedPreferences, val gson: Gson) {
    val editor = pref.edit()

    var token: String?
        get() {
            return pref.getString(TOKEN, null)
        }
        set(token) {
            editor.putString(TOKEN, token)
            editor.commit()
        }

    companion object {
        private const val TOKEN = "TOKEN"
    }
}