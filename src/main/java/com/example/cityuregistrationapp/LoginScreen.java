package com.example.cityuregistrationapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class LoginScreen extends Activity implements View.OnClickListener {

    EditText etEmail, etEventID, etDateAndTime;
    Button bSave;
    String email, eventID, dateAndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        findViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_screen, menu);
        return true;
    }

    public void findViews() {
        etEmail = (EditText) findViewById(R.id.etEmail);
        etEventID = (EditText) findViewById(R.id.etEventID);
        etDateAndTime = (EditText) findViewById(R.id.etDateAndTime);
        bSave = (Button) findViewById(R.id.bSave);
        bSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bSave) {
            try {
                Class checkincheckout = Class.forName("com.example.cityuregistrationapp.CheckInCheckOut");
                Intent i = new Intent(LoginScreen.this, checkincheckout);
                startActivity(i);
            } catch (ClassNotFoundException c) {
                c.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveInput();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetInput();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveInput();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        resetInput();
    }

    protected void saveInput() {
        email = etEmail.getText().toString();
        eventID = etEventID.getText().toString();
        dateAndTime = etDateAndTime.getText().toString();
    }

    protected void resetInput() {
        etEmail.setText(email);
        etEventID.setText(eventID);
        etDateAndTime.setText(dateAndTime);
    }
}
