package com.souders.christian.csouderslab6;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String SETTINGS = "SETTINGS";
    private static final String FIRST_USE = "FIRST_USE";

    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);


        MainFragment myFrag = new MainFragment();
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.add(R.id.frameLayout, myFrag);
        trans.commit();
        //getFragmentManager().beginTransaction().add(R.id.frameLayout, myFrag).commit();

        boolean firstUse = preferences.getBoolean(FIRST_USE, true);
        if(firstUse) {
           // Toast.makeText(getApplicationContext(), "Hello first time user!!", Toast.LENGTH_SHORT).show();
            SettingsFragment frag = new SettingsFragment();
           // SettingsFragment sFrag = (SettingsFragment)getSupportFragmentManager().findFragmentById(R.id.)
            trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.frameLayout, frag);
            trans.addToBackStack("SETTINGS_FRAGMENT");
            trans.commit();


            preferences.edit().putBoolean(FIRST_USE, false).commit();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SettingsFragment frag = new SettingsFragment();
            // SettingsFragment sFrag = (SettingsFragment)getSupportFragmentManager().findFragmentById(R.id.)
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.frameLayout, frag);
            trans.addToBackStack("SETTINGS_FRAGMENT");
            trans.commit();
            return true;
        }
        else
        if(id == R.id.action_about)
        {
            Toast.makeText(getApplicationContext(), "Lab 6, Winter 2016, Christian Souders", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }


    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else
            super.onBackPressed();
    }


}
