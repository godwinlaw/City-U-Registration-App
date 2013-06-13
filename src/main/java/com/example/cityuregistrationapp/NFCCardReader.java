package com.example.cityuregistrationapp;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by GodwinLaw on 9/6/13.
 */
public class NFCCardReader extends Activity {

    TextView status;
    Tag mTagfromIntent;
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private byte[] keyA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.nfcreader);

        initialize();

        resolveIntent(getIntent());

        mAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mAdapter == null) {
            status.setText("NFC not enabled.");
            return;
        }
        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }

    private void initialize() {
        status = (TextView) findViewById(R.id.tvStatus);
        setKeyA();
    }

    private void resolveIntent(Intent intent) {
        String action = intent.getAction();
        if (action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            status.setText("Ndef discovered.");
        } else if (action.equals(NfcAdapter.ACTION_TECH_DISCOVERED)) {
            status.setText("Tech discovered.");
        } else if (action.equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
            StringBuilder sb = new StringBuilder();
            status.setText("Tag discovered");
            mTagfromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String hexID = getHexID(mTagfromIntent.getId());
            sb.append("Tag ID: " + hexID + "\n");
            MifareClassic mClassic = MifareClassic.get(mTagfromIntent);
            try {
                mClassic.connect();
                sb.append("Number of blocks: " + mClassic.getBlockCount() + "\n");
                boolean accessGranted = mClassic.authenticateSectorWithKeyA(0, keyA);
                if (accessGranted) {
                    sb.append("Access granted!\n");
                    byte[] data = mClassic.readBlock(1);
                    int[] nums = readData(data);
                    sb.append("Student ID: ");
                    for (int i = 2; i <= 9; i++) {
                        sb.append(nums[i]);
                    }
                }
                mClassic.close();
            } catch (IOException i) {
                i.printStackTrace();
                sb = new StringBuilder();
                sb.append("ERROR");
            }
            status.setText(sb.toString());
        }
    }

    private int[] readData(byte[] data) {
        int[] ints = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            int num = (data[i] & 0xff) & 15;
            ints[i] = num;
        }
        return ints;
    }


    private String getHexID(byte[] id) {
        StringBuilder sb = new StringBuilder();
        for (int i = id.length - 1; i >= 0; i--) {
            int num = id[i] & 0xff;
            if (num < 0x10) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(num));
        }
        return sb.toString();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("onNewIntent", "cityu");
        setIntent(intent);
        resolveIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume", "cityu");
        if (mAdapter != null) {
            mAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause", "cityu");
        if (mAdapter != null) {
            mAdapter.disableForegroundDispatch(this);
        }
    }

    private void setKeyA() {
        keyA = new byte[6];
        keyA[0] = (byte) 0xA6;
        keyA[1] = (byte) 0xA7;
        keyA[2] = (byte) 0xA8;
        keyA[3] = (byte) 0xA9;
        keyA[4] = (byte) 0xAA;
        keyA[5] = (byte) 0xAB;
    }
}
