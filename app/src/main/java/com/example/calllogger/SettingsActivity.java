package com.example.calllogger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences.Editor mEditor;

    private boolean mIsStudentEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) SwitchCompat mSwitchStudent = findViewById(R.id.switch_student);
        SharedPreferences mSharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        mIsStudentEnabled = mSharedPreferences.getBoolean(getString(R.string.preference_student_key), false);
        mSwitchStudent.setChecked(mIsStudentEnabled);
        mSwitchStudent.setChecked(mIsStudentEnabled);
        if (mSwitchStudent != null) {
            mSwitchStudent.setChecked(mIsStudentEnabled);
        }

        mSwitchStudent.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mIsStudentEnabled = isChecked;
            mEditor.putBoolean(getString(R.string.preference_student_key), mIsStudentEnabled);
            mEditor.apply();
        });
    }
}
