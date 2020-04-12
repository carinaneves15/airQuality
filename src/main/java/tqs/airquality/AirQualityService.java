package tqs.airquality;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

/**
 * AirQualityService.java
 *
 * @author Carina Neves
 */

@Service
public class AirQualityService {

    Cache cache = new Cache();

    private static final String URL = "http://api.weatherbit.io/v2.0/current/airquality?city=";

    /**
     * Save citu in cache
     * @param city
     */
    public void saveCity(String city) {
        cache.addCity(city);
    }

    /**
     * Get cities available on the website
     * @return
     */
    public List<String> getCities() {
        return cache.getCities();
    }

    /**
     * Get current statistics
     * @return
     */
    public HashMap<String, String> getStatistics() {
        return cache.getStatistics();
    }

    /**
     * Save the quality of a city's air
     * @param city
     * @param airQuality
     */
    public void saveAirQuality(String city, AirQuality airQuality) {
        cache.saveAirQuality(city, airQuality);
    }

    /**
     * Get the quality of a city's air
     * @param city
     * @return
     */
    public AirQualityInfo[] getAirQuality(String city) {
        // if the city is not valid we will go to the external api
        if (!cache.isValid(city.toLowerCase())) {
            RestTemplate restTemplate = new RestTemplate();
            String finalUrl = URL + city + "&country=PT&key=10dc22630e8244faa7a7a0bf5f6f2dbe";
            AirQuality airQuality = restTemplate.getForObject(finalUrl, AirQuality.class);
            // if api return valid air quality then save it
            if (airQuality != null) {
                cache.setMiss();
                airQuality.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
                this.saveAirQuality(city, airQuality);
                return cache.getAirQuality(city).getData();
            }
            // if api does not return valid air quality then
            // we create a null object - preventing whitelabels
            else {
                AirQualityInfo nullInfo = new AirQualityInfo();
                AirQualityInfo[] toReturn = {nullInfo};
                return toReturn;
            }
        }
        // valid value just do get
        else {
            cache.setHit();
            return cache.getAirQuality(city).getData();
        }

    }

}
