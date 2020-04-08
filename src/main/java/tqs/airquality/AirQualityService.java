package tqs.airquality;

import org.springframework.stereotype.Service;

@Service
public class AirQualityService {

    public Fields[] getFields(double lat, double lon) {
        return null; //Cache.getAirQuality(lat, lon);
    }
}
