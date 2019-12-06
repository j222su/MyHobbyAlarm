package com.example.myhobbyalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements CalendarFragment.OnCalendarFragmentInteractionListener,
        DayListFragment.OnDayListFragmentInteractionListener,
        TodoAddFragment.OnTodoAddFragmentInteractionListener {

    private static ArrayList<ToDoItem> mToDoItemsArrayList = new ArrayList<ToDoItem>();

    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frameLayout);

        replaceFragment(new CalendarFragment());

    }

    public ArrayList<ToDoItem> mToDoItemsArrayListEventSetting() {
        for (int i = 1; i < 20; i++) {
            mToDoItemsArrayList.add(new ToDoItem(
                    "1", "des 1",true,
                    new Date(2019, 12, i, 12, 12, 0)));
            Log.d("mToDoItems ",""+i);
        }
        return mToDoItemsArrayList;
    }


    /**
     * Fragment에서 Fragment를 화면 전환하기 위한 함수 선언
     */
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    @Override
    public void onCalendarFragmentInteraction(ArrayList<ToDoItem> list) {
        this.mToDoItemsArrayList = list;
    }

    @Override
    public void onDayListFragmentInteraction(ArrayList<ToDoItem> list) {
        this.mToDoItemsArrayList = list;
    }

    @Override
    public void onTodoAddFragmentInteraction(ArrayList<ToDoItem> list) {
        this.mToDoItemsArrayList = list;
    }
}
