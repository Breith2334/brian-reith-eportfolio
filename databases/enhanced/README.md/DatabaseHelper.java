
package com.example.event_reminder_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EventReminder.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT UNIQUE NOT NULL, " +
            "password TEXT NOT NULL)"
        );

        db.execSQL(
            "CREATE TABLE events (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "event_date TEXT NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db, int oldVersion, int newVersion) {
        // Add database migration logic when the schema changes.
    }

    // Save an event
    public boolean addEvent(String name, String date) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("event_date", date);

        return db.insert("events", null, values) != -1;
    }

    // Retrieve all saved events
    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        try (Cursor cursor = db.rawQuery(
                "SELECT id, name, event_date FROM events ORDER BY id DESC",
                null)) {

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String date = cursor.getString(2);

                events.add(new Event(id, name, date));
            }
        }

        return events;
    }

    // Search event names
    public ArrayList<Event> searchEvents(String searchText) {
        ArrayList<Event> events = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        try (Cursor cursor = db.query(
                "events",
                new String[]{"id", "name", "event_date"},
                "name LIKE ?",
                new String[]{"%" + searchText + "%"},
                null, null, "name ASC")) {

            while (cursor.moveToNext()) {
                events.add(new Event(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                ));
            }
        }

        return events;
    }

    // Update an existing event
    public boolean updateEvent(int id, String name, String date) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("event_date", date);

        return db.update(
            "events", values, "id = ?",
            new String[]{String.valueOf(id)}
        ) > 0;
    }

    // Delete a saved event
    public boolean deleteEvent(int id) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(
            "events", "id = ?",
            new String[]{String.valueOf(id)}
        ) > 0;
    }

    // Validate an existing login
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();

        try (Cursor cursor = db.query(
                "users",
                new String[]{"id"},
                "username = ? AND password = ?",
                new String[]{username, password},
                null, null, null)) {

            return cursor.moveToFirst();
        }
    }

    // Create a new account
    public boolean createUser(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);

        return db.insert("users", null, values) != -1;
    }
}
