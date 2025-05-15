package aharon.weather;

import io.reactivex.rxjava3.core.Single;
import json.WeatherResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("/data/2.5/weather")
    Single<WeatherResponse> weatherNow(
            @Query("q") String query,                  // city name
            @Query("appid") String apikey,            // api key
            @Query("units") String units             // units - imperial in this case
    );
}
