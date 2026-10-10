Enhanced Version: Search Events by Name
The enhancement adds the ability to search for events by name.
Files changed
- DatabaseHelper.java — adds a searchEvents(String searchText) method that queries SQLite for matching event names.
- EventGrid_Activity.java — connects the search interface to the database method and refreshes the event list with the results.
- activity_event_grid.xml — adds a search text field, a Search Events button, and a Show All Events button, using these IDs:
  - etSearchEvent
  - btnSearchEvent
  - btnShowAllEvents
The XML controls must exist for the corresponding findViewById() calls to work.
How the search works
The database query uses SQLite's LIKE operator:
The search term is passed as a selection argument with % wildcards. This allows partial-name searches. For example, searching for Doctor can return events such as Doctor Appointment if that event exists in the database.
When the user selects Search Events, the activity requests matching records from DatabaseHelper, replaces the current event list with those results, and refreshes the RecyclerView. Selecting Show All Events reloads all saved events.
The enhancement does not require a new database table or a database-version change, assuming the existing table and columns are named events, id, name, and event_date.
