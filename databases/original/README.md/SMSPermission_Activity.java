package com.example.event_reminder_app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SMSPermission_Activity extends AppCompatActivity {

    private static final int SMS_PERMISSION_CODE = 100;
    private TextView tvSmsStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smspermission);

        tvSmsStatus = findViewById(R.id.tvSmsStatus);
        Button btnRequestSMS = findViewById(R.id.btnRequestSMS);
        Button btnDeny = findViewById(R.id.btnDeny);

        // Check if permission already granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            tvSmsStatus.setText("SMS permission already granted. You will receive event notifications.");
        } else {
            tvSmsStatus.setText("SMS permission not yet granted.");
        }

        // Grant button
        btnRequestSMS.setOnClickListener(v -> {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    SMS_PERMISSION_CODE);
        });

        // Deny button
        btnDeny.setOnClickListener(v -> {
            tvSmsStatus.setText("SMS permission denied. App will continue without notifications.");
            Toast.makeText(this, "Notifications disabled.", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                tvSmsStatus.setText("SMS permission granted. You will receive event notifications.");
                Toast.makeText(this, "SMS notifications enabled!", Toast.LENGTH_SHORT).show();
            } else {
                tvSmsStatus.setText("SMS permission denied. App will continue without notifications.");
                Toast.makeText(this, "Notifications disabled. App will continue without SMS.", Toast.LENGTH_LONG).show();
            }
        }
    }
}