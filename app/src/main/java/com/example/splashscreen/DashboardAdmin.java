package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardAdmin extends AppCompatActivity {

    Button admlogoutbutton;
    TextView tvaddadmin,tvaddparent,tvaddheadteacher,tvaddbusdriver,tvaddclassteacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);

        Button admlogoutbutton = (Button) findViewById(R.id.admlogoutbutton);
        TextView tvaddadmin = (TextView) findViewById(R.id.tvaddadmin);
        TextView tvaddparent = (TextView) findViewById(R.id.tvaddparent);
        TextView tvaddheadteacher = (TextView) findViewById(R.id.tvaddheadteacher);
        TextView tvaddbusdriver = (TextView) findViewById(R.id.tvaddbusdriver);
        TextView tvaddclassteacher = (TextView) findViewById(R.id.tvaddclassteacher);

        admlogoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tvaddadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this,AdminSignUp.class);
                startActivity(intent);
                finish();
            }
        });

        tvaddparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this,ParentSignUp.class);
                startActivity(intent);
                finish();
            }
        });

        tvaddheadteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this,HeadTeacherSignUp.class);
                startActivity(intent);
                finish();
            }
        });

        tvaddbusdriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this,DriverSignUp.class);
                startActivity(intent);
                finish();
            }
        });


        tvaddclassteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this,TeacherSignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }
}