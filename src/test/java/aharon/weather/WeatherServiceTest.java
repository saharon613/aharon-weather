package aharon.weather;

import com.andrewoid.apikeys.ApiKey;
import json.WeatherResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {
    @Test
    public void weatherNow() {
        // given
        WeatherService service = new WeatherServiceFactory().getService();
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();

        // when
        WeatherResponse response = service.weatherNow("Miami",
                keyString,
                "imperial").blockingGet();

        // then
        assertTrue(response.main.temp > 0);
    }
}