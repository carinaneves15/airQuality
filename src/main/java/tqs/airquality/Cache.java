package tqs.airquality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {

    private static List<City> cities = new ArrayList<>();
    private static Map<double[], AirQuality> cache = new HashMap<>();

    public Cache() {
        throw new IllegalStateException("Cache class");
    }

    public static void addCity(City city) {
        cities.add(city);
    }

    public static List<City> getCities() {
        return cities;
    }

    public static AirQuality getAirQuality(double lat, double lon){
        double[] city = {lat, lon};
        return cache.get(city);
    }

    public static void saveAirQuality(double lat, double lon, AirQuality quality) {
        double[] city = {lat, lon};
        cache.put(city, quality);
    }


}
