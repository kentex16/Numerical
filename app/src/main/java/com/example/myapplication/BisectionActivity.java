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

public class BisectionActivity extends AppCompatActivity {
    private EditText equationInput;
    private EditText xlInput;
    private EditText xrInput;
    private EditText precisionInput;
    private TextView resultText;
    private TableLayout iterationTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisection);

        equationInput = findViewById(R.id.equation_input);
        xlInput = findViewById(R.id.xl_input);
        xrInput = findViewById(R.id.xr_input);
        precisionInput = findViewById(R.id.precision_input);
        resultText = findViewById(R.id.result_text);


        Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBisection();
            }
        });
    }

    private void calculateBisection() {
        String equation = equationInput.getText().toString();
        double xl = Double.parseDouble(xlInput.getText().toString());
        double xr = Double.parseDouble(xrInput.getText().toString());
        double precision = Double.parseDouble(precisionInput.getText().toString());

        if (f(xl, equation) * f(xr, equation) > 0.0) {
            resultText.setText("Wrong Assumptions\nTry Again with different guess values.");
        } else {
            TableLayout iterationTable = findViewById(R.id.iteration_table);
            iterationTable.removeAllViews(); // Clear any existing rows

            TableRow headerRow = new TableRow(this);
            headerRow.setBackgroundColor(Color.parseColor("#DDDDDD"));

            String[] headers = {"i", "XL", "XR", "XM", "YL", "YM", "YR"};
            for (String header : headers) {
                TextView headerTextView = new TextView(this);
                headerTextView.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
                headerTextView.setText(header);
                headerRow.addView(headerTextView);
            }

            iterationTable.addView(headerRow); // Add the header row to the table

            double x0 = xl;
            double x1 = xr;
            double x2 = 0.0;
            int step = 1;
            boolean condition = true;

            while (condition) {
                x2 = (x0 + x1) / 2;

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
                            dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", x0));
                            break;
                        case 2:
                            dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", x1));
                            break;
                        case 3:
                            dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", x2));
                            break;
                        case 4:
                            dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", f(x0, equation)));
                            break;
                        case 5:
                            dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", f(x2, equation)));
                            break;
                        case 6:
                            dataTextViews[i].setText(String.format(Locale.ENGLISH, "%.4f", f(x1, equation)));
                            break;
                    }
                    dataRow.addView(dataTextViews[i]);
                }

                iterationTable.addView(dataRow); // Add the data row to the table

                if (f(x0, equation) * f(x2, equation) < 0) {
                    x1 = x2;
                } else {
                    x0 = x2;
                }

                step++;
                condition = Math.abs(f(x2, equation)) > precision;
            }

            double root = x2;
            String result = String.format("Root Value is: %.4f", root);
            resultText.setText(result);
        }

        resultText.setVisibility(View.VISIBLE);

    }
    private double f(double x, String equation) {
        return evaluateEquation(x, equation);
    }

    private double evaluateEquation(double x, String equation) {
        try {
            Expression expression = new ExpressionBuilder(equation)
                    .variables("x")
                    .build()
                    .setVariable("x", x);
            return expression.evaluate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }


}