package com.example.myhobbyalarm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Map;

public class DayListFragment extends Fragment implements View.OnClickListener {
    private String TAG = "DayListFragment : ";

    private static ArrayList<ToDoItem> mToDoItemsArrayList = new ArrayList<ToDoItem>();
    private static String DAY_INDEX = "";

    public DayListFragment() {
    }

    public static Fragment newInstance(ArrayList<ToDoItem> list, String selected) {
        DayListFragment dayListFragment = new DayListFragment();
        mToDoItemsArrayList = list;
        DAY_INDEX = selected;
        return dayListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_list_fragment, container, false);

        TextView tvDay = view.findViewById(R.id.tvDay);
        RecyclerView list = view.findViewById(R.id.commentlist);
        FloatingActionButton btnAddEvent = view.findViewById(R.id.btnAddEvent);

        tvDay.setText(DAY_INDEX);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(new LinearLayoutManager(getContext()));

        DayListAdapter dayListAdapter = new DayListAdapter(mToDoItemsArrayList);
        list.setAdapter(dayListAdapter);

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
        ((MainActivity) getActivity()).replaceFragment(TodoAddFragment.newInstance(mToDoItemsArrayList, DAY_INDEX));

    }

    /** MainActivity와 Fragment간의 데이터 전달하기 위한 인터페이스 선언*/
    public interface OnDayListFragmentInteractionListener {
        void onDayListFragmentInteraction(ArrayList<ToDoItem> list);
    }
}
