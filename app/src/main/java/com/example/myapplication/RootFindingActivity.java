package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class RootFindingActivity extends AppCompatActivity {

    private Button buttonBisec;
    private Button buttonFalsi;
    private Button buttonTang;
    private Button buttonSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_finding);

        buttonBisec = findViewById(R.id.buttonBisec);
        buttonFalsi = findViewById(R.id.buttonFalsi);
        buttonTang = findViewById(R.id.buttonTang);
        buttonSec = findViewById(R.id.buttonSec);

        buttonBisec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RootFindingActivity.this, BisectionActivity.class);
                // Add any additional data or extras to the intent if needed
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.slide_out_left);
            }
        });

        buttonFalsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RootFindingActivity.this, FalsePositionActivity.class);
                // Add any additional data or extras to the intent if needed
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.slide_out_left);
            }
        });

        buttonTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RootFindingActivity.this, TangentActivity.class);
                // Add any additional data or extras to the intent if needed
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.slide_out_left);
            }
        });

        buttonSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RootFindingActivity.this, SecantActivity.class);
                // Add any additional data or extras to the intent if needed
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.slide_out_left);
            }
        });
    }
}