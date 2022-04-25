package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splashscreen.response_object.ClassTeacherResponseObject;
import com.example.splashscreen.response_object.HeadTeacherResponseObject;
import com.example.splashscreen.services.EndPoints;
import com.example.splashscreen.services.Repository;
import com.example.splashscreen.services.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Teacher extends AppCompatActivity {

    private static final String TAG = "TAG";
    EditText edtteachername,edtteacherpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        Button teacherlogin = (Button) findViewById(R.id.teacherlogin);
        edtteachername = (EditText) findViewById(R.id.edtteachername);
        edtteacherpass = (EditText) findViewById(R.id.edtteacherpass);
        TextView tvteachersignup = (TextView) findViewById(R.id.tvteachersignup);
        TextView tvteacherforgot = (TextView) findViewById(R.id.tvteacherforgot);

        //loginteacher
        loginClassTeacher();

        teacherlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get all the values
                String teachersignupname = edtteachername.getText().toString();
                String teachersignuppass = edtteacherpass.getText().toString();

                boolean check = validateInfo(teachersignupname,teachersignuppass);
                if (check==true){
                    //loginclassteacher
                    loginClassTeacher(
                            teachersignupname,
                            teachersignuppass
                    );
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry Check Info again",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvteachersignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Teacher.this, TeacherSignUp.class);
                startActivity(intent);
                finish();
            }
        });

        tvteacherforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Teacher.this, ResetPasswordTeacher.class);
                startActivity(intent);
                finish();
            }
        });
        }

    private void loginClassTeacher() {
    }

    private void loginClassTeacher(
            String teachername,
            String teacherpass) {
        Log.d(TAG, "loginClassTeacher: "+"called");
        Repository repository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(Repository.class);
        Call<List<ClassTeacherResponseObject>> call = repository.loginClassTeacher(
                teachername,
                teacherpass
        );
        call.enqueue(new Callback<List<ClassTeacherResponseObject>>() {
            @Override
            public void onResponse(Call<List<ClassTeacherResponseObject>> call, retrofit2.Response<List<ClassTeacherResponseObject>> response) {
                Log.d(TAG, "onResponse: "+response);
                Log.d(TAG,"onResponse: "+response.body());
                if (response.isSuccessful() && response.code() == 200) {
                    Intent intent = new Intent(Teacher.this, DashboardTeacher.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getBaseContext(), "Error!!!"+response.message(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<ClassTeacherResponseObject>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
                System.out.println();
                Toast.makeText(getBaseContext(), "Error! Check internet connection and try again... "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private Boolean validateInfo(String teachersignupname, String teachersignuppass) {
            if (teachersignupname.length()==0){
                edtteachername.requestFocus();
                edtteachername.setError("Field cannot be empty");
                return false;
            }else if (teachersignuppass.length()==0){
                edtteacherpass.requestFocus();
                edtteacherpass.setError("Field cannot be empty");
                return false;
            }else if (teachersignuppass.length()<=5){
                edtteacherpass.requestFocus();
                edtteacherpass.setError("Minimum 6 Characters Required!!");
                return false;
            }else{
                return  true;
            }
    }
    }
