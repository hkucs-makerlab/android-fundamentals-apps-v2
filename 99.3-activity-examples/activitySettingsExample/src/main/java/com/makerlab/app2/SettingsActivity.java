package com.makerlab.app2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.util.Log;
import android.os.Bundle;

import java.util.prefs.PreferenceChangeListener;

public class SettingsActivity extends AppCompatActivity {
    // key of SwitchPreference defined in preferences xml
    public static final String KEY_PREF_EXAMPLE_SWITCH = "example_switch";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create Fragment for preference UI and replace activity content by Fragment UI
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    // fragment for preferences UI
    static public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceClickListener {
        private PreferenceScreen preferenceScreen;
        private SharedPreferences sharedPref;
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            Log.d("onCreatePreferences", "called");
            // load UI from preferences xml
            setPreferencesFromResource(R.xml.preferences, rootKey);
            //
            preferenceScreen=getPreferenceScreen();
            //Preference firstPref=preferenceScreen.getPreference(0);
            Preference firstPref=findPreference(KEY_PREF_EXAMPLE_SWITCH);
            firstPref.setOnPreferenceClickListener(this);
            //
            sharedPref=firstPref.getSharedPreferences();
            // 1st preference to disable/enable rest of preferences
            boolean isEnabled = sharedPref.getBoolean(firstPref.getKey(),false);
            setPreferencesEnabled(isEnabled);

        }

        @Override
        public boolean onPreferenceTreeClick(Preference preference) {
            Log.d("onPreferenceTreeClick", "called, key is "+ preference.getKey());
//            Preference firstPref=preferenceScreen.getPreference(0);
//            if (firstPref == preference) {
//                SharedPreferences sharedPref=firstPref.getSharedPreferences();
//                boolean b = sharedPref.getBoolean(firstPref.getKey(),false);
//                setPreferencesEnabled(b);
//                return true;
//            }
            return super.onPreferenceTreeClick(preference);
        }

        private void setPreferencesEnabled(boolean flag) {
            int c=preferenceScreen.getPreferenceCount();
            for (int i=1; i< c; i++) {
                Preference p=preferenceScreen.getPreference(i);
                p.setEnabled(flag);

            }
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            Log.d("onPreferenceClick", "called");
            boolean isEnabled = sharedPref.getBoolean(preference.getKey(),false);
            setPreferencesEnabled(isEnabled);
            return true;
        }
    }
}
