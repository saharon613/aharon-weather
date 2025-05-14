package aharon.weather;

import com.andrewoid.apikeys.ApiKey;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import json.WeatherResponse;

import javax.swing.*;

public class WeatherController {

    private final WeatherService service;
    private final JLabel temperatureLabel;

    public WeatherController(WeatherService service, JLabel temperatureLabel) {
        this.service = service;
        this.temperatureLabel = temperatureLabel;
    }

    public void display() {
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        Disposable disposable = service.weatherNow("Miami", keyString, "imperial")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                .subscribe(
                        this::handleResponse,
                        Throwable::printStackTrace
                );
    }

    private void handleResponse(WeatherResponse response) {
        double temp = response.main.temp;
        temperatureLabel.setText("Current temp in Miami: " + temp + " °F");
    }
}
