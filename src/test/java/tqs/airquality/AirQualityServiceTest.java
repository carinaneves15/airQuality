package tqs.airquality;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

        Mockito.when(cache.getCities()).thenReturn(allCities);
    }

    /**
    @Test
    public void givenCities_whenGetAllCities_thenReturn() {
        String city0 ="Porto";
        String city1 = "Santarém";
        String city2 = "Aveiro";

        List<String> all = airQualityService.getCities();
        assertThat(all)
                .hasSize(3);
                //.extracting(String::toString)
                //.contains(city0, city1, city2);
    }
    */

}