package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class JacobiActivity extends AppCompatActivity{

    private EditText xInput, yInput, zInput, a11Input, a12Input, a13Input,
            a21Input, a22Input, a23Input, a31Input, a32Input, a33Input,
            d1Input, d2Input, d3Input, iterationsInput;
    private TextView equation1label,equation2label,equation3label,initialguess,outputTextView,xlabel,ylabel,zlabel,coefficients,a11label,a12label,a13label,a21label,a22label,a23label,a31label,a32label,a33label,constantlabel,constant1label,constant2label,constant3label,iterationslabel;
    private Button solvebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jacobi_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Initialize the EditText fields
        xInput = findViewById(R.id.x_input);
        yInput = findViewById(R.id.y_input);
        zInput = findViewById(R.id.z_input);
        a11Input = findViewById(R.id.a11_input);
        a12Input = findViewById(R.id.a12_input);
        a13Input = findViewById(R.id.a13_input);
        a21Input = findViewById(R.id.a21_input);
        a22Input = findViewById(R.id.a22_input);
        a23Input = findViewById(R.id.a23_input);
        a31Input = findViewById(R.id.a31_input);
        a32Input = findViewById(R.id.a32_input);
        a33Input = findViewById(R.id.a33_input);
        d1Input = findViewById(R.id.constant1_input);
        d2Input = findViewById(R.id.constant2_input);
        d3Input = findViewById(R.id.constant3_input);
        iterationsInput = findViewById(R.id.iterations_input);
        outputTextView = findViewById (R.id.output_text);
        xlabel =findViewById (R.id.x_label);
        ylabel =findViewById (R.id.y_label);
        zlabel =findViewById (R.id.z_label);
        coefficients = findViewById (R.id.coefficients_label);
        a11label = findViewById (R.id.a11_label);
        a12label = findViewById (R.id.a12_label);
        a13label = findViewById (R.id.a13_label);
        a21label = findViewById (R.id.a21_label);
        a22label = findViewById (R.id.a22_label);
        a23label = findViewById (R.id.a23_label);
        a31label = findViewById (R.id.a31_label);
        a32label = findViewById (R.id.a32_label);
        a33label = findViewById (R.id.a33_label);
        constantlabel =findViewById (R.id.constants_label);
        constant1label =findViewById (R.id.constant1_label);
        constant2label =findViewById (R.id.constant2_label);
        constant3label =findViewById (R.id.constant3_label);
        iterationslabel =findViewById (R.id.iterations_label);
        initialguess =findViewById (R.id.initial_guess_label);
        equation1label = findViewById (R.id.equation1_label);
        equation2label = findViewById (R.id.equation2_label);
        equation3label = findViewById (R.id.equation3_label);
        solvebutton = findViewById (R.id.solve_button);
        // Set onClickListener for the solve button
        Button solveButton = findViewById(R.id.solve_button);
        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveEquations();
            }
        });
    }



    private void solveEquations() {
        // Get initial guess values
        double x = parseInput(xInput.getText().toString());
        double y = parseInput(yInput.getText().toString());
        double z = parseInput(zInput.getText().toString());

        // Get coefficient values
        double a11 = parseInput(a11Input.getText().toString());
        double a12 = parseInput(a12Input.getText().toString());
        double a13 = parseInput(a13Input.getText().toString());
        double a21 = parseInput(a21Input.getText().toString());
        double a22 = parseInput(a22Input.getText().toString());
        double a23 = parseInput(a23Input.getText().toString());
        double a31 = parseInput(a31Input.getText().toString());
        double a32 = parseInput(a32Input.getText().toString());
        double a33 = parseInput(a33Input.getText().toString());

        // Get constant values
        double d1 = parseInput(d1Input.getText().toString());
        double d2 = parseInput(d2Input.getText().toString());
        double d3 = parseInput(d3Input.getText().toString());

        // Get the number of iterations
        int iterations = Integer.parseInt(iterationsInput.getText().toString());

        // Create the table layout
        TableLayout iterationTableLayout = findViewById(R.id.iteration_table_layout);
        iterationTableLayout.removeAllViews(); // Clear any existing rows

        // Create the header row
        TableRow headerRow = new TableRow(this);
        headerRow.setBackgroundColor(Color.parseColor("#DDDDDD"));

        TextView iterationHeader = createTableHeaderTextView("i");
        TextView xHeader = createTableHeaderTextView("X");
        TextView yHeader = createTableHeaderTextView("Y");
        TextView zHeader = createTableHeaderTextView("Z");

        headerRow.addView(iterationHeader);
        headerRow.addView(xHeader);
        headerRow.addView(yHeader);
        headerRow.addView(zHeader);

        iterationTableLayout.addView(headerRow); // Add the header row to the table

        // Solve the equations and add the rows to the table
        for (int i = 0; i < iterations; i++) {
            double newX = (d1 - (a12 * y) - (a13 * z)) / a11;
            double newY = (d2 - (a21 * x) - (a23 * z)) / a22;
            double newZ = (d3 - (a31 * x) - (a32 * y)) / a33;

            TableRow dataRow = new TableRow(this);

            TextView iterationValue = createTableDataTextView(String.valueOf(i + 1));
            TextView xValue = createTableDataTextView(String.format(Locale.ENGLISH, "%.4f", newX));
            TextView yValue = createTableDataTextView(String.format(Locale.ENGLISH, "%.4f", newY));
            TextView zValue = createTableDataTextView(String.format(Locale.ENGLISH, "%.4f", newZ));

            dataRow.addView(iterationValue);
            dataRow.addView(xValue);
            dataRow.addView(yValue);
            dataRow.addView(zValue);

            iterationTableLayout.addView(dataRow); // Add the data row to the table

            x = newX;
            y = newY;
            z = newZ;
        }

        // Show the table layout and hide the input fields and labels
        iterationTableLayout.setVisibility(View.VISIBLE);
        xInput.setVisibility (View.GONE);
        yInput.setVisibility (View.GONE);
        zInput.setVisibility (View.GONE);
        a11Input.setVisibility (View.GONE);
        a12Input.setVisibility (View.GONE);
        a13Input.setVisibility (View.GONE);
        a21Input.setVisibility (View.GONE);
        a22Input.setVisibility (View.GONE);
        a23Input.setVisibility (View.GONE);
        a31Input.setVisibility (View.GONE);
        a32Input.setVisibility (View.GONE);
        a33Input.setVisibility (View.GONE);
        d1Input.setVisibility (View.GONE);
        d2Input.setVisibility (View.GONE);
        d3Input.setVisibility (View.GONE);
        iterationsInput.setVisibility (View.GONE);
        xlabel.setVisibility (View.GONE);
        ylabel.setVisibility (View.GONE);
        zlabel.setVisibility (View.GONE);
        coefficients.setVisibility (View.GONE);
        a11label.setVisibility (View.GONE);
        a12label.setVisibility (View.GONE);
        a13label.setVisibility (View.GONE);
        a21label.setVisibility (View.GONE);
        a22label.setVisibility (View.GONE);
        a23label.setVisibility (View.GONE);
        a31label.setVisibility (View.GONE);
        a32label.setVisibility (View.GONE);
        a33label.setVisibility (View.GONE);
        constantlabel.setVisibility (View.GONE);
        constant1label.setVisibility (View.GONE);
        constant2label.setVisibility (View.GONE);
        constant3label.setVisibility (View.GONE);
        iterationslabel.setVisibility (View.GONE);
        initialguess.setVisibility (View.GONE);
        equation1label.setVisibility (View.GONE);
        equation2label.setVisibility (View.GONE);
        equation3label.setVisibility (View.GONE);
        solvebutton.setVisibility (View.GONE);
        hideInputFields();
    }

    private void hideInputFields() {
        xInput.setVisibility(View.GONE);
        yInput.setVisibility(View.GONE);
        zInput.setVisibility(View.GONE);
        a11Input.setVisibility(View.GONE);
    }

    private TextView createTableHeaderTextView(String text) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        textView.setPadding(16, 16, 16, 16);
        textView.setBackgroundColor(Color.parseColor("#EEEEEE"));
        textView.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        textView.setText(text);
        return textView;
    }

    private TextView createTableDataTextView(String text) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        textView.setPadding(16, 16, 16, 16);
        textView.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        textView.setText(text);
        return textView;
    }



    private double parseInput(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            if (input.startsWith("-")) {
                String numericInput = input.substring(1); // Remove the minus sign temporarily
                return -Double.parseDouble(numericInput); // Convert the remaining string to a negative numeric value
            } else {
                throw e; // Rethrow the exception if it's not a valid negative value
            }
        }
    }
}