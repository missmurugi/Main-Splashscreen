package com.example.splashscreen;

import android.Manifest;
import android.app.assist.AssistStructure;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ActivityResultLauncher<String[]> locationPermissionRequest = registerForActivityResult(new ActivityResultContracts
                        .RequestMultiplePermissions(), result -> {
                    Boolean fineLocationGranted = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        fineLocationGranted = result.getOrDefault(
                                Manifest.permission.ACCESS_FINE_LOCATION, false);
                    }
                    Boolean coarseLocationGranted = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        coarseLocationGranted = result.getOrDefault(
                                Manifest.permission.ACCESS_COARSE_LOCATION,false);
                    }
                    if (fineLocationGranted != null && fineLocationGranted) {
                        // Precise location access granted.
                        Toast.makeText(this, "Location Permission for precise location granted", Toast.LENGTH_SHORT).show();
                    } else if (coarseLocationGranted != null && coarseLocationGranted) {
                        // Only approximate location access granted.
                        Toast.makeText(this, "Location Permission for Coarse location granted", Toast.LENGTH_SHORT).show();
                    } else {
                        // No location access granted.
                        Toast.makeText(this, "Location Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
        Button btnadminlogin = (Button) findViewById(R.id.btnadmlogin);
        btnadminlogin.setOnClickListener(this); // calling onClick() method
        Button btnparentlogin = (Button) findViewById(R.id.btnparentlogin);
        btnparentlogin.setOnClickListener(this);
        Button btndriverlogin = (Button) findViewById(R.id.btndriverlogin);
        btndriverlogin.setOnClickListener(this);
        Button btnclassteacherlogin = (Button) findViewById(R.id.btnclassteacherlogin);
        btnclassteacherlogin.setOnClickListener(this);
        Button btnheadteacherlogin = (Button) findViewById(R.id.btnheadteacherlogin);
        btnheadteacherlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnadmlogin:
                // code for button when user clicks btnadminlogin.
                Intent intent = new Intent(MainActivity.this,AdminSignUp.class);
                startActivity(intent);
                finish();
                break;

            case R.id.btnparentlogin:
                // do your code
                Intent intent1 = new Intent(MainActivity.this,ParentSignUp.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.btndriverlogin:
                // do your code
                Intent intent2 = new Intent(MainActivity.this,DriverSignUp.class);
                startActivity(intent2);
                finish();
                break;

            case R.id.btnclassteacherlogin:
                // do your code
                Intent intent3 = new Intent(MainActivity.this,TeacherSignUp.class);
                startActivity(intent3);
                finish();
                break;

            case R.id.btnheadteacherlogin:
                // do your code
                Intent intent4 = new Intent(MainActivity.this,HeadTeacherSignUp.class);
                startActivity(intent4);
                finish();
                break;

            default:
                break;
        }
    }
}







