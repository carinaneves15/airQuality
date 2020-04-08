package tqs.airquality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AirQualityController {

    @Autowired
    private AirQualityService airQualityService;

    @GetMapping("/airQuality/{city}")
    public AirQualityInfo[] getCityAirQuality (@PathVariable(value = "city") City city) {
        return airQualityService.getAirQualityInfo(city);
    }

}