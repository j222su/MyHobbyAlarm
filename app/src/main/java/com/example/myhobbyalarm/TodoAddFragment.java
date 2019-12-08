package com.example.myhobbyalarm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.INPUT_METHOD_SERVICE;

public class TodoAddFragment extends Fragment implements View.OnClickListener {
    private String TAG = "TodoAddFragment";
    private static ArrayList<ToDoItem> mToDoItemsArrayList = new ArrayList<ToDoItem>();
    private static String DAY_INDEX;

    private String mUserEnteredText;
    private String mUserEnteredDescription;

    private Date mUserReminderDate;
    private ToDoItem mUserToDoItem;
    private int mUserColor;

    private LinearLayout mUserDateSpinnerContainingLinearLayout;
    private TextView mReminderTextView;
    private EditText mDateEditText;
    private EditText mTimeEditText;
    private EditText mToDoTextBodyEditText;
    private EditText mToDoTextBodyDescription;
    private LinearLayout mContainerLayout;
    private SwitchCompat mToDoDateSwitch;
    private FloatingActionButton mToDoSendFloatingActionButton;
    private boolean mUserHasReminder;

    public TodoAddFragment() {
    }

    public static Fragment newInstance(ArrayList<ToDoItem> list, String selected) {
        TodoAddFragment todoAddFragment = new TodoAddFragment();
       mToDoItemsArrayList = list;
        DAY_INDEX = selected;
        return todoAddFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_list_fragment, container, false);

        mUserToDoItem = (ToDoItem) getActivity().getIntent().getSerializableExtra(CalendarFragment.TODOITEM);
//        mUserEnteredDescription = mUserToDoItem.getmToDoDescription();
//        mUserHasReminder = mUserToDoItem.hasReminder();
//        mUserReminderDate = mUserToDoItem.getToDoDate();
//        mUserColor = mUserToDoItem.getTodoColor();


        mContainerLayout = (LinearLayout) view.findViewById(R.id.todoReminderAndDateContainerLayout);
        mUserDateSpinnerContainingLinearLayout = (LinearLayout) view.findViewById(R.id.toDoEnterDateLinearLayout);
        mToDoTextBodyEditText = (EditText) view.findViewById(R.id.userToDoEditText);
        mToDoTextBodyDescription = (EditText) view.findViewById(R.id.userToDoDescription);
        mToDoDateSwitch = (SwitchCompat) view.findViewById(R.id.toDoHasDateSwitchCompat);
        mToDoSendFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.makeToDoFloatingActionButton);
        mReminderTextView = (TextView) view.findViewById(R.id.newToDoDateTimeReminderTextView);

        mDateEditText = (EditText) view.findViewById(R.id.newTodoDateEditText);
        mTimeEditText = (EditText) view.findViewById(R.id.newTodoTimeEditText);

        mDateEditText.setOnClickListener(this);
        mTimeEditText.setOnClickListener(this);
        mToDoSendFloatingActionButton.setOnClickListener(this);

        Log.d(TAG, "onCreateView");
        return view;
    }

    @Override
    public void onClick(View v) {

    }


    /**
     * MainActivity와 Fragment간의 데이터 전달하기 위한 인터페이스 선언
     */
    public interface OnTodoAddFragmentInteractionListener {
        void onTodoAddFragmentInteraction(ArrayList<ToDoItem> list);
    }
}
