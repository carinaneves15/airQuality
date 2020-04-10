package tqs.airquality;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AirQualityController.class)
class AirQualityControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirQualityService airQualityService;

    @BeforeEach
    public void setUp() throws Exception{
    }

    @Test
    public void givenCities_whenGetAllCities_thenReturnJsonArray() throws Exception {
        List<String> allCities = new ArrayList<>();
        String[] cities = {"Porto", "Santarém", "Aveiro"};
        for (String c : cities)
            allCities.add(c);

        given(airQualityService.getAllCities()).willReturn(allCities);
        mvc.perform(get("/api/airQuality/allCities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", is("Porto")))
                .andExpect(jsonPath("$[1]", is("Santarém")))
                .andExpect(jsonPath("$[2]", is("Aveiro")));
    }

    @Test
    public void givenStatistics_whenGetStatistics_thenReturnArrayJson() throws Exception {
        HashMap<String, Integer> statistics = new HashMap<>();
        statistics.put("hit", 7);
        statistics.put("miss", 3);

        given(airQualityService.getStatistics()).willReturn(statistics);
        mvc.perform(get("/api/airQuality/statistics")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.miss", is(statistics.get("miss"))))
                .andExpect(jsonPath("$.hit", is(statistics.get("hit"))));
    }
    /**
    @Test
    public void whenGetCityInfo_thenReturnAirQualityInfo() throws Exception {
        String valid_city = "Aveiro";

        given(airQualityService.getAirQualityInfo(valid_city)).willReturn("Perceber como por aqui um airQualityInfo[] -.-");
        mvc.perform(get("/api/airQuality/".concat(valid_city)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0][1]", is("aqi")));
    }
    */

    @Test
    public void whenGetCityDoesNotExist_thenReturnNothing() throws Exception {
        String invalid_city = "Tomar";

        given(airQualityService.getAirQualityInfo(invalid_city)).willReturn(null);
        mvc.perform(get("/airQuality/".concat(invalid_city)))
                .andExpect(status().isNotFound());
    }

}