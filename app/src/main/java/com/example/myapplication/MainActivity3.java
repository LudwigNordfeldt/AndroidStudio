package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity3 extends AppCompatActivity {

    public interface WeatherOneDayApi {
        @GET("/data/2.5/weather")
        Call<Example> getWeatherByCityName(@Query("q") String city, @Query("appid") String appID);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditText edit31 = findViewById(R.id.edit31);
        EditText edit32 = findViewById(R.id.edit32);
        TextView text30 = findViewById(R.id.text30);

        Button button30 = findViewById(R.id.button30);
        button30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")//базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create())//конвертер
                .build();
        WeatherOneDayApi weatherOneDayApi;
        weatherOneDayApi=retrofit.create(WeatherOneDayApi.class);//создали объект, с его помощью будем отправлять запросы
        weatherOneDayApi.getWeatherByCityName(getIntent().getExtras().get("Key").toString(),"3d822b9dce4e57f12b9b3400d480a358").
                enqueue(new Callback<Example>() {//aсинхронный вызов (для синхронного был бы метод execute() )
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        if (response.isSuccessful()) {
                            Log.i("MainActivity", response.body().getWind().getSpeed().toString());
                            text30.setText(getIntent().getExtras().get("Key").toString());
                            Double temp = response.body().getMain().getTemp() - 273.0;
                            edit31.setText(String.format("%.2f", temp) + "°C");
                            edit32.setText(response.body().getWind().getSpeed().toString());
                            Log.i("MainActivity", "OK");
                        } else  {
                            Log.i("MainActivity", getIntent().getExtras().get("Key").toString());
                            Log.i("MainActivity", "no response");
                            if (getIntent().getExtras().get("Key").toString().isEmpty()) {
                                text30.setText("Query is empty");
                            }
                            else {
                                text30.setText(getIntent().getExtras().get("Key").toString() + " is not a valid city.");
                            }

                        }
                    }
                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        text30.setText("Error! Try later.");
                        Log.i("MainActivity","Failure "+t);
                    }
                });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.i("MainActivity", getIntent().getExtras().get("Key").toString());
        }
    }
}