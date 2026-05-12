package com.tafsir.notesapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * AboutActivity - fourth Activity to satisfy "Multiple views" requirement.
 * Simple info page about the app and developer.
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void onBackClicked(View view) {
        finish();
    }
}
