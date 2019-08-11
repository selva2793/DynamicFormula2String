package com.androidselva.dynamicformula2string;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.androidselva.dynamicformula2string.Utils.FormulaSplitter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    TextView outPut;
    Button btCalculate;
    List<String> argumentValues = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = (EditText) findViewById(R.id.f1);
        e2 = (EditText) findViewById(R.id.f2);
        e3 = (EditText) findViewById(R.id.f3);
        e4 = (EditText) findViewById(R.id.f4);
        btCalculate = (Button) findViewById(R.id.btn);
        outPut = (TextView) findViewById(R.id.tv_result);

        btCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                argumentValues = new ArrayList<>();
                String aValue = "";
                String bValue = "";
                String splitterText = "";
                String forMula = "";

                if (e1.getText().toString().trim().length() == 0) {
                    e1.setError("Please enter the a value");
                    e1.requestFocus();
                }

                else if (e2.getText().toString().trim().length() == 0) {
                    e2.setError("Please enter the b value");
                    e2.requestFocus();
                }

                else if (e3.getText().toString().trim().length() == 0) {
                    e3.setError("Please enter the splitter text");
                    e3.requestFocus();
                }
                else if (e4.getText().toString().trim().length() == 0) {
                    e4.setError("Please enter the customised formula");
                    e4.requestFocus();
                }

                aValue = e1.getText().toString();
                bValue = e2.getText().toString();
                splitterText = e3.getText().toString();
                forMula = e4.getText().toString();

                argumentValues.add(aValue);
                argumentValues.add(bValue);
                Log.d("check", "onCreate: " + aValue + " " + bValue);
                if (aValue != "" && bValue != "" && splitterText != "" && forMula != "") {
                    FormulaSplitter formulaSplitter = new FormulaSplitter();
                    String result = formulaSplitter.main(forMula, splitterText, argumentValues, getApplicationContext());
                    outPut.setText("The Output is : " + result);
                }
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btCalculate.getWindowToken(), 0);

            }
        });


    }


}
