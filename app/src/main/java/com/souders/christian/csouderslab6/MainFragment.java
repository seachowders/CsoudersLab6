package com.souders.christian.csouderslab6;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        setHasOptionsMenu(true);
        //preferences.getString("Name","unknown");
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_main, container, false);
       // Map<String, ?> map = preferences.getAll();
        boolean student = preferences.getBoolean("Student", false);
        String yearsToCommission = preferences.getString("yearsToCommission", "0");
        String homeWorld = preferences.getString("homeWorld", "5");
        String name = preferences.getString("Name", "John Doe");

        TextView tx = (TextView)v.findViewById(R.id.helloName);
        tx.append(" " + name);
        tx = (TextView)v.findViewById(R.id.message);
        if(student)
        {
            tx.setText("We wish you success during your " + yearsToCommission +" years at the academy.");
            ImageView image = (ImageView)v.findViewById(R.id.imageView);
            TypedArray typedImgs =getResources().obtainTypedArray(R.array.homeWorldImages);
            int[] images = new int[typedImgs.length()];
            for (int i=0 ; i<typedImgs.length() ; i++)
                images[i] = typedImgs.getResourceId(i, 0) ;
            image.setImageResource(images[Integer.parseInt(homeWorld)]);

        }
        else
        {
            tx.setText("Welcome Back.");
            ImageView image = (ImageView)v.findViewById(R.id.imageView);
            image.setImageResource(R.drawable.academy);
        }

        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
    }

}
