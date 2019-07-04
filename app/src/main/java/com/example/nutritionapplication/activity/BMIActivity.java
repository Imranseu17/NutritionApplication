package com.example.nutritionapplication.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.nutritionapplication.R;
import com.example.nutritionapplication.databinding.ActivityDeshboardBindingImpl;
import com.example.nutritionapplication.dialog.CustomAlertDialog;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BMIActivity extends AppCompatActivity {

    ActivityDeshboardBindingImpl deshboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deshboardBinding = DataBindingUtil.
                setContentView(BMIActivity.this,
                        R.layout.activity_deshboard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("BMI CALCULATE");

        deshboardBinding.calculate.
                setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!emptyValidation()){
                    double bmi = bmiCalculate();
                    String bMICategory = printBMICategory(bmi);
                    CustomAlertDialog.showError
                            (BMIActivity.this,
                                    "You are BMI is: "+bmi+"\n"
                                            +bMICategory);
                    deshboardBinding.weight.getEditText().setText("");
                    deshboardBinding.height.getEditText().setText("");
                }
                else{
                    Toast.makeText(BMIActivity.this, "Empty Fields",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
              finish();
              return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public double  bmiCalculate(){
        String weightString =deshboardBinding.weight.
                getEditText().getText().toString().trim();
       String heightfeetString = deshboardBinding.height.
                getEditText().getText().toString().trim();

       int weight = Integer.parseInt(weightString);
       double heightfeet = Double.parseDouble(heightfeetString);

        double heightmeter = ((heightfeet*12)*2.54)/100;

        double bmi = weight/(heightmeter*heightmeter);

        return bmi;

    }

    private static String printBMICategory(double bmi) {
        if(bmi < 18.5) {
                 return "Your result suggests You are a underweight";
        }else if (bmi < 25) {
            return "Your result suggests You are a normal weight";
        }else if (bmi < 30) {
            return "Your result suggests You are a overweight";
        }else {
            return  "Your result suggests You are a obese";
        }
    }

    private boolean emptyValidation() {
        if (TextUtils.isEmpty(deshboardBinding.weight.getEditText().getText().toString().trim()) ||
                TextUtils.isEmpty(deshboardBinding.height.getEditText().getText().toString().trim())) {
            return true;
        }else {
            return false;
        }
    }
}
