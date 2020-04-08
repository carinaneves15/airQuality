package tqs.airquality;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    public void save (City city) {
        Cache.addCity(city);
    }

    public List<City> getAllCities() {
        return Cache.getCities();
    }

}
