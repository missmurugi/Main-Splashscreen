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
import com.example.splashscreen.services.EndPoints;
import com.example.splashscreen.services.Repository;
import com.example.splashscreen.services.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;

public class AdminSignUp extends AppCompatActivity {
    
    private static final String TAG = "TAG";
    EditText edtadmnamesignup,edtadmmailsignup,edtadmpasssignup,edtadmcontactreg;
    RadioButton radiobtnadmmale,radiobtnadmfemale;
    RadioGroup radioGroup;
    Button signupbtnadm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);

        Button signupbtnadm = (Button) findViewById(R.id.signupbtnadm);
        edtadmnamesignup = (EditText) findViewById(R.id.edtadmnamesignup);
        edtadmmailsignup = (EditText) findViewById(R.id.edtadmmailsignup);
        edtadmpasssignup = (EditText) findViewById(R.id.edtadmpasssignup);
        edtadmcontactreg = (EditText) findViewById(R.id.edtadmcontactreg);

        signupbtnadm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get all the values
                String adminsignupname = edtadmnamesignup.getText().toString();
                String adminsignupmail = edtadmmailsignup.getText().toString();
                String adminsignuppass = edtadmpasssignup.getText().toString();
                String adminsignupcontact = edtadmcontactreg.getText().toString();

                boolean check = validateInfo(adminsignupname,adminsignupmail,adminsignuppass,adminsignupcontact);
                if (check==true){
                    // create admin
                    createAdmin(
                            adminsignupname,
                            adminsignupmail,
                            adminsignuppass,
                            adminsignupcontact
                    );
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry Check Info again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateInfo(String adminsignupname, String adminsignupmail, String adminsignuppass,String adminsignupcontact) {
        if (adminsignupname.length()==0){
            edtadmnamesignup.requestFocus();
            edtadmnamesignup.setError("Field cannot be empty");
            return false;
        }else if (adminsignupmail.length()==0){
            edtadmmailsignup.requestFocus();
            edtadmmailsignup.setError("Field cannot be empty");
            return false;
        }else if (adminsignupcontact.length()==0) {
            edtadmcontactreg.requestFocus();
            edtadmcontactreg.setError("Field cannot be empty");
            return false;
        }else if (adminsignuppass.length()==0){
            edtadmpasssignup.requestFocus();
            edtadmpasssignup.setError("Field cannot be empty");
            return false;
        }else if (adminsignuppass.length()<=5){
            edtadmpasssignup.requestFocus();
            edtadmpasssignup.setError("Minimum 6 Characters Required!!");
            return false;
        }else{
            return  true;
        }
    }

    public void createAdmin(
            String name,
            String email,
            String password,
            String contact
            ) {
        Log.d(TAG, "createAdmin: "+"called");
        Repository repository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(Repository.class);
        Call<AdminResponseObject> call = repository.createAdmin(
                name,
                email,
                password,
                contact
                );
        call.enqueue(new Callback<AdminResponseObject>() {
            @Override
            public void onResponse(Call<AdminResponseObject> call, retrofit2.Response<AdminResponseObject> response) {
                Log.d(TAG, "onResponse: "+response);
                if (response.isSuccessful() && response.code() == 200) {
                    Intent intent = new Intent(AdminSignUp.this, Admin.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getBaseContext(), "Error!!!"+response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AdminResponseObject> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
                System.out.println();
                Toast.makeText(getBaseContext(), "Error! Check internet connection and try again... "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    }// end class

