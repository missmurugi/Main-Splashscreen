package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPasswordTeacher extends AppCompatActivity {

    EditText edtteacherpreviouspass,edtteachercurrentpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_teacher);

        Button btnteacherreset = (Button) findViewById(R.id.btnteacherreset);
        edtteacherpreviouspass = (EditText) findViewById(R.id.edtteacherpreviouspass);
        edtteachercurrentpass = (EditText) findViewById(R.id.edtteachercurrentpass);

        btnteacherreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get all the values
                String teacherprevious = edtteacherpreviouspass.getText().toString();
                String teachercurrent = edtteachercurrentpass.getText().toString();

                boolean check = validateInfo(teacherprevious,teachercurrent);
                if (check==true){
                    Intent intent = new Intent(ResetPasswordTeacher.this, Teacher.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry Check Info again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateInfo(String teacherprevious, String teachercurrent) {
        if (teacherprevious.length()==0){
            edtteacherpreviouspass.requestFocus();
            edtteacherpreviouspass.setError("Field cannot be empty");
            return false;
        }else if (teacherprevious.length()<=5){
            edtteacherpreviouspass.requestFocus();
            edtteacherpreviouspass.setError("Minimum 6 Characters Required!!");
            return false;
        }
        else if (teachercurrent.length()==0){
            edtteachercurrentpass.requestFocus();
            edtteachercurrentpass.setError("Field cannot be empty");
            return false;
        }else if (teachercurrent.length()<=5){
            edtteachercurrentpass.requestFocus();
            edtteachercurrentpass.setError("Minimum 6 Characters Required!!");
            return false;
        }else{
            return  true;
        }
    }
}