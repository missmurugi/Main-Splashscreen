package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Driver extends AppCompatActivity  {

    private static final String TAG = "TAG";
    EditText edtdrivername,edtdriverpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_driver);

        Button driverlogin = (Button) findViewById(R.id.driverlogin);
        edtdrivername = (EditText) findViewById(R.id.edtdrivername);
        edtdriverpass = (EditText) findViewById(R.id.edtdriverpass);
        TextView tvdriversignup = (TextView) findViewById(R.id.tvdriversignup);
        TextView tvdriverforgot = (TextView) findViewById(R.id.tvdriverforgot);

        // fetch test
        fetchAllDriver();

        driverlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //Get all the values
                String drivername = edtdrivername.getText().toString();
                String driverpass = edtdriverpass.getText().toString();

                boolean check = validateInfo(drivername,driverpass);
                if (check==true){
                    Intent intent = new Intent(Driver.this, DashboardDriver.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry Check Info again",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvdriversignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Driver.this, DriverSignUp.class);
                startActivity(intent);
                finish();
            }
        });

        tvdriverforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Driver.this, ResetPasswordDriver.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validateInfo(String drivername, String driverpass) {
        if (drivername.length()==0){
            edtdrivername.requestFocus();
            edtdrivername.setError("Field cannot be empty");
            return false;
        }else if (driverpass.length()==0){
            edtdriverpass.requestFocus();
            edtdriverpass.setError("Field cannot be empty");
            return false;
        }else if (driverpass.length()<=5){
            edtdriverpass.requestFocus();
            edtdriverpass.setError("Minimum 6 Characters Required!!");
            return false;
        }else{
            return  true;
        }
    }

    private void fetchAllDriver() {
        Log.d(TAG, "fetchDriver: "+"called");
        Repository repository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(Repository.class);
        Call<List<DriverResponseObject>> call = repository.fetchAllDriver();
        call.enqueue(new Callback<List<DriverResponseObject>>() {
            @Override
            public void onResponse(Call<List<DriverResponseObject>> call, retrofit2.Response<List<DriverResponseObject>> response) {
                Log.d(TAG, "onResponse: "+response);
                Log.d(TAG, "onResponse: "+response.body().get(0));
                if (response.isSuccessful() && response.code() == 200) {
                    List<DriverResponseObject> driverResponseObjectList = response.body();
                    if (driverResponseObjectList != null)
                        Toast.makeText(Driver.this,"Successful",Toast.LENGTH_SHORT).show();
                    //getBaseContext(),
                    //"Successful"+adminResponseObjectList.get(4).getAdminname(),
                    // Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getBaseContext(), "Error! Check internet connection and try again... "+response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<DriverResponseObject>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
                System.out.println();
                Toast.makeText(getBaseContext(), "Error! Check internet connection and try again... "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}


