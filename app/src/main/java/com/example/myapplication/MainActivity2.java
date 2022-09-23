package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button=findViewById(R.id.button2);//описали переменную и связали с кнопкой
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                intent.putExtra("key11","Hello");
                EditText edit1=findViewById(R.id.edit2);
                intent.putExtra("key22",edit1.getText());
                startActivity(intent);
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            EditText editText = findViewById(R.id.edit2);
            editText.setText(getIntent().getExtras().get("key2").toString());
            Log.i("MainActivity2", getIntent().getExtras().get("key1").toString());
        }
    }
}