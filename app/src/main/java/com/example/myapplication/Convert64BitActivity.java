package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Convert64BitActivity extends AppCompatActivity {
    private EditText decimalEditText;
    private Button convertButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convert_64_bit);

        decimalEditText = findViewById(R.id.decimalEditText);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String decimalString = decimalEditText.getText().toString();
                double decimal = Double.parseDouble(decimalString);

                long binary = Double.doubleToLongBits(decimal);
                String binaryString = Long.toBinaryString(binary);

                resultTextView.setText("The converted " + decimal + " is:\n" + binaryString);
            }
        });
    }
}