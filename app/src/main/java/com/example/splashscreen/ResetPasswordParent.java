package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPasswordParent extends AppCompatActivity  {

    EditText edtparentpreviouspass,edtparentcurrentpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_parent);

        Button btnparentreset = (Button) findViewById(R.id.signupbtnparent);
        edtparentpreviouspass = (EditText) findViewById(R.id.edtparentpreviouspass);
        edtparentcurrentpass = (EditText) findViewById(R.id.edtparentcurrentpass);

        btnparentreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get all the values
                String parentprevious = edtparentpreviouspass.getText().toString();
                String parentcurrent = edtparentcurrentpass.getText().toString();

                boolean check = validateInfo(parentprevious,parentcurrent);
                if (check==true){
                    Intent intent = new Intent(ResetPasswordParent.this, Parent.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry Check Info again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateInfo(String parentprevious, String parentcurrent) {
        if (parentprevious.length()==0){
            edtparentpreviouspass.requestFocus();
            edtparentpreviouspass.setError("Field cannot be empty");
            return false;
        }else if (parentprevious.length()<=5){
            edtparentpreviouspass.requestFocus();
            edtparentpreviouspass.setError("Minimum 6 Characters Required!!");
            return false;
        }
        else if (parentcurrent.length()==0){
            edtparentcurrentpass.requestFocus();
            edtparentcurrentpass.setError("Field cannot be empty");
            return false;
        }else if (parentcurrent.length()<=5){
            edtparentcurrentpass.requestFocus();
            edtparentcurrentpass.setError("Minimum 6 Characters Required!!");
            return false;
        }else{
            return  true;
        }
    }
}


