package com.tafsir.notesapp;

/**
 * Note - simple data model representing a single note.
 * Beginner-friendly: plain Java object (POJO), no database needed.
 */
public class Note {

    private String title;
    private String content;
    private String category;   // e.g. "General", "Study", "Personal"
    private boolean important; // toggle to mark as important

    public Note(String title, String content, String category, boolean important) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.important = important;
    }

    // Getters
    public String getTitle()    { return title; }
    public String getContent()  { return content; }
    public String getCategory() { return category; }
    public boolean isImportant(){ return important; }

    // Setters (used when editing)
    public void setTitle(String title)       { this.title = title; }
    public void setContent(String content)   { this.content = content; }
    public void setCategory(String category) { this.category = category; }
    public void setImportant(boolean important) { this.important = important; }
}
