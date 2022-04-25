package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splashscreen.response_object.AdminResponseObject;
import com.example.splashscreen.response_object.DriverResponseObject;
import com.example.splashscreen.services.EndPoints;
import com.example.splashscreen.services.Repository;
import com.example.splashscreen.services.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;

public class DriverSignUp extends AppCompatActivity {

    private static final String TAG = "TAG";
    EditText edtdrivernamesignup,edtdrivermailsignup,edtdrivercontactsignup,edtdriverpasssignup;
    RadioButton radiobtndrivergen;
    TextView tvdriveraccount;
    RadioGroup dgender;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_sign_up);

        Button signupbtndriver = (Button) findViewById(R.id.signupbtndriver);
        Button signinbtndriver = (Button) findViewById(R.id.signinbtndriver);
        edtdrivernamesignup = (EditText) findViewById(R.id.edtdrivernamesignup);
        edtdrivermailsignup = (EditText) findViewById(R.id.edtdrivermailsignup);
        edtdriverpasssignup = (EditText) findViewById(R.id.edtdriverpasssignup);
        edtdrivercontactsignup = (EditText) findViewById(R.id.edtdrivercontactsignup);
        dgender = (RadioGroup) findViewById(R.id.dgender);
        tvdriveraccount = (TextView) findViewById(R.id.tvdriveraccount);

        tvdriveraccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DriverSignUp.this,Driver.class);
                startActivity(intent);
            }
        });

        signupbtndriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get all the values
                String driversignupname = edtdrivernamesignup.getText().toString();
                String driversignupmail = edtdrivermailsignup.getText().toString();
                String driversignuppass = edtdriverpasssignup.getText().toString();
                String driversignupcontact = edtdrivercontactsignup.getText().toString();
                int selectedId = dgender.getCheckedRadioButtonId();
                radiobtndrivergen = (RadioButton) findViewById(selectedId);
                String driversigngender = radiobtndrivergen.getText().toString();

                boolean check = validateInfo(driversignupname,driversignupmail,driversignuppass,driversignupcontact,driversigngender);
                if (check==true){
                    // create driver
                    createDriver(
                            driversignupname,
                            driversignupmail,
                            driversignuppass,
                            driversignupcontact,
                            driversigngender
                    );
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry Check Info again",Toast.LENGTH_SHORT).show();
                }
            }

    private boolean validateInfo(String driversignupname, String driversignupmail, String driversignuppass, String driversignupcontact, String driversigngender) {
        if (driversignupname.length()==0){
            edtdrivernamesignup.requestFocus();
            edtdrivernamesignup.setError("Field cannot be empty");
            return false;
        }else if (driversignupmail.length()==0){
            edtdrivermailsignup.requestFocus();
            edtdrivermailsignup.setError("Field cannot be empty");
            return false;
        }else if (driversignuppass.length()==0) {
            edtdriverpasssignup.requestFocus();
            edtdriverpasssignup.setError("Field cannot be empty");
            return false;
        }else if (driversignupcontact.length()==0) {
            edtdrivercontactsignup.requestFocus();
            edtdrivercontactsignup.setError("Field cannot be empty");
            return false;
        }else if (driversigngender.length()==0) {
            radiobtndrivergen.requestFocus();
            radiobtndrivergen.setError("Select one");
            return false;
        }
        else if (driversignuppass.length()<=5){
            edtdriverpasssignup.requestFocus();
            edtdriverpasssignup.setError("Minimum 6 Characters Required!!");
            return false;
        }else{
            return  true;
        }
    }
    });
        signinbtndriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DriverSignUp.this, Driver.class);
                startActivity(intent);
                finish();
            }

            private boolean validateInfo(String driversignupname, String driversignupmail, String driversignuppass, String driversignupcontact) {
                if (driversignupname.length()==0){
                    edtdrivernamesignup.requestFocus();
                    edtdrivernamesignup.setError("Field cannot be empty");
                    return false;
                }else if (driversignupmail.length()==0){
                    edtdrivermailsignup.requestFocus();
                    edtdrivermailsignup.setError("Field cannot be empty");
                    return false;
                }else if (driversignuppass.length()==0) {
                    edtdriverpasssignup.requestFocus();
                    edtdriverpasssignup.setError("Field cannot be empty");
                    return false;
                }else if (driversignupcontact.length()==0) {
                    edtdrivercontactsignup.requestFocus();
                    edtdrivercontactsignup.setError("Field cannot be empty");
                    return false;
                }
                else if (driversignuppass.length()<=5){
                    edtdriverpasssignup.requestFocus();
                    edtdriverpasssignup.setError("Minimum 6 Characters Required!!");
                    return false;
                }else{
                    return  true;
                }
            }
        });
    }

    private void createDriver(
            String drivername,
            String drivermail,
            String driverpass,
            String drivercontact,
            String drivergender) {
        Log.d(TAG, "createDriver: " + "called");
        Repository repository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(Repository.class);
        Call<DriverResponseObject> call = repository.createDriver(
                drivername,
                drivermail,
                driverpass,
                drivercontact,
                drivergender
        );
        call.enqueue(new Callback<DriverResponseObject>() {
            @Override
            public void onResponse(Call<DriverResponseObject> call, retrofit2.Response<DriverResponseObject> response) {
                Log.d(TAG, "onResponse: " + response);
                Log.d(TAG,"onResponse: "+response.body());
                if (response.isSuccessful() && response.code() == 200) {
                    Intent intent = new Intent(DriverSignUp.this, Driver.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "Error!!!" + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DriverResponseObject> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                System.out.println();
                Toast.makeText(getBaseContext(), "Error! Check internet connection and try again... " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

}