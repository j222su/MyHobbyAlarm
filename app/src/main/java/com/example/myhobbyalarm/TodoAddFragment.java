package com.example.myhobbyalarm;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

class TodoAddFragment extends Fragment {
    private String TAG = "TodoAddFragment : ";

    public TodoAddFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_add_fragment, container, false);
        Log.d(TAG, "onCreateView");
        return view;
    }


    public interface OnTodoAddFragmentInteractionListener {
       void onTodoAddFragmentInteraction(String name);
    }
}
