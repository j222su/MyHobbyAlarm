package com.example.myhobbyalarm;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CalendarFragment extends Fragment implements View.OnClickListener {
    private String TAG = "CalendarFragment : ";
    private String day;

    private static String DAY_INDEX = "SELECTED_DAY";


    /** Intent Code */
    private static final int REQUEST_ID_TODO_ITEM = 100;

    public static final String DATE_TIME_FORMAT_12_HOUR = "MMM d, yyyy  h:mm a";
    public static final String DATE_TIME_FORMAT_24_HOUR = "MMM d, yyyy  k:mm";

    View view;
    Context context;

    private static ArrayList<ToDoItem> mToDoItemsArrayList;


    /** File Name */
    public static final String FILENAME = "todoitems.json";

    /** File Code */
    private static String SHARED_PREF_DATA_SET_CHANGED = "com.example.myminimaltest.datasetchanged";
    public static final String CHANGE_OCCURED = "com.example.myminimaltest.changeoccured";
    public static final String TODOITEM = "com.example.myminimaltest.MainActivity";
    public static final String THEME_PREFERENCES = "com.example.myminimaltest.themepref";

    public static final String THEME_SAVED = "com.example.myminimaltest.savedtheme";
    public static final String LIGHTTHEME = "com.example.myminimaltest.lighttheme";
    public static final String DARKTHEME = "com.example.myminimaltest.darktheme";

    /**
     * Calendar View *
     */
    public CalendarFragment() {
    }

    public static Fragment newInstance(ArrayList<ToDoItem> list) {
        CalendarFragment calendarFragment = new CalendarFragment();
        mToDoItemsArrayList = list;
        Bundle bundle = new Bundle();

//        bundle.putString(DAY_INDEX, day);
        calendarFragment.setArguments(bundle);
        return calendarFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_fragment, container, false);
        Log.d(TAG, "onCreateView");

        day = getArguments().getString(DAY_INDEX);
        TextView tvDay = view.findViewById(R.id.tvDay);
        tvDay.setText(day);

        Button btnDay = view.findViewById(R.id.btnDay);
        btnDay.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        /**
         * Fragment에서 Fragment를 화면 전환하기 위한
         * MainActivity에 선언된 함수 사용
         * 새로 불러올 Fragment의 Instance를 Main으로 전달
         * */
        ((MainActivity) getActivity()).replaceFragment(DayListFragment.newInstance(day));

    }

    /** MainActivity와 Fragment간의 데이터 전달하기 위한 인터페이스 선언*/
    public interface OnCalendarFragmentInteractionListener {
        void onCalendarFragmentInteraction(ArrayList<ToDoItem> list);
    }
}
