 Cursor cursor = db.rawQuery(
            "SELECT id, name, event_date FROM events " +
            "WHERE name LIKE ? ORDER BY event_date",
            new String[]{"%" + searchText + "%"}
    );

    if (cursor.moveToFirst()) {
        do {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String date = cursor.getString(2);

            events.add(new Event(id, name, date));
        } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return events;
}
