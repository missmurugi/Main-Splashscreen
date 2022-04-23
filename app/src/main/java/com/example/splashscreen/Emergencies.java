package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Emergencies extends AppCompatActivity {

    Button emergencyexit;
    TextView tvaccident,tvtraffic,tvriot,tvflood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencies);

        Button emergencyexit = (Button) findViewById(R.id.emergencyexit);
        TextView tvaccident = (TextView) findViewById(R.id.tvaccident);
        TextView tvtraffic = (TextView) findViewById(R.id.tvtraffic);
        TextView tvflood = (TextView) findViewById(R.id.tvflood);

        emergencyexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Emergencies.this,DashboardDriver.class);
                startActivity(intent);
            }
        });
    }
}