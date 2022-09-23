package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button1);//описали переменную и связали с кнопкой
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("key1","Hello");
                EditText edit1=findViewById(R.id.edit1);
                intent.putExtra("key2",edit1.getText());
                startActivity(intent);
            }

        });

        Button button2=findViewById(R.id.button11);//описали переменную и связали с кнопкой
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("https://api.openweathermap.org")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();
                MessageAPI messageAPI=retrofit.create(MessageAPI.class);

                Call<String> message=messageAPI.message();
                message.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i("MainActivity","" + response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.i("MainActivity","Failure" + t);
                    }
                });
            }

        });

        EditText edit11 = findViewById(R.id.edit11);

        Button buttonTrapezoid = findViewById(R.id.button10);
        buttonTrapezoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity4.class);
                startActivity(intent);

            }
        });

        Button button3pojo = findViewById(R.id.button12);
        button3pojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity3.class);
                intent.putExtra("Key",edit11.getText().toString());
                startActivity(intent);

            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            EditText editText=findViewById(R.id.edit1);
            editText.setText(getIntent().getExtras().get("key22").toString());
            Log.i("MainActivity",getIntent().getExtras().get("key11").toString());
        }


    }

    public interface MessageAPI{
        @GET("https://api.openweathermap.org/data/2.5/weather?q=London&appid=3d822b9dce4e57f12b9b3400d480a358")
        Call<String> message();
    }




}