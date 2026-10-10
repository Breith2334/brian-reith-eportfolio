This project is an Android Event Reminder App built with Java and SQLite. It allows users to manage event information and provides a simple interface for viewing saved events.
This README documents the original version of the app and the database enhancement added for the CS 499 ePortfolio.
Original Version
The original app includes database and event-management functionality:
- Create a user account and check login credentials.
- Add events with a name and date.
- Retrieve saved events from the SQLite database.
- Display events in a RecyclerView.
- Edit existing events.
- Delete events.
- Open the SMS permission screen.
The DatabaseHelper.java class manages database operations. The EventGrid_Activity.java class connects the event interface to the database and updates the displayed event list.
Original database behavior
The original app can retrieve all saved events from the database. It does not include a database search that filters events by a name entered by the user.
Enhanced Version: Search Events by Name
The enhancement adds the ability to search for events by name.
