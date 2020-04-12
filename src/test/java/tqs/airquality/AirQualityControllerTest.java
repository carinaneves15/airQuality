package tqs.airquality;

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

import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * AirQualityControllerTest.java
 *
 * @author Carina Neves
 */

@WebMvcTest(AirQualityController.class)
class AirQualityControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirQualityService airQualityService;

    /**
     * Test method: List<String> getCities()
     */
    @Test
    public void givenCities_whenGetAllCities_thenReturnJsonArray() throws Exception {
        List<String> allCities = new ArrayList<>();
        String[] cities = {"Porto", "Santarém", "Aveiro"};
        for (String c : cities)
            allCities.add(c);

        given(airQualityService.getCities()).willReturn(allCities);
        mvc.perform(get("/api/airQuality/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", is("Porto")))
                .andExpect(jsonPath("$[1]", is("Santarém")))
                .andExpect(jsonPath("$[2]", is("Aveiro")));
    }

    /**
     * Test method: HashMap<String, String> getStatistics()
     */
    @Test
    public void givenStatistics_whenGetStatistics_thenReturnArrayJson() throws Exception {
        List<String> allCities = new ArrayList<>();
        String[] cities = {"Porto", "Santarém", "Aveiro"};
        for (String c : cities)
            allCities.add(c);

        HashMap<String, String> statistics = new HashMap<>();
        statistics.put("hit", "7");
        statistics.put("miss", "3");
        statistics.put("citiesAirInfoIn", allCities.toString());

        given(airQualityService.getStatistics()).willReturn(statistics);
        mvc.perform(get("/api/airQuality/statistics")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.miss", is(statistics.get("miss"))))
                .andExpect(jsonPath("$.hit", is(statistics.get("hit"))))
                .andExpect(jsonPath("$.citiesAirInfoIn", is(statistics.get("citiesAirInfoIn"))));
    }

    /**
     * Test method: AirQualityInfo[] getCityAirQuality(@PathVariable(value = "city") String city)
     */
    @Test
    public void whenGetAirQuality_theReturnAirQualityInfo() throws Exception {
        AirQualityInfo info = new AirQualityInfo("32", "68.2071", "205", "1.51429", "6");
        AirQualityInfo[] airInfo = {info};
        String valid_city = "Lisboa";
        given(airQualityService.getAirQuality(anyString())).willReturn(airInfo);
        mvc.perform(get("/api/airQuality/".concat(valid_city)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]", hasKey("aqi")))
                .andExpect(jsonPath("$[0]", hasKey("o3")))
                .andExpect(jsonPath("$[0]", hasKey("co")))
                .andExpect(jsonPath("$[0]", hasKey("so2")))
                .andExpect(jsonPath("$[0]", hasKey("no2")));

    }
    /**
     * Test method: AirQualityInfo[] getCityAirQuality(@PathVariable(value = "city") String city)
     */
    @Test
    public void whenGetCityDoesNotExist_thenReturnNothing() throws Exception {
        String invalid_city = "coisa";
        given(airQualityService.getAirQuality(invalid_city)).willReturn(null);
        mvc.perform(get("/airQuality/".concat(invalid_city)))
                .andExpect(status().isNotFound());
    }

}