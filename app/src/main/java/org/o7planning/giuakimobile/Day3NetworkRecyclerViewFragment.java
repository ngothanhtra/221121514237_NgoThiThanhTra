package org.o7planning.giuakimobile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Day3NetworkRecyclerViewFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<Item> newsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day3_network_recyclerview, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsList = new ArrayList<>();
        newsAdapter = new NewsAdapter(getContext(), newsList);
        recyclerView.setAdapter(newsAdapter);

        fetchMovies();

        return view;
    }

    private void fetchMovies() {
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
                if (response.isSuccessful() && response.body() != null && response.body().getResults() != null) {
                    List<Item> movies = response.body().getResults();
                    int count = Math.min(movies.size(), 5); // Lấy tối đa 5 phim
                    newsList.clear();

                    for (int i = 0; i < count; i++) {
                        Item movie = movies.get(i);
                        newsList.add(movie);
                    }

                    newsAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Failed to load movies", Toast.LENGTH_SHORT).show();
                    Log.e("Day3NetworkRecyclerView", "Response unsuccessful or body is null/empty");
                }
            }

            @Override
            public void onFailure(Call<TMDbResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Day3NetworkRecyclerView", "API call failed: " + t.getMessage());
            }
        });
    }
}