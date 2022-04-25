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
import com.example.splashscreen.response_object.ParentResponseObject;
import com.example.splashscreen.services.EndPoints;
import com.example.splashscreen.services.Repository;
import com.example.splashscreen.services.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;

public class ParentSignUp extends AppCompatActivity {

    private static final String TAG = "TAG";
    EditText edtparentnamesignup, edtparentmailsignup, edtparentpasssignup, edtparentcontactsignup, edtparentaddressignup, edtparentchildsignup;
    Button signupbtnparent;
    TextView tvparentaccount;
    RadioButton radiobtnparentgen;
    RadioGroup pgender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_sign_up);

        Button signupbtnparent = (Button) findViewById(R.id.signupbtnparent);
        Button signinbtnparent = (Button) findViewById(R.id.signinbtnparent);
        edtparentnamesignup = (EditText) findViewById(R.id.edtparentnamesignup);
        edtparentmailsignup = (EditText) findViewById(R.id.edtparentmailsignup);
        edtparentpasssignup = (EditText) findViewById(R.id.edtparentpasssignup);
        edtparentcontactsignup = (EditText) findViewById(R.id.edtparentcontactsignup);
        edtparentchildsignup = (EditText) findViewById(R.id.edtparentchildsignup);
        edtparentaddressignup = (EditText) findViewById(R.id.edtparentaddressignup);
        tvparentaccount = (TextView) findViewById(R.id.tvparentaccount);
        pgender = (RadioGroup) findViewById(R.id.pgender);

        tvparentaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentSignUp.this,Parent.class);
                startActivity(intent);
            }
        });

        signupbtnparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get all the values
                String parentsignupname = edtparentnamesignup.getText().toString();
                String parentsignupmail = edtparentmailsignup.getText().toString();
                String parentsignuppassword = edtparentpasssignup.getText().toString();
                String parentsignupcontact = edtparentcontactsignup.getText().toString();
                String parentchildsignup = edtparentchildsignup.getText().toString();
                String parentsignupaddress = edtparentaddressignup.getText().toString();
                int selectedId = pgender.getCheckedRadioButtonId();
                radiobtnparentgen = (RadioButton) findViewById(selectedId);
                String parentsigngender = radiobtnparentgen.getText().toString();

                boolean check = validateInfo(parentsignupname, parentsignupmail, parentsignuppassword, parentsignupcontact, parentchildsignup, parentsignupaddress,parentsigngender);
                if (check == true) {
                    // create parent
                    createParent(
                            parentsignupname,
                            parentsignupmail,
                            parentsignuppassword,
                            parentsignupcontact,
                            parentchildsignup,
                            parentsignupaddress,
                            parentsigngender
                    );
                } else {
                    Toast.makeText(getApplicationContext(), "Sorry Check Info again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signinbtnparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentSignUp.this, Parent.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void createParent(
            String parentname,
            String parentmail,
            String parentpassword,
            String parentcontact,
            String parentchild,
            String parentaddress,
            String parentgender) {
        Log.d(TAG, "createParent: "+"called");
        Repository repository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(Repository.class);
        Call<ParentResponseObject> call = repository.createParent(
                parentname,
                parentmail,
                parentpassword,
                parentcontact,
                parentchild,
                parentaddress,
                parentgender
        );
        call.enqueue(new Callback<ParentResponseObject>() {
            @Override
            public void onResponse(Call<ParentResponseObject> call, retrofit2.Response<ParentResponseObject> response) {
                Log.d(TAG, "onResponse: "+response);
                Log.d(TAG,"onResponse: "+response.body());
                if (response.isSuccessful() && response.code() == 200) {
                    Intent intent = new Intent(ParentSignUp.this, Parent.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getBaseContext(), "Error!!!"+response.message(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ParentResponseObject> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
                System.out.println();
                Toast.makeText(getBaseContext(), "Error! Check internet connection and try again... "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    //input validation
    private boolean validateInfo(String parentsignupname, String parentsignupmail, String parentsignuppassword, String parentsignupcontact, String parentchildsignup, String parentsignupaddress, String parentsigngender) {
        if (parentsignupname.length()==0){
            edtparentnamesignup.requestFocus();
            edtparentnamesignup.setError("Field cannot be empty");
            return false;
        }else if (parentsignupmail.length()==0){
            edtparentmailsignup.requestFocus();
            edtparentmailsignup.setError("Field cannot be empty");
            return false;
        }else if (parentsignuppassword.length()==0) {
            edtparentpasssignup.requestFocus();
            edtparentpasssignup.setError("Field cannot be empty");
            return false;
        }else if (parentsignupcontact.length()==0){
            edtparentcontactsignup.requestFocus();
            edtparentcontactsignup.setError("Field cannot be empty");
            return false;
        } else if (parentchildsignup.length()==0){
            edtparentchildsignup.requestFocus();
            edtparentchildsignup.setError("Field cannot be empty");
            return false;
        } else if (parentsignupaddress.length()==0){
            edtparentaddressignup.requestFocus();
            edtparentaddressignup.setError("Field cannot be empty");
            return false;
        }else if (parentsigngender.length()==0) {
            radiobtnparentgen.requestFocus();
            radiobtnparentgen.setError("Select one");
            return false;
        }else if (parentsignuppassword.length()<=5){
            edtparentpasssignup.requestFocus();
            edtparentpasssignup.setError("Minimum 6 Characters Required!!");
            return false;
        }else{
            return  true;
        }
    }
    }



