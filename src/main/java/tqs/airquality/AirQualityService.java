package tqs.airquality;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
public class AirQualityService {

    private static final String URL = "http://api.weatherbit.io/v2.0/current/airquality?city=";

    public void saveCity (String city) {
        Cache.addCity(city);
    }

    public List<String> getAllCities() {
        return Cache.getCities();
    }

    public HashMap getStatistics() {
        return Cache.getStatistics();
    }

    public void saveAirQuality(String city, AirQuality airQuality) {
        Cache.saveAirQuality(city, airQuality);
    }

    public AirQualityInfo[] getAirQualityInfo(String city) {
        if (!Cache.isValid(city)){
            Cache.setMiss();
            RestTemplate restTemplate = new RestTemplate();
            String finalUrl = URL + city + "&country=PT&key=10dc22630e8244faa7a7a0bf5f6f2dbe";
            AirQuality airQuality = restTemplate.getForObject(finalUrl, AirQuality.class);
            airQuality.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
            this.saveAirQuality(city, airQuality);
        }
        else
            Cache.setHit();

        return Cache.getAirQuality(city).getData();
    }


}
