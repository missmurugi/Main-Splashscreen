package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class Absentee extends AppCompatActivity {

    Button btnabsentclose;
    CalendarView absencecalender;
    TextView tvclickdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absentee);

        Button btnabsentclose = (Button) findViewById(R.id.btnabsentclose);
        TextView tvclickdate = (TextView) findViewById(R.id.tvclickdate);
        CalendarView absencecalender = (CalendarView) findViewById(R.id.absencecalender);

        absencecalender.setVisibility(View.GONE);
        absencecalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
                tvclickdate.setText(String.valueOf(year)+"/"+String.valueOf(month)+"/"+String.valueOf(date));
                absencecalender.setVisibility(View.GONE);
            }
        });

        //calendar onclick listener
        tvclickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                absencecalender.setVisibility(absencecalender.isShown()?View.GONE:View.VISIBLE);
            }
        });

        btnabsentclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Absentee.this,ParentDashboard.class);
                startActivity(intent);
            }
        });
    }
}