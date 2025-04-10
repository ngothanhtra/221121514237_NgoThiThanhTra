package org.o7planning.giuakimobile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface APIManager {
    @GET("/3/discover/movie")
    Call<TMDbResponse> getMovies(
            @Header("Authorization") String authorization,
            @Query("language") String language,
            @Query("region") String region,
            @Query("release_date.gte") String releaseDateGte,
            @Query("release_date.lte") String releaseDateLte,
            @Query("with_release_type") String withReleaseType

    );
}