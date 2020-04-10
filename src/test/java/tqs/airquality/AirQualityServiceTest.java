package tqs.airquality;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AirQualityServiceTest {

    @Mock(lenient = true)
    private Cache cache;

    @InjectMocks
    private AirQualityService airQualityService;

    @BeforeEach
    public void setUp() {
        List<String> allCities = new ArrayList<>();
        String[] cities = {"Porto", "Santarém", "Aveiro"};
        for (String c : cities)
            allCities.add(c);

        HashMap<String, Integer> statistics = new HashMap<>();
        statistics.put("hit", 7);
        statistics.put("miss", 3);

        //MUDAR AQUI
        AirQualityInfo[] info = null;

        Mockito.when(cache.getCities()).thenReturn(allCities);
        Mockito.when(cache.getStatistics()).thenReturn(statistics);
        Mockito.when(cache.getAirQuality("Aveiro")).thenReturn(null);
    }


}