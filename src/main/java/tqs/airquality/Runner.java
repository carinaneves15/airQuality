package tqs.airquality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private CityService cityService;

    @Override
    public void run(String... args) throws Exception {
        // Array List com as cidades válidas
        List<City> cities = new ArrayList<>();

        // Nomes das cidades a serem instanciadas
        String[] names = {"Viana-do-Castelo", "Braga", "VilaReal", "Bragança", "Porto", "Aveiro", "Viseu", "Guarda",
                "Coimbra", "CasteloBranco", "Leiria", "Santarém", "Lisboa", "Portalegre", "Évora", "Setubal",
                "Beja", "Faro", "Funchal", "PontaDelgada"};

        // Instanciação das cidades
        int nrCities = 0;
        while (nrCities < names.length) {
            City c = new City(names[nrCities]);
            cities.add(c);
            nrCities++;
        }

        // Guardar cidades
        for (City c : cities) {
            cityService.save(c);
        }

    }
}
