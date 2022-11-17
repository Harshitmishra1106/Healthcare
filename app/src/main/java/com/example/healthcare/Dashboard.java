package com.example.healthcare;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Button button = findViewById(R.id.button);
        ImageButton shareText = findViewById(R.id.btn);
        TextView temper = findViewById(R.id.temperature);
        TextView pulse = findViewById(R.id.pulse);
        TextView oxo   = findViewById(R.id.oxygen);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),History.class);
                startActivity(i);
            }
        });
        shareText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIt = new Intent();
                shareIt.setAction(Intent.ACTION_SEND);
                shareIt.putExtra(Intent.EXTRA_TEXT," Body Temperature:"+temper.getText().toString()+"\nPulse rate:"+pulse.getText().toString()+"\nOxygen Level:"+oxo.getText().toString());
                shareIt.setType("text/plain");

                Intent shareIntent = Intent.createChooser(shareIt, "Share Via");
                startActivity(shareIntent);
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Temperature");
        DatabaseReference myRef1 = database.getReference("Pulse");
        DatabaseReference myRef2 = database.getReference("Oxygen");

        final TextView temp = findViewById(R.id.temperature);
        final TextView pul = findViewById(R.id.pulse);
        final TextView oxy = findViewById(R.id.oxygen);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue().toString();
                temp.setText(value+" °C");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue().toString();
                pul.setText(value+" bpm");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue().toString();
                oxy.setText(value+"%");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}