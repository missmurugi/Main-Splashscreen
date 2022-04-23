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
import android.widget.Toast;

import com.example.splashscreen.response_object.ClassTeacherResponseObject;
import com.example.splashscreen.response_object.HeadTeacherResponseObject;
import com.example.splashscreen.services.EndPoints;
import com.example.splashscreen.services.Repository;
import com.example.splashscreen.services.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;

public class TeacherSignUp extends AppCompatActivity {

    private static final String TAG = "TAG";
    EditText edtclassnamesignup,edtclassmailsignup,edtclasscontactsignup,edtclasspasssignup;
    RadioButton radiobtnclassmale,radiobtnclassfemale;
    RadioGroup radioGroup;
    Button signupbtnteacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_sign_up);

        Button signupbtnteacher = (Button) findViewById(R.id.signupbtnteacher);
        edtclassnamesignup = (EditText) findViewById(R.id.edtclassnamesignup);
        edtclassmailsignup = (EditText) findViewById(R.id.edtclassmailsignup);
        edtclasscontactsignup = (EditText) findViewById(R.id.edtclasscontactsignup);
        edtclasspasssignup = (EditText) findViewById(R.id.edtclasspasssignup);

        signupbtnteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get all the values
                String teachersignupname = edtclassnamesignup.getText().toString();
                String teachersignupmail = edtclassmailsignup.getText().toString();
                String teachersignupcontact = edtclasscontactsignup.getText().toString();
                String teachersignuppass = edtclasspasssignup.getText().toString();

                boolean check = validateInfo(teachersignupname,teachersignupmail,teachersignupcontact,teachersignuppass);
                if (check==true){
                    // create classteacher
                    createHeadTeacher(
                            teachersignupname,
                            teachersignupmail,
                            teachersignupcontact,
                            teachersignuppass
                    );
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry Check Info again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createHeadTeacher(
            String teachername,
            String teachermail,
            String teachercontact,
            String teacherpass) {
        Log.d(TAG, "createClassTeacher: " + "called");
        Repository repository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(Repository.class);
        Call<ClassTeacherResponseObject> call = repository.createClassTeacher(
                teachername,
                teachermail,
                teachercontact,
                teacherpass
        );
        call.enqueue(new Callback<ClassTeacherResponseObject>() {
            @Override
            public void onResponse(Call<ClassTeacherResponseObject> call, retrofit2.Response<ClassTeacherResponseObject> response) {
                Log.d(TAG, "onResponse: " + response);
                if (response.isSuccessful() && response.code() == 200) {
                    Intent intent = new Intent(TeacherSignUp.this, Teacher.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "Error!!!" + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ClassTeacherResponseObject> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                System.out.println();
                Toast.makeText(getBaseContext(), "Error! Check internet connection and try again... " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

    private boolean validateInfo(String teachersignupname, String teachersignupmail, String teachersignupcontact, String teachersignuppass) {
        if (teachersignupname.length()==0){
            edtclassnamesignup.requestFocus();
            edtclassnamesignup.setError("Field cannot be empty");
            return false;
        }else if (teachersignupmail.length()==0){
            edtclassmailsignup.requestFocus();
            edtclassmailsignup.setError("Field cannot be empty");
            return false;
        }else if (teachersignupcontact.length()==0) {
            edtclasscontactsignup.requestFocus();
            edtclasscontactsignup.setError("Field cannot be empty");
            return false;
        }else if (teachersignuppass.length()==0) {
            edtclasspasssignup.requestFocus();
            edtclasspasssignup.setError("Field cannot be empty");
            return false;
        }
        else if (teachersignuppass.length()<=5){
            edtclasspasssignup.requestFocus();
            edtclasspasssignup.setError("Minimum 6 Characters Required!!");
            return false;
        }else{
            return  true;
        }
    }
}