package com.example.cityuregistrationapp;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;

import com.example.cityuregistrationapp.record.ParsedNdefRecord;
import com.example.cityuregistrationapp.record.UriRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GodwinLaw on 8/6/13.
 */
public class NdefMessageParser {

    //Utility class
    private NdefMessageParser() {

    }

    /** Parse an NdefMessage */
    public static List<ParsedNdefRecord> parse(NdefMessage msg) {
        return getRecords(msg.getRecords());
    }

    private static List<ParsedNdefRecord> getRecords(NdefRecord[] records) {
        return null;
    }
}
