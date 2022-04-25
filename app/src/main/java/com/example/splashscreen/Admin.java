package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splashscreen.response_object.AdminResponseObject;
import com.example.splashscreen.response_object.ParentResponseObject;
import com.example.splashscreen.services.EndPoints;
import com.example.splashscreen.services.Repository;
import com.example.splashscreen.services.RetrofitClientInstance;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Admin extends AppCompatActivity  {

    private static final String TAG = "TAG";
    EditText edtadmname,edtadmpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin);

        Button admlogin = (Button) findViewById(R.id.admlogin);
        edtadmname = (EditText) findViewById(R.id.edtadmname);
        edtadmpass = (EditText) findViewById(R.id.edtadmpass);
        TextView tvadmsignup = (TextView) findViewById(R.id.tvadmsignup);
        TextView tvadmforgot = (TextView) findViewById(R.id.tvadmforgot);

        //loginparent
        loginAdmin();

        admlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get all the values
                String adminsignupname = edtadmname.getText().toString();
                String adminsignuppass = edtadmpass.getText().toString();
                
                boolean check = validateInfo(adminsignupname,adminsignuppass);
                if (check ==true){
                    //loginadmin
                    loginAdmin(
                            adminsignupname,
                            adminsignuppass
                    );
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry Check Info again",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvadmsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, AdminSignUp.class);
                startActivity(intent);
                finish();
            }
        });

        tvadmforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, ResetPasswordAdmin.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void loginAdmin() {
    }

    //login admin
    private void loginAdmin(
            String name,
            String password) {
        Log.d(TAG, "loginAdmin: " + "called");
        Repository repository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(Repository.class);
        Call<List<AdminResponseObject>> call = repository.loginAdmin(
                name,
                password
        );
        call.enqueue(new Callback<List<AdminResponseObject>>() {
            @Override
            public void onResponse(Call<List<AdminResponseObject>> call, retrofit2.Response<List<AdminResponseObject>> response) {
                Log.d(TAG, "onResponse: " + response);
                Log.d(TAG, "onResponse: " + response.body());
                if (response.isSuccessful() && response.code() == 200) {
                    Intent intent = new Intent(Admin.this, DashboardAdmin.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "Error!!!" + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<AdminResponseObject>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                System.out.println();
                Toast.makeText(getBaseContext(), "Error! Check internet connection and try again... " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private Boolean validateInfo(String adminsignupname, String adminsignuppass) {
        if (adminsignupname.length()==0){
            edtadmname.requestFocus();
            edtadmname.setError("Field cannot be empty");
            return false;
        }else if (adminsignuppass.length()==0){
            edtadmpass.requestFocus();
            edtadmpass.setError("Field cannot be empty");
            return false;
        }else if (adminsignuppass.length()<=5){
            edtadmpass.requestFocus();
            edtadmpass.setError("Minimum 6 Characters Required!!");
            return false;
        }else{
            return  true;
        }
    }

    }// end class