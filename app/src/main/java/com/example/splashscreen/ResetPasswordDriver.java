package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPasswordDriver extends AppCompatActivity  {

    EditText edtdriverpreviouspass,edtdrivercurrentpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_driver);

        Button btndriverreset = (Button) findViewById(R.id.btndriverreset);
        edtdriverpreviouspass = (EditText) findViewById(R.id.edtdriverpreviouspass);
        edtdrivercurrentpass = (EditText) findViewById(R.id.edtdrivercurrentpass);


        btndriverreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get all the values
                String driverprevious = edtdriverpreviouspass.getText().toString();
                String drivercurrent = edtdrivercurrentpass.getText().toString();

                boolean check = validateInfo(driverprevious,drivercurrent);
                if (check==true){
                    Intent intent = new Intent(ResetPasswordDriver.this, Driver.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry Check Info again",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validateInfo(String driverprevious, String drivercurrent) {
        if (driverprevious.length()==0){
            edtdriverpreviouspass.requestFocus();
            edtdriverpreviouspass.setError("Field cannot be empty");
            return false;
        }else if (driverprevious.length()<=5){
            edtdriverpreviouspass.requestFocus();
            edtdriverpreviouspass.setError("Minimum 6 Characters Required!!");
            return false;
        }
        else if (drivercurrent.length()==0){
            edtdrivercurrentpass.requestFocus();
            edtdrivercurrentpass.setError("Field cannot be empty");
            return false;
        }else if (drivercurrent.length()<=5){
            edtdrivercurrentpass.requestFocus();
            edtdrivercurrentpass.setError("Minimum 6 Characters Required!!");
            return false;
        }else{
            return  true;
        }
    }

}