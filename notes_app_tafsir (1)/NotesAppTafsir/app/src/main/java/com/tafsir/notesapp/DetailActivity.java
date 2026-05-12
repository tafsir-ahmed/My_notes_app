package com.tafsir.notesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * DetailActivity - Part 2 concepts applied:
 * - Third Activity (multiple views)
 * - Receives data via Intent extras
 * - Displays full note with ImageView for the importance icon
 */
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Retrieve the data passed through the Intent
        Bundle extras = getIntent().getExtras();
        if (extras == null) { finish(); return; }

        String title     = extras.getString("NOTE_TITLE", "");
        String content   = extras.getString("NOTE_CONTENT", "");
        String category  = extras.getString("NOTE_CATEGORY", "");
        boolean important = extras.getBoolean("NOTE_IMPORTANT", false);

        TextView textTitle    = findViewById(R.id.detailTextTitle);
        TextView textCategory = findViewById(R.id.detailTextCategory);
        TextView textContent  = findViewById(R.id.detailTextContent);
        ImageView imageStar   = findViewById(R.id.detailImageStar);
        TextView textImportantLabel = findViewById(R.id.detailTextImportant);

        textTitle.setText(title);
        textCategory.setText("Category: " + category);
        textContent.setText(content);

        // Show star image when note is important (Part 3 - ImageView)
        if (important) {
            imageStar.setImageResource(android.R.drawable.btn_star_big_on);
            imageStar.setVisibility(View.VISIBLE);
            textImportantLabel.setVisibility(View.VISIBLE);
        } else {
            imageStar.setVisibility(View.GONE);
            textImportantLabel.setVisibility(View.GONE);
        }
    }

    // Back button
    public void onBackClicked(View view) {
        finish();
    }
}
