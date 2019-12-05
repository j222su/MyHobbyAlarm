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

import java.util.ArrayList;

public class DayListFragment extends Fragment implements View.OnClickListener {
    private String TAG = "DayListFragment : ";
    private String day;
    private static String DAY_INDEX = "SELECTED_DAY";

    public DayListFragment() {
    }

    public static Fragment newInstance(String day) {
        DayListFragment dayListFragment = new DayListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DAY_INDEX,day);
        dayListFragment.setArguments(bundle);
        return dayListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_list_fragment, container, false);

        day = getArguments().getString(DAY_INDEX);
        TextView tvDay = view.findViewById(R.id.tvDay);
        tvDay.setText(day);

        Button btnAddEvent = view.findViewById(R.id.btnAddEvent);
        btnAddEvent.setOnClickListener(this);
        Log.d(TAG, "onCreateView");
        return view;
    }

    @Override
    public void onClick(View v) {

        /**
         * Fragment에서 Fragment를 화면 전환하기 위한
         * MainActivity에 선언된 함수 사용
         * 새로 불러올 Fragment의 Instance를 Main으로 전달
         * */
        ((MainActivity) getActivity()).replaceFragment(TodoAddFragment.newInstance(day));

    }

    /** MainActivity와 Fragment간의 데이터 전달하기 위한 인터페이스 선언*/
    public interface OnDayListFragmentInteractionListener {
        void onDayListFragmentInteraction(ArrayList<ToDoItem> list);
    }
}
