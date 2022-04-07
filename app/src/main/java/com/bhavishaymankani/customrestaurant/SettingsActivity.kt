package com.bhavishaymankani.customrestaurant

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.*
import com.bhavishaymankani.customrestaurant.base.BaseActivity
import com.bhavishaymankani.customrestaurant.utils.Constants.DARK_MODE_ON

class SettingsActivity : BaseActivity(), SharedPreferences.OnSharedPreferenceChangeListener/*,Preference.OnPreferenceChangeListener*/ {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)
        Log.d("pref", "oncreate")
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            //val themePreference: SwitchPreferenceCompat? =  findPreference(getString(R.string.theme_key)) as SwitchPreferenceCompat?
            /*val themePreference =  PreferenceManager.getDefaultSharedPreferences(requireContext()).edit()

            val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this.requireContext())
            val isDarkModeOn = sharedPreference.getBoolean(DARK_MODE_ON, true)

            themePreference.putBoolean(getString(R.string.theme_key), false)*/

            Log.d("pref", getString(R.string.theme_key))
            /*themePreference?.setOnPreferenceChangeListener { preference, newValue ->
                sharedPreference.edit().putBoolean(DARK_MODE_ON, newValue as Boolean).apply()
                if (preference.isEnabled) {
                    SettingsActivity().setDarkMode(true)
                } else {
                    SettingsActivity().setDarkMode(false)
                }
                true
            }  ?: Log.d("pref", "$isDarkModeOn")*/
        }

       /* override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            val themePreference: SwitchPreference? = findPreference(getString(R.string.theme_key))
            val sharedPreference = PreferenceManager.getDefaultSharedPreferences(requireContext().applicationContext)
            val isDarkModeOn = sharedPreference.getBoolean(DARK_MODE_ON, true)
            Log.d("pref", getString(R.string.theme_key))
            themePreference?.setOnPreferenceChangeListener { preference, newValue ->
                sharedPreference.edit().putBoolean(DARK_MODE_ON, newValue as Boolean).apply()
                if ((preference as SwitchPreference).isChecked) {
                    Log.d("pref", "true")
                    SettingsActivity().setDarkMode(true)
                } else {
                    Log.d("pref", "false")
                    SettingsActivity().setDarkMode(false)
                }
                true
            } ?: Log.d("pref", "null")
            return super.onCreateView(inflater, container, savedInstanceState)
        }*/


    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        key?.let {
            when(it) {
                getString(R.string.theme_key) -> {
                    sharedPreferences?.let { pref ->
                        var darkMode = pref.getBoolean(getString(R.string.theme_key), true)
                    if (darkMode) {
                        SettingsActivity().setDarkMode(false)
                    } else
                        SettingsActivity().setDarkMode(true)
                    }
                }
                else -> {}
            }
        }
    }

  /*  override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        Log.d("preference", "$newValue")
        return true
    }*/
}