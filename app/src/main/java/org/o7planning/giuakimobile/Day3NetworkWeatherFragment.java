package org.o7planning.giuakimobile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Day3NetworkWeatherFragment extends Fragment {

    private TextView cityTextView, descriptionTextView, currentTempTextView;
    private RecyclerView recyclerView;
    private HourAdapter hourAdapter;
    private List<Weather> weatherList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day3_network_weather, container, false);

        // Ánh xạ các thành phần giao diện
        cityTextView = view.findViewById(R.id.city_text_view);
        descriptionTextView = view.findViewById(R.id.description_text_view);
        currentTempTextView = view.findViewById(R.id.current_temp_text_view);
        recyclerView = view.findViewById(R.id.recycler_view);

        // Cấu hình RecyclerView để lướt ngang
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        weatherList = new ArrayList<>();
        hourAdapter = new HourAdapter(getContext(), weatherList);
        recyclerView.setAdapter(hourAdapter);

        fetchWeatherData();

        return view;
    }

    private void fetchWeatherData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApiManager apiManager = retrofit.create(WeatherApiManager.class);
        Call<WeatherResponse> call = apiManager.getWeatherForecast("Hanoi", "5e3f873d869e4b9b9e29730f0bcc803d");

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().getList().isEmpty()) {
                    weatherList.clear();
                    weatherList.addAll(response.body().getList());
                    hourAdapter.notifyDataSetChanged();


                    Weather currentWeather = weatherList.get(0);
                    cityTextView.setText("Ha noi");
                    double currentTemp = currentWeather.getMain().getTemp() - 273.15;
                    currentTempTextView.setText(String.format("%.0f°", currentTemp));
                } else {
                    Toast.makeText(getContext(), "Failed to load weather data: " + response.message(), Toast.LENGTH_LONG).show();
                    Log.e("Day3NetworkWeather", "Response unsuccessful or body is null: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Day3NetworkWeather", "API call failed: " + t.getMessage());
            }
        });
    }
}