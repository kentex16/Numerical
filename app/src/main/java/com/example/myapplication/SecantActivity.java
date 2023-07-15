package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Locale;

public class SecantActivity extends AppCompatActivity {
    private EditText equationInput;
    private EditText xaInput;
    private EditText xbInput;
    private EditText precisionInput;
    private TextView resultText;
    private TableLayout iterationTable;

    private Expression f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secant);

        equationInput = findViewById(R.id.equation_input);
        xaInput = findViewById(R.id.xa_input);
        xbInput = findViewById(R.id.xb_input);
        precisionInput = findViewById(R.id.precision_input);
        resultText = findViewById(R.id.result_text);
        iterationTable = findViewById (R.id.iteration_table);

        Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSecant();
            }
        });
    }

    private void calculateSecant() {
        String equation = equationInput.getText().toString();
        double xa = Double.parseDouble(xaInput.getText().toString());
        double xb = Double.parseDouble(xbInput.getText().toString());
        double precision = Double.parseDouble(precisionInput.getText().toString());

        Expression f = new ExpressionBuilder(equation)
                .variable("x")
                .build();

        int step = 1;

        iterationTable.removeAllViews(); // Clear any existing rows

        TableRow headerRow = new TableRow(this);
        headerRow.setBackgroundColor(Color.parseColor("#DDDDDD"));

        String[] headers = {"i", "Xa", "Xb", "Xn", "Ya", "Yb", "Yn"};
        for (String header : headers) {
            TextView headerTextView = new TextView(this);
            headerTextView.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            headerTextView.setText(header);
            headerRow.addView(headerTextView);
        }

        iterationTable.addView(headerRow); // Add the header row to the table

        boolean condition = true;

        while (condition) {
            double fa = f.setVariable("x", xa).evaluate();
            double fb = f.setVariable("x", xb).evaluate();
            double xn = xa - ((fa * (xa - xb)) / (fa - fb));
            double fn = f.setVariable("x", xn).evaluate();

            TableRow dataRow = new TableRow(this);
            TextView[] dataTextViews = new TextView[7];

            for (int i = 0; i < 7; i++) {
                dataTextViews[i] = new TextView(this);
                dataTextViews[i].setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
                // Set appropriate values based on the iteration
                switch (i) {
                    case 0:
                        dataTextViews[i].setText(String.valueOf(step));
                        break;
                    case 1:
                        dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", xa));
                        break;
                    case 2:
                        dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", xb));
                        break;
                    case 3:
                        dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", xn));
                        break;
                    case 4:
                        dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", fa));
                        break;
                    case 5:
                        dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", fb));
                        break;
                    case 6:
                        dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", fn));
                        break;
                }
                dataRow.addView(dataTextViews[i]);
            }

            iterationTable.addView(dataRow); // Add the data row to the table

            xa = xb;
            xb = xn;

            step++;
            condition = Math.abs(fn) > precision;
        }

        double root = xb;
        String result = String.format("Root Value is: %.4f", root);

        resultText.setText(result);
        iterationTable.setVisibility(View.VISIBLE);
    }




    private double evaluateFunction(double x) {
        f.setVariable("x", x);
        return f.evaluate();
    }
}
