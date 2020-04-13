package tqs.airquality;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tqs.airquality.AirQuality;
import tqs.airquality.AirQualityInfo;
import tqs.airquality.AirQualityService;
import tqs.airquality.Cache;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * AirQualityServiceTest.java
 *
 * @author Carina Neves
 */


@ExtendWith(MockitoExtension.class)
class AirQualityServiceTest {

    @Mock(lenient = true)
    private Cache cache;

    @InjectMocks
    private AirQualityService airQualityService;

    @BeforeEach
    public void setUp() {
        List<String> allCities = Arrays.asList("Porto", "Santarém", "Aveiro");

        HashMap<String, String> statistics = new HashMap<>();
        statistics.put("hit", "7");
        statistics.put("miss", "3");
        statistics.put("citiesAirInfoIn", allCities.toString());

        AirQualityInfo info = new AirQualityInfo("27", "57.5", "95", "1.5", "6");
        AirQualityInfo[] airInfo = {info};
        long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        AirQuality aveiro = new AirQuality(airInfo,timestamp);

        Mockito.when(cache.getCities()).thenReturn(allCities);
        Mockito.when(cache.getStatistics()).thenReturn(statistics);
        Mockito.when(cache.getAirQuality("Aveiro")).thenReturn(aveiro);
        Mockito.when(cache.getAirQuality("blablabla")).thenReturn(null);
    }

    /**
     * Test method: List<String> getCities()
     */
     @Test
     public void givenCities_whenGetAllCities_thenReturn() {
         String city0 ="Porto";
         String city1 = "Santarém";
         String city2 = "Aveiro";

         List<String> all = airQualityService.getCities();
         assertThat(all)
            .hasSize(3)
            .extracting(String::toString)
            .contains(city0, city1, city2);
     }

    /**
     * Test method: HashMap<String, String> getStatistics()
     */
     @Test
     public void givenStatistics_whenGetStatistics_thenReturn() {
         int miss = 3;
         int hit = 7;
         List<String> cities = Arrays.asList("Porto", "Santarém", "Aveiro");

         Map<String, String> stat = airQualityService.getStatistics();
         assertThat(stat)
            .hasSize(3)
            .containsKeys("hit", "miss", "citiesAirInfoIn")
            .containsValues(String.valueOf(hit), String.valueOf(miss), cities.toString());
     }

    /**
     * Test method: AirQualityInfo[] getAirQuality(String city)
     */
     @Test
     public void whenInvalidCity_thenAirQualityInfoShouldBeNull() {
        String invalidCity = "blablabla";
        AirQualityInfo[] returned = airQualityService.getAirQuality(invalidCity);
        AirQualityInfo info= airQualityService.getAirQuality(invalidCity)[0];
        assertThat(returned)
                .hasSize(1);
        assertEquals(null, info.getAqi());
        assertEquals(null, info.getO3());
        assertEquals(null, info.getCo());
        assertEquals(null, info.getSo2());
        assertEquals(null, info.getNo2());

    }

    /**
     * Test method: AirQualityInfo[] getAirQuality(String city)
     */
     @Test
     public void whenValidCity_thenAirQualityInfoShouldBeReturned() {
        String validCity = "Aveiro";
        AirQualityInfo[] returned = airQualityService.getAirQuality(validCity);
        AirQualityInfo info= airQualityService.getAirQuality(validCity)[0];
        assertThat(returned)
                .hasSize(1);
        assertEquals("27", info.getAqi());
        assertEquals("57.5", info.getO3());
        assertEquals("95", info.getCo());
        assertEquals("1.5", info.getSo2());
        assertEquals("6", info.getNo2());
     }

}