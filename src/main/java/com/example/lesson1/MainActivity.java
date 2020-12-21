package com.example.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lesson1.core.OperationType;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnMC, btnMR, btnMS, btnM_add, btnM_sub, btnBackspace, btnToggleSign, btnClear,
                   btnSqrt, btnInverse, btnAdd, btnMultiply, btnSubtract, btnDivide, btnDot, btnEquals,
                   btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private TextView textBlock;
    private EditText textBox;

    private Double memory;
    private Double buffer, newVal;
    private OperationType selectedOp;

    @Override
    //создание текущего activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        //сброс всех параметров
        reset();
    }

    //инициализация переменных-контроллов и связывание и обработчиком событий
    private void initViews() {
        btnMC = (Button) findViewById(R.id.mc_btn);
        btnMR = (Button) findViewById(R.id.mr_btn);
        btnMS = (Button) findViewById(R.id.ms_btn);
        btnM_add = (Button) findViewById(R.id.m_add_btn);
        btnM_sub = (Button) findViewById(R.id.m_sub_btn);
        btnBackspace = (Button) findViewById(R.id.backspace_btn);
        btnToggleSign = (Button) findViewById(R.id.toggle_sign_btn);
        btnClear = (Button) findViewById(R.id.clear_btn);
        btnSqrt = (Button) findViewById(R.id.sqrt_btn);
        btnInverse = (Button) findViewById(R.id.inv_btn);
        btnAdd = (Button) findViewById(R.id.add_btn);
        btnMultiply = (Button) findViewById(R.id.multiply_btn);
        btnSubtract = (Button) findViewById(R.id.subtract_btn);
        btnDivide = (Button) findViewById(R.id.divide_btn);
        btnDot = (Button) findViewById(R.id.dot_btn);
        btnEquals = (Button) findViewById(R.id.equals_btn);
        btn0 = (Button) findViewById(R.id.zero_btn);
        btn1 = (Button) findViewById(R.id.one_btn);
        btn2 = (Button) findViewById(R.id.two_btn);
        btn3 = (Button) findViewById(R.id.three_btn);
        btn4 = (Button) findViewById(R.id.four_btn);
        btn5 = (Button) findViewById(R.id.five_btn);
        btn6 = (Button) findViewById(R.id.six_btn);
        btn7 = (Button) findViewById(R.id.seven_btn);
        btn8 = (Button) findViewById(R.id.eight_btn);
        btn9 = (Button) findViewById(R.id.nine_btn);
        textBlock = (TextView) findViewById(R.id.output_textBlock);
        textBox = (EditText) findViewById(R.id.input_textBox);

        btnMC.setOnClickListener(this);
        btnMR.setOnClickListener(this);
        btnMS.setOnClickListener(this);
        btnM_add.setOnClickListener(this);
        btnM_sub.setOnClickListener(this);
        btnBackspace.setOnClickListener(this);
        btnToggleSign.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnSqrt.setOnClickListener(this);
        btnInverse.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnEquals.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
    }

    //общий обработчик события нажатия кнопки
    @Override
    public void onClick(View view) {
        try {
            if (view.getId() == R.id.clear_btn) {
                reset();
            } else {
                String text = (textBox.getText().length() > 0) ? textBox.getText().toString() : "";

                if (view.getId() == R.id.backspace_btn) {
                    if (text.length() > 0) {
                        textBox.setText(text.substring(0, text.length() - 1));
                    } else if (text == "-") {
                        textBox.setText(R.string.zero);
                    }
                } else {
                    newVal = Double.parseDouble(text);

                    switch (view.getId()) {
                        case R.id.mc_btn: {
                            memory = 0d;
                            break;
                        }
                        case R.id.mr_btn: {
                            textBox.setText(printDouble(memory));
                            break;
                        }
                        case R.id.ms_btn: {
                            memory = newVal;
                            break;
                        }
                        case R.id.m_add_btn: {
                            memory += newVal;
                            break;
                        }
                        case R.id.m_sub_btn: {
                            memory -= newVal;
                            break;
                        }
                        case R.id.toggle_sign_btn: {
                            textBlock.setText("");

                            newVal = newVal * -1.0;
                            textBox.setText(printDouble(newVal));

                            break;
                        }
                        case R.id.sqrt_btn: {
                            textBlock.setText("sqrt(" + text + ") = ");

                            newVal = Math.sqrt(newVal);
                            textBox.setText(printDouble(newVal));

                            break;
                        }
                        case R.id.inv_btn: {
                            textBlock.setText("1 / " + text + " = ");

                            newVal = 1.0 / this.newVal;
                            textBox.setText(printDouble(newVal));

                            break;
                        }
                        case R.id.add_btn: {
                            textBlock.setText(textBlock.getText() + text + " + ");
                            textBox.setText(R.string.zero);

                            buffer = newVal;
                            selectedOp = OperationType.OP_ADD;
                            break;
                        }
                        case R.id.subtract_btn: {
                            textBlock.setText(textBlock.getText() + text + " - ");
                            textBox.setText(R.string.zero);

                            buffer = newVal;
                            selectedOp = OperationType.OP_SUBTRACT;
                            break;
                        }
                        case R.id.multiply_btn: {
                            textBlock.setText(textBlock.getText() + text + " * ");
                            textBox.setText(R.string.zero);

                            buffer = newVal;
                            selectedOp = OperationType.OP_MULTIPY;
                            break;
                        }
                        case R.id.divide_btn: {
                            textBlock.setText(textBlock.getText() + text + " / ");
                            textBox.setText(R.string.zero);

                            buffer = newVal;
                            selectedOp = OperationType.OP_DIVIDE;
                            break;
                        }
                        case R.id.dot_btn: {
                            if (text.indexOf(".") == -1) {
                                textBox.setText(printDouble(newVal) + ".");
                            }
                            break;
                        }
                        case R.id.equals_btn: {
                            if (selectedOp != OperationType.OP_NONE) {
                                textBlock.setText(textBlock.getText().toString() + text + " = ");
                                switch (selectedOp) {
                                    case OP_ADD:
                                        buffer += newVal;
                                        break;
                                    case OP_SUBTRACT:
                                        buffer -= newVal;
                                        break;
                                    case OP_MULTIPY:
                                        buffer *= newVal;
                                        break;
                                    case OP_DIVIDE:
                                        if (newVal != 0) {
                                            buffer /= newVal;
                                        } else {
                                            textBlock.setText(textBlock.getText().toString() + "Недоп. операция");
                                            buffer = 0d;
                                        }
                                        break;
                                }
                                textBox.setText(printDouble(buffer));
                                selectedOp = OperationType.OP_NONE;
                            }
                            break;
                        }
                        default: {
                            this.newVal = Double.parseDouble(textBox.getText().toString() + ((Button) view).getText().toString());
                            textBox.setText(printDouble(newVal));

                            break;
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            Toast toast = Toast.makeText(getApplicationContext(), "Некорректное значение", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        } catch (Exception ex) {
            Toast toast = Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
    }

    //опциональный вывод числа (без дробной части, или с ней, если есть)
    private String printDouble(double val) {
        if (val == (long) val) {
            return String.format("%d", (long) val);
        } else {
            return String.format("%s", val);
        }
    }

    //сброс всех параметров
    private void reset() {
        memory = 0d;
        buffer = 0d;
        newVal = 0d;
        textBlock.setText("");
        textBox.setText(R.string.zero);
        selectedOp = OperationType.OP_NONE;
    }
}