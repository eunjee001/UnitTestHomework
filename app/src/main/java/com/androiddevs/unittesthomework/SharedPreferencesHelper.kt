package com.androiddevs.unittesthomework

import android.content.SharedPreferences
import java.util.Calendar

const val KEY_NAME = "key_name"


class SharedPreferencesHelper(
    private val mSharedPreferences: SharedPreferences
) {
    fun savePersonalInfo(sharedPreferenceEntry: SharedPreferenceEntry): Boolean {
        val editor = mSharedPreferences.edit()
        editor.putString(KEY_NAME, sharedPreferenceEntry.name)
        return editor.commit()
    }
    val personalInfo: SharedPreferenceEntry
        get() {
            val name = mSharedPreferences.getString(KEY_NAME, "")

            return SharedPreferenceEntry(name!!)

        }

}