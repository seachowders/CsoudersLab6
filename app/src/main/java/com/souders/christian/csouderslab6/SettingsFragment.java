package com.souders.christian.csouderslab6;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
//import android.support.v7.*;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences pref = getPreferenceManager().getSharedPreferences();
        onSharedPreferenceChanged(pref, "Name");
        onSharedPreferenceChanged(pref, "yearsToCommission");
        onSharedPreferenceChanged(pref, "homeWorld");

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equalsIgnoreCase("Name") || key.equalsIgnoreCase("YearsToCommission"))
        {
            getPreferenceManager().findPreference(key).setSummary(sharedPreferences.getString(key, "0"));
        }
        else
            if(key.equalsIgnoreCase("homeWorld"))
            {
                String[] worlds = getResources().getStringArray(R.array.homeWorlds);
                String index = sharedPreferences.getString(key, "5");
                getPreferenceManager().findPreference(key).setSummary(worlds[Integer.parseInt(index)]);
            }


    }

    @Override
    public void onResume()
    {
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        super.onResume();
    }

    @Override
    public void onPause()
    {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

}
