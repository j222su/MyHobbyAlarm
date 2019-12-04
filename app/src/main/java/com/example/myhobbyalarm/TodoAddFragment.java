package com.example.myhobbyalarm;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.INPUT_METHOD_SERVICE;

public class TodoAddFragment extends Fragment implements View.OnClickListener {
    private String TAG = "TodoAddFragment : ";


    private String mUserReminderDate;
    private static String DAY_INDEX = "SELECTED_DAY";


    private LinearLayout mUserDateSpinnerContainingLinearLayout;
    private TextView mReminderTextView;
    private EditText mDateEditText;
    private EditText mTimeEditText;
    private EditText mToDoTextBodyEditText;
    private EditText mToDoTextBodyDescription;
    private LinearLayout mContainerLayout;
    private SwitchCompat mToDoDateSwitch;
    private FloatingActionButton mToDoSendFloatingActionButton;

    public TodoAddFragment() {
    }

    public static Fragment newInstance(String day) {
        TodoAddFragment todoAddFragment = new TodoAddFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DAY_INDEX, day);
        todoAddFragment.setArguments(bundle);
        return todoAddFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_list_fragment, container, false);

        mUserReminderDate = getArguments().getString(DAY_INDEX);

        mUserToDoItem = (ToDoItem) getActivity().getIntent().getSerializableExtra(MainFragment.TODOITEM);

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


        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(AddToDoFragment.this, year, month, day);
        if (theme.equals(MainFragment.DARKTHEME)) {
            datePickerDialog.setThemeDark(true);
        }
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

    private void hideKeyboard(EditText et) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

    /**
     * MainActivity와 Fragment간의 데이터 전달하기 위한 인터페이스 선언
     */
    public interface OnTodoAddFragmentInteractionListener {
        void onTodoAddFragmentInteraction(String day, String event);
    }
}
