package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private String operator;
    private double firstOperand = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        int[] buttons = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonPlus, R.id.buttonMinus,
                R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonClear, R.id.buttonEquals
        };

        for (int id : buttons) {
            findViewById(id).setOnClickListener(this::onButtonClick);
        }
    }

    private void onButtonClick(View view) {
        Button button = (Button) view;
        switch (button.getText().toString()) {
            case "C":
                clear();
                break;
            case "=":
                calculate();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                setOperator(button.getText().toString());
                break;
            default:
                display.append(button.getText());
                break;
        }
    }

    private void clear() {
        display.setText("");
        operator = null;
        firstOperand = 0.0;
    }

    private void setOperator(String op) {
        if (!display.getText().toString().isEmpty()) {
            firstOperand = Double.parseDouble(display.getText().toString());
            operator = op;
            display.setText("");
        }
    }

    private void calculate() {
        if (operator != null && !display.getText().toString().isEmpty()) {
            double secondOperand = Double.parseDouble(display.getText().toString());
            double result;
            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    result = (secondOperand != 0.0) ? firstOperand / secondOperand : Double.NaN;
                    break;
                default:
                    result = 0.0;
                    break;
            }

            if (Double.isNaN(result)) {
                display.setText("Ошибка");
            } else {
                display.setText(String.valueOf(result));
            }
            operator = null;
        }
    }
}
