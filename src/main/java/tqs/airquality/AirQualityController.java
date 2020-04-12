package tqs.airquality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * AirQualityController.java
 *
 * @author Carina Neves
 */

@RestController
@RequestMapping("/api/airQuality")
public class AirQualityController {

    @Autowired
    private AirQualityService airQualityService;

    @GetMapping("/cities")
    public List<String> getCities() {
        return airQualityService.getCities();
    }

    @GetMapping("/statistics")
    public HashMap<String, String> getStatistics () {
        return airQualityService.getStatistics();
    }

    @GetMapping("/{city}")
    public AirQualityInfo[] getCityAirQuality (@PathVariable(value = "city") String city) {
        return airQualityService.getAirQuality(city);
    }
}