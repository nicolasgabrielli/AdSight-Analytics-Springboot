package cl.adscope.adsight_analytics.services;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import cl.adscope.adsight_analytics.entities.Pantalla;
import cl.adscope.adsight_analytics.repositories.PantallaRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PantallaService {
    private final PantallaRepository pantallaRepository;

    public List<Pantalla> getAllPantallas() {
        return pantallaRepository.findAll();
    }

    public Pantalla createPantalla(Pantalla pantalla) {
        return pantallaRepository.save(pantalla);
    }
}
