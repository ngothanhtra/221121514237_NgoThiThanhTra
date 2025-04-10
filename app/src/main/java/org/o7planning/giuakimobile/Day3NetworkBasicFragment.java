package org.o7planning.giuakimobile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Day3NetworkBasicFragment extends Fragment {

    private TextView titleTextView, timeTextView, descriptionTextView;
    private ImageView posterImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day3_network_basic, container, false);

        titleTextView = view.findViewById(R.id.movie_title);
        timeTextView = view.findViewById(R.id.movie_time);
        descriptionTextView = view.findViewById(R.id.movie_description);
        posterImageView = view.findViewById(R.id.movie_poster);

        fetchMovie();

        return view;
    }

    private void fetchMovie() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIManager apiManager = retrofit.create(APIManager.class);
        Call<TMDbResponse> call = apiManager.getMovies(
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzN2QzOGFjOTVkZjVhZWNkOWRlNjgwYzRmZGYxZjU2OSIsIm5iZiI6MTczOTE3ODc0OC41MjgsInN1YiI6IjY3YTljMmZjYTRjMDExOGY5ZTkzN2NjOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.F5bPbmsa7Z5S78AQGlVjRCeGSDW4rHmTr4rE4WEqSiY",
                "de-DE",
                "DE",
                "2016-11-16",
                "2016-12-02",
                "2|3"
        );

        call.enqueue(new Callback<TMDbResponse>() {
            @Override
            public void onResponse(Call<TMDbResponse> call, Response<TMDbResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getResults() != null && !response.body().getResults().isEmpty()) {
                    Item movie = response.body().getResults().get(0); // Lấy phim đầu tiên

                    Log.d("Day3NetworkBasic", "Title: " + movie.getTitle());
                    Log.d("Day3NetworkBasic", "Poster Path: " + movie.getPoster_path());
                    Log.d("Day3NetworkBasic", "Overview: " + movie.getOverview());

                    // Hiển thị tiêu đề
                    if (movie.getTitle() != null && !movie.getTitle().isEmpty()) {
                        titleTextView.setText(movie.getTitle());
                    } else {
                        titleTextView.setText("No Title Available");
                    }


                    if (movie.getRelease_date() != null && !movie.getRelease_date().isEmpty()) {
                        timeTextView.setText(movie.getRelease_date());
                    } else {
                        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM/yyyy - HH:mm", Locale.getDefault());
                        String currentTime = sdf.format(new Date());
                        timeTextView.setText(currentTime);
                    }


                    if (movie.getOverview() != null && !movie.getOverview().isEmpty()) {
                        descriptionTextView.setText(movie.getOverview());
                    } else {
                        descriptionTextView.setText("No Description Available");
                    }

                    // Hiển thị hình ảnh poster
                    if (movie.getPoster_path() != null && !movie.getPoster_path().isEmpty()) {
                        String posterUrl = "https://image.tmdb.org/t/p/w500" + movie.getPoster_path();
                        Log.d("Day3NetworkBasic", "Full Poster URL: " + posterUrl);
                        Glide.with(getContext())
                                .load(posterUrl)
                                .placeholder(android.R.drawable.ic_menu_gallery)
                                .error(android.R.drawable.ic_menu_report_image)
                                .into(posterImageView);
                    } else {
                        posterImageView.setImageResource(android.R.drawable.ic_menu_report_image);
                        Log.e("Day3NetworkBasic", "Poster path is null or empty");
                    }
                } else {
                    Toast.makeText(getContext(), "Failed to load movie", Toast.LENGTH_SHORT).show();
                    Log.e("Day3NetworkBasic", "Response unsuccessful or body is null/empty");
                }
            }

            @Override
            public void onFailure(Call<TMDbResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Day3NetworkBasic", "API call failed: " + t.getMessage());
            }
        });
    }
}