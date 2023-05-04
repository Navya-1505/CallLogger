package com.example.calllogger;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

public class CallHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_history);

        // Set the range of call log to retrieve (in this case, for the past day)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        String selection = Calendar.DATE + ">?";
        String[] selectionArgs = new String[]{String.valueOf(calendar.getTimeInMillis())};

        // Query the call log
        Cursor cursor = getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI,
                null,
                selection,
                selectionArgs,
                Calendar.DATE + " DESC");

        // Iterate over the cursor and add each call log item to the list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int type = cursor.getInt(cursor.getColumnIndex(android.provider.CallLog.Calls.TYPE));

                switch (type) {
                    case android.provider.CallLog.Calls.INCOMING_TYPE:
                    case CallLog.Calls.MISSED_TYPE:
                    case android.provider.CallLog.Calls.OUTGOING_TYPE:
                        break;
                }

            } while (cursor.moveToNext());

            cursor.close();
        }

        // Set up RecyclerView and adapter
        RecyclerView mRecyclerView = findViewById(R.id.call_log_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CallLogAdapter mAdapter = new CallLogAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }
}
