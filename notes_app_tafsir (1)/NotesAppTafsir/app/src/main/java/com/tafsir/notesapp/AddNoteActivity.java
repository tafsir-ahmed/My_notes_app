package com.tafsir.notesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * AddNoteActivity - Part 1 & Part 2 concepts applied:
 * - A second Activity (multiple views requirement)
 * - UI components: EditText (text fields), Spinner (dropdown), Switch (toggler), Button
 * - Adds a new Note to the shared list and returns to MainActivity
 */
public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextContent;
    private Spinner spinnerCategory;
    private Switch switchImportant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle   = findViewById(R.id.editTextTitle);
        editTextContent = findViewById(R.id.editTextContent);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        switchImportant = findViewById(R.id.switchImportant);

        // Populate the category Spinner
        String[] categories = {"General", "Study", "Personal", "Work", "Ideas"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(spinnerAdapter);
    }

    // Save button clicked
    public void onSaveClicked(View view) {
        String title   = editTextTitle.getText().toString().trim();
        String content = editTextContent.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString();
        boolean important = switchImportant.isChecked();

        // Simple validation
        if (title.isEmpty()) {
            editTextTitle.setError("Please enter a title");
            return;
        }
        if (content.isEmpty()) {
            editTextContent.setError("Please enter some content");
            return;
        }

        // Add note to the shared list in MainActivity
        Note newNote = new Note(title, content, category, important);
        MainActivity.notesList.add(0, newNote); // Add to top

        Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
        finish(); // Return to MainActivity (onResume refreshes the list)
    }

    // Cancel button - just go back
    public void onCancelClicked(View view) {
        finish();
    }
}
