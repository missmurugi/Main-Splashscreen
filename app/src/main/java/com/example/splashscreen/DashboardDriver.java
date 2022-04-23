package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardDriver extends AppCompatActivity {

    Button driverlogoutbutton;
    TextView tvnotifyparent,tvnotifyschool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_driver);

        Button driverlogoutbutton = (Button) findViewById(R.id.driverlogoutbutton);
        TextView tvnotifyparent = (TextView) findViewById(R.id.tvnotifyparent);
        TextView tvnotifyschool = (TextView) findViewById(R.id.tvnotifyschool);

        driverlogoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardDriver.this,MainActivity.class);
                startActivity(intent);
            }
        });

        tvnotifyparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardDriver.this,MapsActivity.class);
                startActivity(intent);
            }
        });

        tvnotifyschool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardDriver.this,Emergencies.class);
                startActivity(intent);
            }
        });
    }
}