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

        // fetch test
        fetchAllClassTeacher();

        teacherlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get all the values
                String classteachername = edtteachername.getText().toString();
                String classteacherpass = edtteacherpass.getText().toString();

                boolean check = validateInfo(classteachername,classteacherpass);
                if (check==true){
                    Intent intent = new Intent(Teacher.this, DashboardTeacher.class);
                    startActivity(intent);
                    finish();
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

    private void fetchAllClassTeacher() {
        Log.d(TAG, "fetch_classteacher: "+"called");
        Repository repository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(Repository.class);
        Call<List<ClassTeacherResponseObject>> call = repository.fetchAllClassTeacher();
        call.enqueue(new Callback<List<ClassTeacherResponseObject>>() {
            @Override
            public void onResponse(Call<List<ClassTeacherResponseObject>> call, retrofit2.Response<List<ClassTeacherResponseObject>> response) {
                Log.d(TAG, "onResponse: "+response);
                Log.d(TAG, "onResponse: "+response.body().get(0));
                if (response.isSuccessful() && response.code() == 200) {
                    List<ClassTeacherResponseObject> classTeacherResponseObjectList = response.body();
                    if (classTeacherResponseObjectList != null)
                        Toast.makeText(Teacher.this,"Successful",Toast.LENGTH_SHORT).show();
                    //getBaseContext(),
                    //"Successful"+adminResponseObjectList.get(4).getAdminname(),
                    // Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getBaseContext(), "Error! Check internet connection and try again... "+response.message(), Toast.LENGTH_LONG).show();
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

    private Boolean validateInfo(String classteachername, String classteacherpass) {
            if (classteachername.length()==0){
                edtteachername.requestFocus();
                edtteachername.setError("Field cannot be empty");
                return false;
            }else if (!classteachername.matches("[a-zA]+")){
                edtteachername.requestFocus();
                edtteachername.setError("Enter Aplhabets Only!!");
                return false;
            }else if (classteacherpass.length()==0){
                edtteacherpass.requestFocus();
                edtteacherpass.setError("Field cannot be empty");
                return false;
            }else if (classteacherpass.length()<=5){
                edtteacherpass.requestFocus();
                edtteacherpass.setError("Minimum 6 Characters Required!!");
                return false;
            }else{
                return  true;
            }
    }
    }
