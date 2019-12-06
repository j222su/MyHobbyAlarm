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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.INPUT_METHOD_SERVICE;

public class TodoAddFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private String TAG = "TodoAddFragment : ";
    private static ArrayList<ToDoItem> mToDoItemsArrayList = new ArrayList<ToDoItem>();

    private static String DAY_INDEX = "SELECTED_DAY";
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

    public static Fragment newInstance(ArrayList<ToDoItem> list) {
        TodoAddFragment todoAddFragment = new TodoAddFragment();
       mToDoItemsArrayList = list;
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
        mToDoSendFloatingActionButton.setOnClickListener(this);

        Log.d(TAG, "onCreateView");
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.makeToDoFloatingActionButton:
                makeToDoFloatingActionButtonOnClickAction();
                break;

            case R.id.newTodoDateEditText:
                newTodoDateEditTextOnClickAction();
                break;
        }
    }

    private void newTodoDateEditTextOnClickAction() {
        Date date;
        hideKeyboard(mToDoTextBodyEditText);
        if (mUserToDoItem.getToDoDate() != null) {
//                    date = mUserToDoItem.getToDoDate();
            date = mUserReminderDate;
        } else {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, year, month, day);
        datePickerDialog.show(getActivity().getFragmentManager(), "DateFragment");
    }

    private void makeToDoFloatingActionButtonOnClickAction() {
        if (mToDoTextBodyEditText.length() <= 0) {
            mToDoTextBodyEditText.setError(getString(R.string.todo_error));
        } else if (mUserReminderDate != null && mUserReminderDate.before(new Date())) {
//                    app.send(this, "Action", "Date in the Past");
            makeResult(RESULT_CANCELED);
        } else {
//                    app.send(this, "Action", "Make Todo");
            makeResult(RESULT_OK);
            getActivity().finish();
        }
        hideKeyboard(mToDoTextBodyEditText);
        hideKeyboard(mToDoTextBodyDescription);
    }

    public void makeResult(int result) {
        Log.d(TAG, "makeResult - ok : in");
        Intent i = new Intent();
        if (mUserEnteredText.length() > 0) {

            String capitalizedString = Character.toUpperCase(mUserEnteredText.charAt(0)) + mUserEnteredText.substring(1);
            mUserToDoItem.setToDoText(capitalizedString);
            Log.d(TAG, "Description: " + mUserEnteredDescription);
            mUserToDoItem.setmToDoDescription(mUserEnteredDescription);
        } else {
            mUserToDoItem.setToDoText(mUserEnteredText);
            Log.d(TAG, "Description: " + mUserEnteredDescription);
            mUserToDoItem.setmToDoDescription(mUserEnteredDescription);
        }
//        mUserToDoItem.setLastEdited(mLastEdited);
        if (mUserReminderDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(mUserReminderDate);
            calendar.set(Calendar.SECOND, 0);
            mUserReminderDate = calendar.getTime();
        }
        mUserToDoItem.setHasReminder(mUserHasReminder);
        mUserToDoItem.setToDoDate(mUserReminderDate);
        mUserToDoItem.setTodoColor(mUserColor);
        i.putExtra(CalendarFragment.TODOITEM, mUserToDoItem);
        getActivity().setResult(result, i);
    }

    private void hideKeyboard(EditText et) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        setDate(year, monthOfYear, dayOfMonth);
    }

    public void setDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        int hour, minute;
//        int currentYear = calendar.get(Calendar.YEAR);
//        int currentMonth = calendar.get(Calendar.MONTH);
//        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        Calendar reminderCalendar = Calendar.getInstance();
        reminderCalendar.set(year, month, day);

        if (reminderCalendar.before(calendar)) {
            //    Toast.makeText(this, "My time-machine is a bit rusty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mUserReminderDate != null) {
            calendar.setTime(mUserReminderDate);
        }

        if (DateFormat.is24HourFormat(getContext())) {
            hour = calendar.get(Calendar.HOUR_OF_DAY);
        } else {

            hour = calendar.get(Calendar.HOUR);
        }
        minute = calendar.get(Calendar.MINUTE);

        calendar.set(year, month, day, hour, minute);
        mUserReminderDate = calendar.getTime();
        setReminderTextView();
//        setDateAndTimeEditText();
        setDateEditText();
    }

    public void setReminderTextView() {
        if (mUserReminderDate != null) {
            mReminderTextView.setVisibility(View.VISIBLE);
            if (mUserReminderDate.before(new Date())) {
                Log.d("OskarSchindler", "DATE is " + mUserReminderDate);
                mReminderTextView.setText(getString(R.string.date_error_check_again));
                mReminderTextView.setTextColor(Color.RED);
                return;
            }
            Date date = mUserReminderDate;
            String dateString = formatDate("d MMM, yyyy", date);
            String timeString;
            String amPmString = "";

            if (DateFormat.is24HourFormat(getContext())) {
                timeString = formatDate("k:mm", date);
            } else {
                timeString = formatDate("h:mm", date);
                amPmString = formatDate("a", date);
            }
            String finalString = String.format(getResources().getString(R.string.remind_date_and_time), dateString, timeString, amPmString);
            mReminderTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
            mReminderTextView.setText(finalString);
        } else {
            mReminderTextView.setVisibility(View.INVISIBLE);

        }
    }

    public void setDateEditText() {
        String dateFormat = "d MMM, yyyy";
        mDateEditText.setText(formatDate(dateFormat, mUserReminderDate));
    }

    public void setTimeEditText() {
        String dateFormat;
        if (DateFormat.is24HourFormat(getContext())) {
            dateFormat = "k:mm";
        } else {
            dateFormat = "h:mm a";

        }
        mTimeEditText.setText(formatDate(dateFormat, mUserReminderDate));
    }

    public static String formatDate(String formatString, Date dateToFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
        return simpleDateFormat.format(dateToFormat);
    }

    /**
     * MainActivity와 Fragment간의 데이터 전달하기 위한 인터페이스 선언
     */
    public interface OnTodoAddFragmentInteractionListener {
        void onTodoAddFragmentInteraction(ArrayList<ToDoItem> list);
    }
}
