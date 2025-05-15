package aharon.weather;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherServiceFactory {
    public WeatherService getService() {
        // configure Retrofit for the dummyjson website
        Retrofit retrofit = new Retrofit.Builder()      // this is an object, the below is method calls on this object
                .baseUrl("https://api.openweathermap.org/")      // the server we're connecting to
                // Configure Retrofit to use Gson to turn the Json into Objects
                // uses Gson to make Java objects
                .addConverterFactory(GsonConverterFactory.create())
                // Configure Retrofit to use Rx Java - it lets it do it asynchronously
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        return retrofit.create(WeatherService.class);
    }
}
