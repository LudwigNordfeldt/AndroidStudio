package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.Math;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Button returnToMenu = findViewById(R.id.button22);
        Button calcArea = findViewById(R.id.button21);

        EditText biggerBase = findViewById(R.id.edit21);
        EditText smallerBase = findViewById(R.id.edit22);
        EditText angle = findViewById(R.id.edit23);

        TextView areaRes = findViewById(R.id.text24);

        Spinner spinner = findViewById(R.id.spinner41);
        String[] options = new String[]{"Radians", "Degrees"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        spinner.setAdapter(adapter);

        returnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity4.this,MainActivity.class);
                startActivity(intent);
            }
        });

        calcArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a = Double.parseDouble(biggerBase.getText().toString());
                double b = Double.parseDouble(smallerBase.getText().toString());
                double ang = Double.parseDouble(angle.getText().toString());
                areaRes.setText("Area is " + calcTheArea(a,b,ang,spinner));
            }
        });

    }

    public String calcTheArea (double bb, double sb, double ang, Spinner sp) {
        double t = java.lang.Math.tan ( java.lang.Math.toRadians(ang) );
        String m = String.format("%.4f", t);
        Log.i("MainActivity", m);

        if (sp.getSelectedItem().toString() == "Degrees") {
            return String.format("%.2f",java.lang.Math.abs( bb*bb - sb*sb )/4.0 * java.lang.Math.tan ( java.lang.Math.toRadians(ang) ) );
        }

        return String.format("%.2f",java.lang.Math.abs( bb*bb - sb*sb )/4.0 * java.lang.Math.tan ( ang ) );

    }
}