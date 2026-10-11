package com.example.event_reminder_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventGrid_Activity extends AppCompatActivity {

    private List<Event> eventList = new ArrayList<>();
    private EventAdapter adapter;
    private EditText etEventName, etEventDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_grid);

        etEventName = findViewById(R.id.etEventName);
        etEventDate = findViewById(R.id.etEventDate);

        // Set up RecyclerView
        RecyclerView rvEvents = findViewById(R.id.rvEvents);
        adapter = new EventAdapter(eventList, position -> {
            eventList.remove(position);
            adapter.notifyItemRemoved(position);
        });
        rvEvents.setLayoutManager(new LinearLayoutManager(this));
        rvEvents.setAdapter(adapter);

        // Add event button
        Button btnAddEvent = findViewById(R.id.btnAddEvent);
        btnAddEvent.setOnClickListener(v -> {
            String name = etEventName.getText().toString().trim();
            String date = etEventDate.getText().toString().trim();
            if (name.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                return;
            }
            eventList.add(new Event(name, date));
            adapter.notifyItemInserted(eventList.size() - 1);
            etEventName.setText("");
            etEventDate.setText("");
        });

        // SMS Permission button
        Button btnSmsPermission = findViewById(R.id.btnSmsPermission);
        btnSmsPermission.setOnClickListener(v -> {
            Intent intent = new Intent(EventGrid_Activity.this, SMSPermission_Activity.class);
            startActivity(intent);
        });
    }
}