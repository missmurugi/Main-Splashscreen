package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardHeadTeacher extends AppCompatActivity {

    Button headlogoutbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_head_teacher);

        Button headlogoutbutton = (Button) findViewById(R.id.headlogoutbutton);

        headlogoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardHeadTeacher.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}