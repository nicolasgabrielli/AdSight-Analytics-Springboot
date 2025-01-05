package cl.adscope.adsight_analytics.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import java.util.Map;
import cl.adscope.adsight_analytics.services.ResumenService;
import cl.adscope.adsight_analytics.entities.Resumen;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resumen")
public class ResumenController {

    private final ResumenService resumenService;

    @PostMapping
    public ResponseEntity<Resumen> getResumen(@RequestBody Map<String, Object> resumenRequest) {
        Resumen resumen = new Resumen();

        System.out.println("resumenRequest: " + resumenRequest);

        // TODO: Implementar la lógica para obtener el resumen
        resumenService.getResumen(resumenRequest);

        return ResponseEntity.ok(resumen);
    }
}