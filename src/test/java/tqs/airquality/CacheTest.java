package tqs.airquality;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CacheTest {
    protected Cache cache = new Cache();

    @Test
    public void testGetStatistics_thenAddStatistics_theGetStatistics(){
        testGetStatisticsWithoutStatistics();
        testAddStatisticsThenGet();
    }

    @Test
    private void testAddStatisticsThenGet() {
        int hit = 1;
        int miss = 0;
        List<String> citiesAirInfoIn = Arrays.asList("Porto");
        AirQuality air = new AirQuality();
        air.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());

        cache.setMiss();
        miss++;
        cache.setHit();
        hit++;
        cache.saveAirQuality("Porto", air);
        HashMap<String, String> found = cache.getStatistics();
        hit++;

        HashMap<String, String> expected = new HashMap<>();
        expected.put("miss", String.valueOf(miss));
        expected.put("hit", String.valueOf(hit));
        expected.put("citiesAirInfoIn", citiesAirInfoIn.toString());
        assertEquals(expected, found);
    }

    @Test
    private void testGetStatisticsWithoutStatistics() {
        HashMap<String, String> expected = new HashMap<>();
        List<String> citiesAirInfoIn = new ArrayList<>();
        expected.put("miss", "0");
        expected.put("hit", "1");
        expected.put("citiesAirInfoIn", citiesAirInfoIn.toString());
        HashMap<String, String> found = cache.getStatistics();
        assertEquals(expected, found);
    }

    @Test
    public void testGetCities_thenAddCities_thenGetCities(){
        testGetCities();
        testAddCitiesThenGet();
    }

    @Test
    private void testAddCitiesThenGet() {
        String city0 = "Aveiro";
        String city1 = "Porto";
        cache.addCity(city0);
        cache.addCity(city1);
        List<String> cities = cache.getCities();
        assertEquals(22, cities.size());
        assertTrue(cities.contains(city0));
        assertTrue(cities.contains(city1));
        assertFalse(cities.contains("TorresNovas"));
    }

    @Test
    private void testGetCities() {
        List<String> cities = cache.getCities();
        assertEquals(20, cities.size());
    }

    @Test
    public void testIsValidWithValidCity() {
        boolean res = cache.isValid("Porto");
        assertTrue(res);
    }

    @Test
    public void testIsValidWithInvalidCity() {
        boolean res0 = cache.isValid("Setubal");
        AirQuality air = new AirQuality();
        cache.saveAirQuality("Évora", air);
        boolean res1 = cache.isValid("Évora");
        assertFalse(res0);
        assertFalse(res1);
    }

    @Test
    public void testSaveAirQuality() {
        String city = "Braga";
        AirQuality air = new AirQuality();
        cache.saveAirQuality(city, air);
        Map<String, AirQuality> inCache = cache.getCache();
        assertTrue(inCache.containsKey("Braga"));
        assertEquals(air, inCache.get("Braga"));
    }

    // ver se é preciso o testGetAirQuality

}