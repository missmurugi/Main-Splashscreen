package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPasswordHeadTeacher extends AppCompatActivity {

    EditText edtheadteacherpreviouspass,edtheadteachercurrentpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_head_teacher);


        Button btnheadteacherreset = (Button) findViewById(R.id.btnheadteacherreset);
        edtheadteacherpreviouspass = (EditText) findViewById(R.id.edtheadteacherpreviouspass);
        edtheadteachercurrentpass = (EditText) findViewById(R.id.edtheadteachercurrentpass);

        btnheadteacherreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get all the values
                String headteacherprevious = edtheadteacherpreviouspass.getText().toString();
                String headteachercurrent = edtheadteachercurrentpass.getText().toString();
                boolean check = validateInfo(headteacherprevious,headteachercurrent);
                if (check==true){
                    Intent intent = new Intent(ResetPasswordHeadTeacher.this, HeadTeacher.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry Check Info again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateInfo(String headteacherprevious, String headteachercurrent) {
        if (headteacherprevious.length()==0){
            edtheadteacherpreviouspass.requestFocus();
            edtheadteacherpreviouspass.setError("Field cannot be empty");
            return false;
        }else if (headteacherprevious.length()<=5){
            edtheadteacherpreviouspass.requestFocus();
            edtheadteacherpreviouspass.setError("Minimum 6 Characters Required!!");
            return false;
        }
        else if (headteachercurrent.length()==0){
            edtheadteachercurrentpass.requestFocus();
            edtheadteachercurrentpass.setError("Field cannot be empty");
            return false;
        }else if (headteachercurrent.length()<=5){
            edtheadteachercurrentpass.requestFocus();
            edtheadteachercurrentpass.setError("Minimum 6 Characters Required!!");
            return false;
        }else{
            return  true;
        }
    }
}