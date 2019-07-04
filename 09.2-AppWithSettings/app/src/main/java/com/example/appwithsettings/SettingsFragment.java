package com.example.appwithsettings;


import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {
        //to associate with this Fragment the preferences.xml settings resource
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
