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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Parent extends AppCompatActivity  {

    private static final String TAG = "TAG";
    EditText edtparentname,edtparentpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_parent);

        Button parentlogin = (Button) findViewById(R.id.parentlogin);
        edtparentname = (EditText) findViewById(R.id.edtparentname);
        edtparentpass = (EditText) findViewById(R.id.edtparentpass);
        TextView tvparentsignup = (TextView) findViewById(R.id.tvparentsignup);
        TextView tvparentforgot = (TextView) findViewById(R.id.tvparentforgot);

        // fetch test
        fetchAllParent();

        parentlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get all the values
                String parentname = edtparentname.getText().toString();
                String parentpass = edtparentpass.getText().toString();

                boolean check = validateInfo(parentname,parentpass);
                if (check==true){
                    Intent intent = new Intent(Parent.this, ParentDashboard.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry Check Info again",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvparentsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Parent.this, ParentSignUp.class);
                startActivity(intent);
                finish();
            }
        });

        tvparentforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Parent.this, ResetPasswordParent.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void fetchAllParent() {
        Log.d(TAG, "fetchParent: "+"called");
        Repository repository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(Repository.class);
        Call<List<ParentResponseObject>> call = repository.fetchAllParent();
        call.enqueue(new Callback<List<ParentResponseObject>>() {
            @Override
            public void onResponse(Call<List<ParentResponseObject>> call, retrofit2.Response<List<ParentResponseObject>> response) {
                Log.d(TAG, "onResponse: "+response);
                Log.d(TAG, "onResponse: "+response.body().get(0));
                if (response.isSuccessful() && response.code() == 200) {
                    List<ParentResponseObject> parentResponseObjectList = response.body();
                    if (parentResponseObjectList != null)
                        Toast.makeText(Parent.this,"Successful",Toast.LENGTH_SHORT).show();
                    //getBaseContext(),
                    //"Successful"+adminResponseObjectList.get(4).getAdminname(),
                    // Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getBaseContext(), "Error! Check internet connection and try again... "+response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ParentResponseObject>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
                System.out.println();
                Toast.makeText(getBaseContext(), "Error! Check internet connection and try again... "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private Boolean validateInfo(String parentname, String parentpass) {
        if (parentname.length()==0){
            edtparentname.requestFocus();
            edtparentname.setError("Field cannot be empty");
            return false;
        }else if (parentpass.length()==0){
            edtparentpass.requestFocus();
            edtparentpass.setError("Field cannot be empty");
            return false;
        }else if (parentpass.length()<=5){
            edtparentpass.requestFocus();
            edtparentpass.setError("Minimum 6 Characters Required!!");
            return false;
        }else{
            return  true;
        }
    }

    }
