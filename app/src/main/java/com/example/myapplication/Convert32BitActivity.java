package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Convert32BitActivity extends AppCompatActivity {
    private EditText decimalEditText;
    private Button convertButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convert_32_bit);

        decimalEditText = findViewById(R.id.decimalEditText32);
        convertButton = findViewById(R.id.convertButton32);
        resultTextView = findViewById(R.id.resultTextView32);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String decimalString = decimalEditText.getText().toString();
                float decimal = Float.parseFloat(decimalString);

                int binary = Float.floatToIntBits(decimal);
                String binaryString = Integer.toBinaryString(binary);

                resultTextView.setText("The converted " + decimal + " is:\n" + binaryString);
            }
        });
    }
}