package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPasswordAdmin extends AppCompatActivity {

    EditText edtadmpreviouspass,edtadmcurrentpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_admin);

        Button btnadmreset = (Button) findViewById(R.id.btnadmreset);
        edtadmpreviouspass = (EditText) findViewById(R.id.edtadmpreviouspass);
        edtadmcurrentpass = (EditText) findViewById(R.id.edtadmcurrentpass);

        btnadmreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get all the values
                String adminprevious = edtadmpreviouspass.getText().toString();
                String admincurrent = edtadmcurrentpass.getText().toString();

                boolean check = validateInfo(adminprevious,admincurrent);
                if (check==true){
                    Intent intent = new Intent(ResetPasswordAdmin.this, Admin.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry Check Info again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateInfo(String adminprevious, String admincurrent) {
        if (adminprevious.length()==0){
            edtadmpreviouspass.requestFocus();
            edtadmpreviouspass.setError("Field cannot be empty");
            return false;
        }else if (adminprevious.length()<=5){
            edtadmpreviouspass.requestFocus();
            edtadmpreviouspass.setError("Minimum 6 Characters Required!!");
            return false;
        }
        else if (admincurrent.length()==0){
            edtadmcurrentpass.requestFocus();
            edtadmcurrentpass.setError("Field cannot be empty");
            return false;
        }else if (admincurrent.length()<=5){
            edtadmcurrentpass.requestFocus();
            edtadmcurrentpass.setError("Minimum 6 Characters Required!!");
            return false;
        }else{
            return  true;
        }
    }

}
