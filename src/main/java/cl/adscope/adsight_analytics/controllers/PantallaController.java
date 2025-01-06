package cl.adscope.adsight_analytics.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import cl.adscope.adsight_analytics.services.PantallaService;
import cl.adscope.adsight_analytics.entities.Pantalla;
import lombok.RequiredArgsConstructor;
import java.util.List;


@RestController
@RequestMapping("/pantallas")
@RequiredArgsConstructor
public class PantallaController {
    private final PantallaService pantallaService;

    @GetMapping
    public List<Pantalla> getAllPantallas() {
        return pantallaService.getAllPantallas();
    }

    @PostMapping
    public Pantalla createPantalla(@RequestBody Pantalla pantalla) {
        return pantallaService.createPantalla(pantalla);
    }
}
