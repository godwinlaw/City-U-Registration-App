package com.example.cityuregistrationapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by GodwinLaw on 6/6/13.
 */
public class CheckInCheckOut extends Activity implements View.OnClickListener{

    TextView tvEventName;
    Button bCheckIn, bCheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.checkincheckout);
        findViews();
    }

    protected void findViews() {
        tvEventName = (TextView) findViewById(R.id.tvEventName);
        bCheckIn = (Button) findViewById(R.id.bCheckIn);
        bCheckOut = (Button) findViewById(R.id.bCheckOut);
        bCheckIn.setOnClickListener(this);
        bCheckOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.bCheckIn):
                //break;
            case (R.id.bCheckOut):
                Intent i = new Intent("com.example.cityuregistrationapp.NFCCardReader");
                startActivity(i);
                break;
        }
    }
}
