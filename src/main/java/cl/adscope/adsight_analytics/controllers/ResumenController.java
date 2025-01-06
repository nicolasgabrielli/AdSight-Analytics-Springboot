package cl.adscope.adsight_analytics.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import java.util.Map;
import java.util.List;
import cl.adscope.adsight_analytics.services.ResumenService;
import cl.adscope.adsight_analytics.entities.Resumen;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resumen")
public class ResumenController {

    private final ResumenService resumenService;

    @GetMapping
    public ResponseEntity<List<Resumen>> getAllResumen() {
        List<Resumen> resumen = resumenService.getAllResumen();
        return ResponseEntity.ok(resumen);
    }

    @PostMapping
    public ResponseEntity<Resumen> createResumen(@RequestBody Map<String, Object> resumenRequest) {
        Resumen resumen = new Resumen();

        resumen = resumenService.createResumen(resumenRequest);

        System.out.println("Resumen creado: " + resumen.toString());

        return ResponseEntity.ok(resumen);
    }

    @GetMapping("/pantalla/{idPantalla}")
    public ResponseEntity<List<Resumen>> getResumenesPorPantalla(@PathVariable String idPantalla) {
        List<Resumen> resumenes = resumenService.getResumenesPorPantalla(idPantalla);
        return ResponseEntity.ok(resumenes);
    }
}