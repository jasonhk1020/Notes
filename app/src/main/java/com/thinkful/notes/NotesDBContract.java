package com.thinkful.notes;

import android.provider.BaseColumns;

/**
 * Created by jasonhk1020 on 4/27/2015.
 */
public class NotesDBContract {

    private NotesDBContract() {}

    /*When you change the schema you need to update the version number*/
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "notesdb";

    //Create Table Statements
    public static final String SQL_CREATE_NOTE = String.format(
            "CREATE TABLE %s ( %s TEXT, %s TEXT, %s DATETIME)",
            Note.TABLE_NAME, Note.COLUMN_NAME_NOTE_TEXT, Note.COLUMN_NAME_STATUS, Note.COLUMN_NAME_NOTE_DATE);

    public static final String SQL_CREATE_TAG = String.format(
            "CREATE TABLE %s ( %s TEXT)",
            Tag.TABLE_NAME, Tag.COLUMN_NAME_TAG_NAME);

    public static final String SQL_CREATE_NOTE_TAG = String.format(
            "CREATE TABLE %s ( %s INTEGER, %s INTEGER)",
            NoteTag.TABLE_NAME, NoteTag.COLUMN_NAME_NOTE_ID, NoteTag.COLUMN_NAME_TAG_ID);

    //Drop Table Statements
    public static final String SQL_DELETE_NOTE = String.format(
            "DROP TABLE IF EXISTS %s",Note.TABLE_NAME);

    public static final String SQL_DELETE_TAG = String.format(
            "DROP TABLE IF EXISTS %s", Tag.TABLE_NAME);

    public static final String SQL_DELETE_NOTE_TAG = String.format(
            "DROP TABLE IF EXISTS %s", NoteTag.TABLE_NAME);

    //Inner class define tables
    public static abstract class Note implements BaseColumns {
        public static final String TABLE_NAME = "note";
        public static final String COLUMN_NAME_ID = "rowid";
        public static final String COLUMN_NAME_NOTE_TEXT = "note_text";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_NOTE_DATE = "note_date";
    }

    public static abstract class Tag implements BaseColumns {
        public static final String TABLE_NAME = "tag";
        public static final String COLUMN_NAME_TAG_NAME = "tag_text";
    }

    public static abstract class NoteTag implements BaseColumns {
        public static final String TABLE_NAME = "note_tag";
        public static final String COLUMN_NAME_NOTE_ID = "note_id";
        public static final String COLUMN_NAME_TAG_ID = "tag_id";
    }
}
