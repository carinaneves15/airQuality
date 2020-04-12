package tqs.airquality;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
public class AirQualityService {

    Cache cache = new Cache();

    private static final String URL = "http://api.weatherbit.io/v2.0/current/airquality?city=";

    public void saveCity (String city) {
        cache.addCity(city);
    }

    public List<String> getCities() {
        return cache.getCities();
    }

    public HashMap<String, String> getStatistics() {
        return cache.getStatistics();
    }

    public void saveAirQuality(String city, AirQuality airQuality) {
        cache.saveAirQuality(city, airQuality);
    }

    public AirQualityInfo[] getAirQuality(String city) {
        if (!cache.isValid(city.toLowerCase())){
            RestTemplate restTemplate = new RestTemplate();
            String finalUrl = URL + city + "&country=PT&key=10dc22630e8244faa7a7a0bf5f6f2dbe";
            AirQuality airQuality = restTemplate.getForObject(finalUrl, AirQuality.class);
            if (airQuality != null){
                cache.setMiss();
                airQuality.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
                this.saveAirQuality(city, airQuality);
                return cache.getAirQuality(city).getData();
            }
            else {
                AirQualityInfo nullInfo = new AirQualityInfo();
                AirQualityInfo[] toReturn = {nullInfo};
                return toReturn;
            }
        }
        else{
            cache.setHit();
            return cache.getAirQuality(city).getData();
        }

    }

}
