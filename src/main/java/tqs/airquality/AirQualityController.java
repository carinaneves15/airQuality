package tqs.airquality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AirQualityController {

    @Autowired
    private AirQualityService airQualityService;

    //como faço para ficar /fields/lat=x&lon=y???
    @GetMapping("/fields")
    public Fields[] getAirQualityLatLon(){
        return airQualityService.getFields();
    }
}