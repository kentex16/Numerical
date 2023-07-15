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

public class TangentActivity extends AppCompatActivity {
    private EditText equationInput;
    private EditText x0Input;
    private EditText precisionInput;
    private TextView resultText;
    private TableLayout iterationTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tangent);

        equationInput = findViewById(R.id.equation_input);
        x0Input = findViewById(R.id.x0_input);
        precisionInput = findViewById(R.id.precision_input);
        resultText = findViewById(R.id.result_text);
        iterationTable =findViewById (R.id.iteration_table);

        Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTangent();
            }
        });
    }

    private void calculateTangent() {
        String equation = equationInput.getText().toString();
        double x0 = Double.parseDouble(x0Input.getText().toString());
        double precision = Double.parseDouble(precisionInput.getText().toString());

        Expression f = new ExpressionBuilder(equation)
                .variable("x")
                .build();

        int step = 1;

        iterationTable.removeAllViews(); // Clear any existing rows

        TableRow headerRow = new TableRow(this);
        headerRow.setBackgroundColor(Color.parseColor("#DDDDDD"));

        String[] headers = {"i", "Xo", "Xn", "Yo", "Yn"};
        for (String header : headers) {
            TextView headerTextView = new TextView(this);
            headerTextView.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            headerTextView.setText(header);
            headerRow.addView(headerTextView);
        }

        iterationTable.addView(headerRow); // Add the header row to the table

        boolean condition = true;

        while (condition) {
            f.setVariable("x", x0);
            double fx0 = f.evaluate();
            double slope = differentiate(f, x0);
            double x2 = x0 - (fx0 / slope);
            double fx2 = f.setVariable("x", x2).evaluate();

            TableRow dataRow = new TableRow(this);
            TextView[] dataTextViews = new TextView[5];

            for (int i = 0; i < 5; i++) {
                dataTextViews[i] = new TextView(this);
                dataTextViews[i].setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
                // Set appropriate values based on the iteration
                switch (i) {
                    case 0:
                        dataTextViews[i].setText(String.valueOf(step));
                        break;
                    case 1:
                        dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", x0));
                        break;
                    case 2:
                        dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", x2));
                        break;
                    case 3:
                        dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", fx0));
                        break;
                    case 4:
                        dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", fx2));
                        break;
                }
                dataRow.addView(dataTextViews[i]);
            }

            iterationTable.addView(dataRow); // Add the data row to the table

            x0 = x2;
            step++;
            condition = Math.abs(fx2) > precision;
        }

        double root = x0;
        String result = String.format("Root Value is: %.4f", root);

        resultText.setText(result);
        iterationTable.setVisibility(View.VISIBLE);
    }


    private double differentiate(Expression f, double x) {
        double h = 1e-8; // small value for approximating the derivative
        double xPlusH = x + h;
        double xMinusH = x - h;
        double fPlusH = f.setVariable("x", xPlusH).evaluate();
        double fMinusH = f.setVariable("x", xMinusH).evaluate();
        return (fPlusH - fMinusH) / (2 * h);
    }
}
