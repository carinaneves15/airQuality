package tqs.airquality;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        Mockito.when(cache.getAirQuality("aveiro")).thenReturn(null);
    }

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

     @Test
     public void givenStatistics_whenGetStatistics_thenReturn() {
         int miss = 3;
         int hit = 7;
         List<String> cities = Arrays.asList("Porto", "Santarém", "Aveiro");

         HashMap<String, String> stat = airQualityService.getStatistics();
         assertThat(stat)
            .hasSize(3)
            .containsKeys("hit", "miss", "citiesAirInfoIn")
            .containsValues(String.valueOf(hit), String.valueOf(miss), cities.toString());
     }

     @Test
     public void whenInvalidCity_thenAirQualityInfoShouldBeNull() {
        String invalidCity = "blablabla";
        AirQualityInfo[] returned = airQualityService.getAirQuality(invalidCity);
        AirQualityInfo info= airQualityService.getAirQuality(invalidCity)[0];
        assertThat(returned)
                .hasSize(1);
        assertEquals(info.getAqi(), null);
        assertEquals(info.getO3(), null);
        assertEquals(info.getCo(), null);
        assertEquals(info.getSo2(), null);
        assertEquals(info.getNo2(), null);

    }

     @Test
     public void whenValidCity_thenAirQualityInfoShouldBeReturned() {
        String validCity = "Aveiro";
        AirQualityInfo[] returned = airQualityService.getAirQuality(validCity);
        AirQualityInfo info= airQualityService.getAirQuality(validCity)[0];
        assertThat(returned)
                .hasSize(1);
        assertEquals(info.getAqi(), "27");
        assertEquals(info.getO3(), "57.5");
        assertEquals(info.getCo(), "95");
        assertEquals(info.getSo2(), "1.5");
        assertEquals(info.getNo2(), "6");
     }

}