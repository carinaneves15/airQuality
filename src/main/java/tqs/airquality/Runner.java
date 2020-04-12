package tqs.airquality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Run.java
 *
 * @author Carina Neves
 */
@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private AirQualityService airQualityService;

    @Override
    public void run(String... args) throws Exception {
        // names of cities that are available on the website
        String[] cities = {"Viana-do-Castelo", "Braga", "VilaReal", "Bragança", "Porto", "Aveiro", "Viseu", "Guarda",
                "Coimbra", "CasteloBranco", "Leiria", "Santarém", "Lisboa", "Portalegre", "Évora", "Setubal",
                "Beja", "Faro", "Funchal", "PontaDelgada"};

        // save cities in cache
        for (String c : cities ) {
            airQualityService.saveCity(c);
        }
    }
}
