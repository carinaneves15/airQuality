package tqs.airquality;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;

@Service
public class AirQualityService {

    private static final String URL = "http://api.weatherbit.io/v2.0/current/airquality?city=";

    public void save (City city, AirQuality airQuality) {
      Cache.saveAirQuality(city, airQuality);
    }

    public AirQualityInfo[] getAirQualityInfo(City city) {
        if (!Cache.validCity(city)){
            RestTemplate restTemplate = new RestTemplate();
            String finalUrl = URL + city.getName() + "&country=PT&key=10dc22630e8244faa7a7a0bf5f6f2dbe";
            AirQuality airQuality = restTemplate.getForObject(finalUrl, AirQuality.class);
            airQuality.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
            this.save(city, airQuality);
        }
        return Cache.getAirQuality(city).getData();
    }
}
