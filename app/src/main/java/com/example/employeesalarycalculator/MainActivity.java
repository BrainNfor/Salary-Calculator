package com.example.employeesalarycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText textName, textSalary, textTax, textNetSalary;
    private Button btn_ok, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialise();
        calculate();


    }

    private void initialise() {
        textName = findViewById(R.id.textName);
        textSalary = findViewById(R.id.textSalary);
        textTax = findViewById(R.id.textTax);
        textNetSalary = findViewById(R.id.textNetSalary);

        textNetSalary.setEnabled(false);
        textNetSalary.setCursorVisible(false);
        textNetSalary.setKeyListener(null);

        textTax.setEnabled(false);
        textTax.setCursorVisible(false);
        textTax.setKeyListener(null);

        btn_ok = findViewById(R.id.btn_ok);
        btn_cancel = findViewById(R.id.btn_cancel);
    }

    private void calculate() {
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isParsable = true;
                double salary = 0;
                String name = String.valueOf(textName.getText());
                try {
                    salary = Double.parseDouble(String.valueOf(textSalary.getText()));
                } catch (Exception e) {
                    isParsable = false;
                }
                double netSalary;
                double tax;

                if (isParsable) {

                    if (salary >= 500000) {
                        tax = (salary * 3) / 100;
                        netSalary = (salary - tax);
                        textTax.setText(String.format(Locale.CHINA, "%.2f", tax));
                        textNetSalary.setText(String.format(Locale.getDefault(), "%.2f", netSalary));
                    }
                    if (salary < 500000 && salary >= 100000) {
                        tax = (salary * 1) / 100;
                        netSalary = (salary - tax);
                        textTax.setText(String.format(Locale.CHINA, "%.2f", tax));
                        textNetSalary.setText(String.format(Locale.getDefault(), "%.2f", netSalary));
                    }
                    if (salary < 100000) {
                        tax = (salary * 0.1) / 100;
                        netSalary = (salary - tax);
                        textTax.setText(String.format(Locale.CHINA, "%.2f", tax));
                        textNetSalary.setText(String.format(Locale.getDefault(), "%.2f", netSalary));
                    }
                } else {
                       textSalary.setError("Enter correct number");
                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textName.setText("");
                textTax.setText("");
                textNetSalary.setText("");
                textSalary.setText("");
            }
        });
    }


}