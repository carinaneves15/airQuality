package tqs.airquality;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {

    private static List<String> cities = new ArrayList<>();
    private static Map<String, AirQuality> cache = new HashMap<>();
    private static int miss = 0;
    private static int hit = 0;
    private static List<String> citiesAirInfoIn = new ArrayList<>();

    public Cache() {
        throw new IllegalStateException("Cache class");
    }

    public static void addCity(String city) {
        cities.add(city);
    }

    public static List<String> getCities() {
        hit++;
        return cities;
    }

    public static HashMap getStatistics() {
        hit++;
        HashMap<String, String> statistics = new HashMap<>();
        statistics.put("miss", String.valueOf(miss));
        statistics.put("hit", String.valueOf(hit));
        statistics.put("citiesAirInfoIn", citiesAirInfoIn.toString());
        return statistics;
    }

    public static void setMiss() {
        miss++;
    }

    public static void setHit() {
        hit++;
    }

    // Válido se a cidade estiver na cache há menos de 20min
    public static boolean isValid(String city) {
        long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
        return (cache.containsKey(city) && currentTime - cache.get(city).getTimestamp() < 1200000);
    }

    public static void saveAirQuality(String city, AirQuality airQuality) {
        citiesAirInfoIn.add(city);
        cache.put(city, airQuality);
    }

    public static AirQuality getAirQuality(String city) {
        return cache.get(city);
    }


}
