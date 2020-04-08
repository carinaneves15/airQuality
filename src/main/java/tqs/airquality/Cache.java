package tqs.airquality;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {

    private static List<City> cities = new ArrayList<>();
    private static Map<City, AirQuality> cache = new HashMap<>();

    public Cache() {
        throw new IllegalStateException("Cache class");
    }

    public static void addCity(City city) {
        cities.add(city);
    }

    public static List<City> getCities() {
        return cities;
    }

    public static void saveAirQuality(City city, AirQuality airQuality) {
        cache.put(city, airQuality);
    }

    public static AirQuality getAirQuality(City city) {
        return cache.get(city);
    }

    // Cidade é válida se estiver na cache há menos de 20min
    public static boolean validCity(City city) {
        long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
        return (cache.containsKey(city) && currentTime - cache.get(city).getTimestamp() < 1200000);
    }
}
