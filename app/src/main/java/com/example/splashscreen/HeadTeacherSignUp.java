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

import com.example.splashscreen.response_object.DriverResponseObject;
import com.example.splashscreen.response_object.HeadTeacherResponseObject;
import com.example.splashscreen.services.EndPoints;
import com.example.splashscreen.services.Repository;
import com.example.splashscreen.services.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;

public class HeadTeacherSignUp extends AppCompatActivity {

    private static final String TAG = "TAG";
    EditText edtheadnamesignup,edtheadmailsignup,edtheadcontactsignup,edtheadpasssignup;
    RadioButton radiobtnheadgen;
    TextView tvheadteacheraccount;
    RadioGroup headteachergender;
    Button signupbtnheadteacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_teacher_sign_up);

        Button signupbtnheadteacher = (Button) findViewById(R.id.signupbtnheadteacher);
        edtheadnamesignup = (EditText) findViewById(R.id.edtheadnamesignup);
        edtheadmailsignup = (EditText) findViewById(R.id.edtheadmailsignup);
        edtheadcontactsignup = (EditText) findViewById(R.id.edtheadcontactsignup);
        edtheadpasssignup = (EditText) findViewById(R.id.edtheadpasssignup);
        headteachergender = (RadioGroup) findViewById(R.id.headteachergender);
        tvheadteacheraccount = (TextView) findViewById(R.id.tvheadteacheraccount);

        tvheadteacheraccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HeadTeacherSignUp.this,HeadTeacher.class);
                startActivity(intent);
            }
        });

        signupbtnheadteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get all the values
                String headteachersignupname = edtheadnamesignup.getText().toString();
                String headteachersignupmail = edtheadmailsignup.getText().toString();
                String headteachercontact = edtheadcontactsignup.getText().toString();
                String headteachersignuppass = edtheadpasssignup.getText().toString();
                int selectedId = headteachergender.getCheckedRadioButtonId();
                radiobtnheadgen = (RadioButton) findViewById(selectedId);
                String headteachersigngender = radiobtnheadgen.getText().toString();

                boolean check = validateInfo(headteachersignupname,headteachersignupmail,headteachercontact,headteachersignuppass,headteachersigngender);
                if (check==true){
                    // create headteacher
                    createHeadTeacher(
                            headteachersignupname,
                            headteachersignupmail,
                            headteachercontact,
                            headteachersignuppass,
                            headteachersigngender
                    );
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry Check Info again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createHeadTeacher(
            String headteachername,
            String headteachermail,
            String headteachercontact,
            String headteacherpass,
            String headteachergender) {
        Log.d(TAG, "createHeadTeacher: " + "called");
        Repository repository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(Repository.class);
        Call<HeadTeacherResponseObject> call = repository.createHeadTeacher(
                headteachername,
                headteachermail,
                headteachercontact,
                headteacherpass,
                headteachergender
        );
        call.enqueue(new Callback<HeadTeacherResponseObject>() {
            @Override
            public void onResponse(Call<HeadTeacherResponseObject> call, retrofit2.Response<HeadTeacherResponseObject> response) {
                Log.d(TAG, "onResponse: " + response);
                Log.d(TAG,"onResponse: "+response.body());
                if (response.isSuccessful() && response.code() == 200) {
                    Intent intent = new Intent(HeadTeacherSignUp.this, HeadTeacher.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "Error!!!" + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<HeadTeacherResponseObject> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                System.out.println();
                Toast.makeText(getBaseContext(), "Error! Check internet connection and try again... " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

    private boolean validateInfo(String headteachersignupname, String headteachersignupmail, String headteachercontact, String headteachersignuppass, String headteachersigngender) {
        if (headteachersignupname.length()==0){
            edtheadnamesignup.requestFocus();
            edtheadnamesignup.setError("Field cannot be empty");
            return false;
        }else if (headteachersignupmail.length()==0){
            edtheadmailsignup.requestFocus();
            edtheadmailsignup.setError("Field cannot be empty");
            return false;
        }else if (headteachercontact.length()==0) {
            edtheadcontactsignup.requestFocus();
            edtheadcontactsignup.setError("Field cannot be empty");
            return false;
        }else if (headteachersignuppass.length()==0) {
            edtheadpasssignup.requestFocus();
            edtheadpasssignup.setError("Field cannot be empty");
            return false;
        }else if (headteachersigngender.length()==0) {
            radiobtnheadgen.requestFocus();
            radiobtnheadgen.setError("Select one");
            return false;
        }
        else if (headteachersignuppass.length()<=5){
            edtheadpasssignup.requestFocus();
            edtheadpasssignup.setError("Minimum 6 Characters Required!!");
            return false;
        }else{
            return  true;
        }
    }
}