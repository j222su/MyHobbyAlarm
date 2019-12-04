package com.example.myhobbyalarm;

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

public class CalendarFragment extends Fragment implements View.OnClickListener {
    private String TAG = "CalendarFragment : ";
    private String day;

    private static String DAY_INDEX = "SELECTED_DAY";

    public CalendarFragment() {
    }

    public static Fragment newInstance(String day) {
        CalendarFragment calendarFragment = new CalendarFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DAY_INDEX, day);
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
        void onCalendarFragmentInteraction(String day);
    }
}
