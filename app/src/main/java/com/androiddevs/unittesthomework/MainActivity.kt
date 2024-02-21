package com.androiddevs.unittesthomework

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.androiddevs.unittesthomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mSharedPreferenceEntry: SharedPreferenceEntry

    var isSuccess : Boolean = false
    var text1 : String ?=null
    private val mSharedPreferencesHelper by lazy {
        SharedPreferencesHelper(PreferenceManager.getDefaultSharedPreferences(this))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.text.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                text1 = binding.text.text.toString()

            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        var sharedPreferenceEntry : SharedPreferenceEntry
        binding.button.setOnClickListener {
            binding.textV.text = text1.toString()
            sharedPreferenceEntry = SharedPreferenceEntry(text1!!.toString())
            isSuccess = mSharedPreferencesHelper.savePersonalInfo(sharedPreferenceEntry)
            if (isSuccess) {
                Toast.makeText(this, "Personal information saved", Toast.LENGTH_LONG).show()
                Log.i(TAG, "Personal information saved")
            } else {
                Log.e(TAG, "Failed to write personal information to SharedPreferences")
            }
        }


    }

}