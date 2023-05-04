package com.example.calllogger;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class CallReceiver extends BroadcastReceiver {

    private static final int OVERLAY_PERMISSION_REQUEST_CODE = 1;

    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;

        if (intent.getAction().equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                showOverlay(incomingNumber);
            } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                hideOverlay();
            }
        }
    }

    private void showOverlay(String incomingNumber) {
        if (!Settings.canDrawOverlays(mContext)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + mContext.getPackageName()));
            ((Activity) mContext).startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST_CODE);
            return;
        }

        // Show the overlay interface
        Intent intent = new Intent(mContext, OverlayActivity.class);
        intent.putExtra("incoming_number", incomingNumber);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    private void hideOverlay() {
        // Close the overlay interface
        Intent intent = new Intent(mContext, OverlayActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("close_overlay", true);
        mContext.startActivity(intent);
    }
}

