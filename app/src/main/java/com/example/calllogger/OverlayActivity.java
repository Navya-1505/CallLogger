package com.example.calllogger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class OverlayActivity extends Activity {

    private boolean mCloseOverlay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the layout for the overlay interface
        setContentView(R.layout.overlay_layout);

        // Set up the UI elements
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView mIncomingNumberTextView = (TextView) findViewById(R.id.incoming_number_textview);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button mMarkAsStudentButton = (Button) findViewById(R.id.mark_as_student_button);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button mMarkAsNotStudentButton = (Button) findViewById(R.id.mark_as_not_student_button);

        // Get the incoming number from the intent
        String incomingNumber = getIntent().getStringExtra("incoming_number");
        mIncomingNumberTextView.setText(incomingNumber);

        // Set up the click listeners for the buttons
        mMarkAsStudentButton.setOnClickListener(v -> markCallAsStudent());

        mMarkAsNotStudentButton.setOnClickListener(v -> markCallAsNotStudent());

        // Set up the window parameters
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    private void markCallAsStudent() {
        // Add code here to mark the call as a student and save it to the database
        // You can use the incoming number and a counter to create a unique student name

        // Close the overlay interface
        mCloseOverlay = true;
        finish();
    }

    private void markCallAsNotStudent() {
        // Add code here to mark the call as not a student and save it to the database

        // Close the overlay interface
        mCloseOverlay = true;
        finish();
    }

    @Override
    public void onBackPressed() {
        // Disable the back button
        // Add code here to prevent the user from closing the overlay interface with the back button
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mCloseOverlay) {
            Intent intent = new Intent(this, CallReceiver.class);
            intent.putExtra("close_overlay", true);
            sendBroadcast(intent);
        }
    }
}
