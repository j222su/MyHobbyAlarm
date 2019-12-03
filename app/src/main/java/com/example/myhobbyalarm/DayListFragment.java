package com.example.myhobbyalarm;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

class DayListFragment extends Fragment {
    private String TAG = "DayListFragment : ";

    public DayListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_list_fragment, container, false);
        Log.d(TAG, "onCreateView");
        return view;
    }


    public interface OnDayListFragmentInteractionListener {
       void onDayListFragmentInteraction(String name);
    }
}
