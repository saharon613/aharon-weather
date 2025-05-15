package aharon.weather;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import json.WeatherResponse;

import javax.swing.*;

public class WeatherController {

    private final WeatherService service;
    private final JLabel temperatureLabel;
    private String apiKey;

    public WeatherController(WeatherService service, JLabel temperatureLabel, String apiKey) {
        this.service = service;
        this.temperatureLabel = temperatureLabel;
        this.apiKey = apiKey;
    }

    public void display(String location) {
        Disposable disposable = service.weatherNow(location, apiKey, "imperial")
                // tells Rx to request the data on a background Thread
                .subscribeOn(Schedulers.io())
                // tells Rx to handle the response on Swing's main Thread
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                //.observeOn(AndroidSchedulers.mainThread()) // Instead use this on Android only
                .subscribe(
                        this::handleResponse,
                        Throwable::printStackTrace);
    }

    private void handleResponse(WeatherResponse response) {
        double temp = response.main.temp;
        temperatureLabel.setText("Current temp is " + temp + " °F");
    }
}
