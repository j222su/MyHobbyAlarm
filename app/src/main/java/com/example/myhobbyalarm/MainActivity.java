package com.example.myhobbyalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements CalendarFragment.OnCalendarFragmentInteractionListener {
    FrameLayout frameLayout;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frameLayout);


        name = "yan";

        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();
        Fragment fragmentActivity = null;
        fragmentActivity = new CalendarFragment();
        ft.replace(R.id.frameLayout, fragmentActivity);
        ft.commit();

    }

    @Override
    public void onCalendarFragmentInteraction(String name) {

    }
}
