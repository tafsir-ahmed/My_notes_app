package com.tafsir.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * MainActivity - Part 1 & Part 3 concepts applied:
 * - Activity as the primary UI container (Part 2)
 * - ListView to display scrollable list of notes (Part 3)
 * - Intent to navigate to another Activity (Part 2)
 */
public class MainActivity extends AppCompatActivity {

    // Static list so notes persist across activity navigation (simple beginner approach)
    public static ArrayList<Note> notesList = new ArrayList<>();

    private ListView listViewNotes;
    private NoteAdapter noteAdapter;
    private TextView textViewEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNotes = findViewById(R.id.listViewNotes);
        textViewEmpty = findViewById(R.id.textViewEmpty);

        // Set up custom adapter (Part 3 - custom layout component)
        noteAdapter = new NoteAdapter(this, notesList);
        listViewNotes.setAdapter(noteAdapter);

        // Add some starter notes so the list is not empty on first launch
        if (notesList.isEmpty()) {
            notesList.add(new Note("Welcome!", "Tap a note to view it, or press Add Note.", "General", false));
            notesList.add(new Note("Android Study", "Remember to practice the ViewHolder pattern for smooth ListViews.", "Study", true));
        }

        updateEmptyState();

        // Click a note to open DetailActivity (Part 2 - Intent navigation)
        listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note selected = notesList.get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("NOTE_INDEX", position);
                intent.putExtra("NOTE_TITLE", selected.getTitle());
                intent.putExtra("NOTE_CONTENT", selected.getContent());
                intent.putExtra("NOTE_CATEGORY", selected.getCategory());
                intent.putExtra("NOTE_IMPORTANT", selected.isImportant());
                startActivity(intent);
            }
        });

        // Long-press to delete a note
        listViewNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                notesList.remove(position);
                noteAdapter.notifyDataSetChanged();
                updateEmptyState();
                Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    // Called when returning from AddNoteActivity so the list refreshes
    @Override
    protected void onResume() {
        super.onResume();
        noteAdapter.notifyDataSetChanged();
        updateEmptyState();
    }

    // Show empty message when there are no notes
    private void updateEmptyState() {
        if (notesList.isEmpty()) {
            textViewEmpty.setVisibility(View.VISIBLE);
            listViewNotes.setVisibility(View.GONE);
        } else {
            textViewEmpty.setVisibility(View.GONE);
            listViewNotes.setVisibility(View.VISIBLE);
        }
    }

    // Button click: navigate to AddNoteActivity (Part 2 - Intent)
    public void onAddNoteClicked(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

    // Button click: navigate to AboutActivity (Part 2 - multiple views)
    public void onAboutClicked(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
