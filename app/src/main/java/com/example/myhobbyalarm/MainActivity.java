package com.example.myhobbyalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements CalendarFragment.OnCalendarFragmentInteractionListener,
        DayListFragment.OnDayListFragmentInteractionListener,
        TodoAddFragment.OnTodoAddFragmentInteractionListener {

    private static final String TAG = "MainActivity";
    private static ArrayList<ToDoItem> mToDoItemsArrayList = new ArrayList<ToDoItem>();

    private FrameLayout frameLayout;
    private ListView list;

    private SQLiteDatabase db;
    private DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Task Reminder");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));

        mDbHelper = new DbHelper(this);
        db = mDbHelper.getWritableDatabase();

        frameLayout = findViewById(R.id.frameLayout);
        list = (ListView) findViewById(R.id.commentlist);

        replaceFragment(new CalendarFragment());

    }

    public ArrayList<ToDoItem> mToDoItemsArrayListEventSetting() {
        String[] from = {mDbHelper.TITLE, mDbHelper.DETAIL, mDbHelper.TYPE,
                mDbHelper.TIME, mDbHelper.DATE};
        final String[] column = {mDbHelper.C_ID, mDbHelper.TITLE, mDbHelper.DETAIL
                , mDbHelper.TYPE, mDbHelper.TIME, mDbHelper.DATE, mDbHelper.ALARM_TIMES
                , mDbHelper.CHANNEL_NAME};
        int[] to = {R.id.tvTitle, R.id.tvDetail, R.id.tvType, R.id.tvTime, R.id.tvDate};

        final Cursor cursor = db.query(mDbHelper.TABLE_NAME, column, null, null, null, null, null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            do {
                String C_ID = cursor.getString(cursor.getColumnIndex(column[0]));
                String TITLE = cursor.getString(cursor.getColumnIndex(column[1]));
                String DETAIL = cursor.getString(cursor.getColumnIndex(column[2]));
                String TYPE = cursor.getString(cursor.getColumnIndex(column[3]));
                String DATE = cursor.getString(cursor.getColumnIndex(column[4]));
                String TIME = cursor.getString(cursor.getColumnIndex(column[5]));
                String ALARM_TIMES = cursor.getString(cursor.getColumnIndex(column[6]));
                String CHANNEL_NAME = cursor.getString(cursor.getColumnIndex(column[7]));

                ToDoItem toDoItem = new ToDoItem(C_ID, TITLE, TYPE, DETAIL, TIME,
                        DATE, ALARM_TIMES, CHANNEL_NAME);
                mToDoItemsArrayList.add(toDoItem);

                Log.d(TAG, "mToDoItemsArrayListEventSetting : " + toDoItem.toString());

            } while (cursor.moveToNext());
        }
        cursor.close();

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
