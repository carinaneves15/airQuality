package tqs.airquality;

import java.sql.Timestamp;
import java.util.*;

public class Cache {

    private static List<String> cities = new ArrayList<>();
    private static Map<String, AirQuality> cache = new HashMap<>();
    private static int miss = 0;
    private static int hit = 0;

    public Cache() {

    }

    public void addCity(String city) {
        cities.add(city);
    }

    public List<String> getCities() {
        hit++;
        return cities;
    }

    public HashMap getStatistics() {
        hit++;
        HashMap<String, String> statistics = new HashMap<>();
        statistics.put("miss", String.valueOf(miss));
        statistics.put("hit", String.valueOf(hit));
        statistics.put("citiesAirInfoIn", cache.keySet().toString());
        return statistics;
    }

    public void setMiss() {
        miss++;
    }

    public void setHit() {
        hit++;
    }

    // Válido se a cidade estiver na cache há menos de 20min
    public boolean isValid(String city) {
        long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
        return (cache.containsKey(city) && currentTime - cache.get(city).getTimestamp() < 1200000);
    }

    public void saveAirQuality(String city, AirQuality airQuality) {
        cache.put(city.toLowerCase(), airQuality);
    }

    public AirQuality getAirQuality(String city) {
        return cache.get(city.toLowerCase());
    }

    //para testes
    public Map<String, AirQuality> getCache() {
        return cache;
    }

    public Set<String> getCitiesAirInfoIn() {
        return cache.keySet();
    }
}
