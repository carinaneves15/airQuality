package tqs.airquality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private AirQualityService airQualityService;

    @Override
    public void run(String... args) throws Exception {
        // Nomes das cidades a serem guardadas
        String[] cities = {"Viana-do-Castelo", "Braga", "VilaReal", "Bragança", "Porto", "Aveiro", "Viseu", "Guarda",
                "Coimbra", "CasteloBranco", "Leiria", "Santarém", "Lisboa", "Portalegre", "Évora", "Setubal",
                "Beja", "Faro", "Funchal", "PontaDelgada"};

        // Guardar cidades
        for (String c : cities ) {
            airQualityService.saveCity(c);
        }
    }
}
