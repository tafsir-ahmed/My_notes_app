package com.tafsir.notesapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * NoteAdapter - Part 3 concepts applied:
 * - Custom layout component (list_item_note.xml)
 * - ViewHolder pattern for smooth scrolling performance
 * - ImageView to show an importance star icon
 */
public class NoteAdapter extends ArrayAdapter<Note> {

    private Context context;
    private ArrayList<Note> notes;

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
        this.context = context;
        this.notes = notes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // ViewHolder pattern: reuse views for performance (Part 3 - learned this!)
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_note, parent, false);
            holder = new ViewHolder();
            holder.textTitle    = convertView.findViewById(R.id.textItemTitle);
            holder.textCategory = convertView.findViewById(R.id.textItemCategory);
            holder.textPreview  = convertView.findViewById(R.id.textItemPreview);
            holder.imageImportant = convertView.findViewById(R.id.imageImportant);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Note note = notes.get(position);

        holder.textTitle.setText(note.getTitle());
        holder.textCategory.setText(note.getCategory());

        // Show a preview (first 60 chars of content)
        String preview = note.getContent();
        if (preview.length() > 60) {
            preview = preview.substring(0, 60) + "…";
        }
        holder.textPreview.setText(preview);

        // Show star if important (Part 3 - ImageView)
        if (note.isImportant()) {
            holder.imageImportant.setVisibility(View.VISIBLE);
            holder.imageImportant.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            holder.imageImportant.setVisibility(View.INVISIBLE);
        }

        // Alternate row background for readability
        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.parseColor("#F5F5F5"));
        } else {
            convertView.setBackgroundColor(Color.WHITE);
        }

        return convertView;
    }

    // ViewHolder caches the child views so findViewById isn't called every time
    static class ViewHolder {
        TextView textTitle;
        TextView textCategory;
        TextView textPreview;
        ImageView imageImportant;
    }
}
