package com.example.event_reminder_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Launch your event scheduler screen
        val intent = Intent(this, EventGrid_Activity::class.java)
        startActivity(intent)

        // Prevent returning to MainActivity
        finish()
    }
}