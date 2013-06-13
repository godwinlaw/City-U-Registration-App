package com.example.cityuregistrationapp.record;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by GodwinLaw on 8/6/13.
 */
public interface ParsedNdefRecord {
    /**
     * Returns a view to display this record.
     */
    public View getView(Activity activity, LayoutInflater inflater, ViewGroup parent, int offset);
}
