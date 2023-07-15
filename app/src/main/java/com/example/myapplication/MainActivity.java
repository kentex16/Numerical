package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button exitButton, ieee754Button, rootFindingButton, jacobiMethodButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ieee754Button = findViewById(R.id.IEEE754Button);
        ieee754Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openIEEE754Page();
            }
        });

        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeApp();
            }
        });

        rootFindingButton = findViewById(R.id.RootFindingButton);
        rootFindingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRootFindingPage();
            }
        });

        jacobiMethodButton = findViewById(R.id.JacobiMethodButton);
        jacobiMethodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openJacobiMethodPage();
            }
        });
    }

    private void closeApp() {
        new AlertDialog.Builder(this)
                .setTitle("Numerical and Symbolic Computation")
                .setMessage("Do you want to close the application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Nothing Happened", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    public void openIEEE754Page(){
        Intent intent = new Intent(this, IEEE754.class);
        startActivity(intent);
        overridePendingTransition(R.anim.scale_up, R.anim.scale_up);
    }

    public void openRootFindingPage(){
        Intent intent = new Intent(this, RootFindingActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.scale_up, R.anim.scale_up);
    }

    public void openJacobiMethodPage(){
        Intent intent = new Intent(this, JacobiActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.scale_up, R.anim.scale_up);
    }
    public void onBackPressed() {
        super.onBackPressed();
        // Add your navigation logic here
    }

}
