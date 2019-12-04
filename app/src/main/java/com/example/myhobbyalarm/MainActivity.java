package com.example.myhobbyalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements CalendarFragment.OnCalendarFragmentInteractionListener,
        DayListFragment.OnDayListFragmentInteractionListener,
        TodoAddFragment.OnTodoAddFragmentInteractionListener {



    FrameLayout frameLayout;
    private String day;
    private String event;

    private static String DAY_INDEX = "SELECTED_DAY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frameLayout);


        day = "2019,10,20";

        replaceFragment(new CalendarFragment());

//        FragmentTransaction ft =
//                getSupportFragmentManager().beginTransaction();
//        ft.add(R.id.frameLayout,CalendarFragment.newInstance(day)).commit();
//        Fragment fragmentActivity = null;
//        fragmentActivity = new CalendarFragment();
//
//        Bundle bundle = new Bundle();
//        bundle.putString(DAY_INDEX, day);
//        fragmentActivity.setArguments(bundle);
//        ft.replace(R.id.frameLayout, fragmentActivity);
//        ft.commit();

    }


    /** Fragment에서 Fragment를 화면 전환하기 위한 함수 선언*/
    public void replaceFragment(Fragment fragment) {
        Bundle bundle = new Bundle(1);
        bundle.putString(DAY_INDEX, day);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    @Override
    public void onCalendarFragmentInteraction(String day) {
        this.day = day;
    }


    @Override
    public void onDayListFragmentInteraction(String day) {
        this.day = day;
    }

    @Override
    public void onTodoAddFragmentInteraction(String day, String event) {
        this.day = day;
        this.event = event;
    }
}
