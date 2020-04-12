package tqs.airquality;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * CasheTest.java
 *
 * @author Carina Neves
 */

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
        Set<String> citiesAirInfoIn = new HashSet<String>();
        citiesAirInfoIn.add("porto");

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

        assertThat(expected).isEqualTo(found);
    }

    @Test
    private void testGetStatisticsWithoutStatistics() {
        HashMap<String, String> expected = new HashMap<>();
        List<String> citiesAirInfoIn = new ArrayList<>();
        expected.put("miss", "0");
        expected.put("hit", "1");
        expected.put("citiesAirInfoIn", citiesAirInfoIn.toString());
        HashMap<String, String> found = cache.getStatistics();

        assertThat(expected).isEqualTo(found);
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
        assertThat(cities)
                .contains(city0, city1)
                .doesNotContain("TorresNovas");
    }

    @Test
    private void testGetCities() {
        List<String> cities = cache.getCities();
        assertEquals(20, cities.size());
    }

    @Test
    public void testIsValidWithValidCity() {
        boolean res = cache.isValid("porto");
        assertThat(res).isTrue();
    }

    @Test
    public void testIsValidWithInvalidCity() {
        boolean res0 = cache.isValid("jijij");
        assertThat(res0).isFalse();

        //erro no timestamp
        AirQuality air = new AirQuality();
        cache.saveAirQuality("Évora", air);
        boolean res1 = cache.isValid("Évora");
        assertThat(res1).isFalse();
    }

    @Test
    public void testSaveAirQuality() {
        String city = "Braga";
        AirQuality air = new AirQuality();
        cache.saveAirQuality(city, air);
        Map<String, AirQuality> inCache = cache.getCache();
        assertThat(inCache)
                .containsKey("braga")
                .containsValue(air);
    }

    @Test
    public void testGetAirQuality() {
        String city = "Tomar";
        AirQualityInfo[] info = {new AirQualityInfo("28", "61", "327.11", "3.8", "2.7")};
        AirQuality air = new AirQuality(info, new Timestamp(System.currentTimeMillis()).getTime());
        cache.saveAirQuality(city, air);
        AirQuality returned = cache.getAirQuality("Tomar");
        assertThat(returned.toString()).isEqualTo(air.toString());
        assertThat(returned.getData().toString()).isEqualTo(info.toString());
    }

}