package org.o7planning.giuakimobile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiManager {
    @GET("data/2.5/forecast")
    Call<WeatherResponse> getWeatherForecast(
            @Query("q") String city,
            @Query("appid") String apiKey
    );
}