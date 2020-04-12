package tqs.airquality;

import java.sql.Timestamp;
import java.util.*;

/**
 * Cache.java
 * Purpose: Save the most recent results and produce statistics
 *
 * @author Carina Neves
 */
public class Cache {
    private static List<String> cities = new ArrayList<>();
    private static Map<String, AirQuality> cache = new HashMap<>();
    private static int miss = 0;
    private static int hit = 0;

    public Cache() {

    }

    /**
     * Called to saved the cities that will be available on the website
     *
     * @param city
     */
    public void addCity(String city) {
        cities.add(city);
    }

    /**
     * @return All cities available on the website
     */
    public List<String> getCities() {
        hit++;
        return cities;
    }

    /**
     * @return statistics about operation
     * (countof requests, hits/misses and the saved cities)
     */
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

    /**
     * Checks if a city is valid
     * That is, it's in the cache and if the lifetime has not passed, 20m
     *
     * @param city
     * @return
     */
    public boolean isValid(String city) {
        long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
        return (cache.containsKey(city) && currentTime - cache.get(city).getTimestamp() < 1200000);
    }

    /**
     * Saves the city and its air quality
     *
     * @param city City to save
     * @param airQuality Respective air quality
     */
    public void saveAirQuality(String city, AirQuality airQuality) {
        cache.put(city.toLowerCase(), airQuality);
    }

    /**
     * @param city Where air quality is desired
     * @return Respective air quality
     */
    public AirQuality getAirQuality(String city) {
        return cache.get(city.toLowerCase());
    }

    /**
     * Used in tests to check which cities are saved in cache
     * @return Map with saved cities as well as their air qualities
     */
    public Map<String, AirQuality> getCache() {
        return cache;
    }

}