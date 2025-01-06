package cl.adscope.adsight_analytics.services;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import cl.adscope.adsight_analytics.entities.Resumen;
import cl.adscope.adsight_analytics.models.Persona;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import cl.adscope.adsight_analytics.repositories.ResumenRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumenService {
    
    private final ResumenRepository resumenRepository;
    private final SimpMessagingTemplate messagingTemplate;
    
    public Resumen createResumen(Map<String, Object> resumenRequest) {
        Resumen resumen = new Resumen();
        
        if (resumenRequest.containsKey("personas")) {
            List<Persona> personas = new ArrayList<>();
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> personasList = (List<Map<String, Object>>) resumenRequest.get("personas");
            for (Map<String, Object> persona : personasList) {
                Object edadPromedio = persona.get("edad_promedio");
                Object tiempoEnPantalla = persona.get("tiempo_en_pantalla");
                
                personas.add(Persona.builder()
                    .id((String) persona.get("id"))
                    .edad_promedio(edadPromedio != null ? ((Number) edadPromedio).floatValue() : 0.0f)
                    .genero_predominante((String) persona.get("genero_predominante"))
                    .raza_predominante((String) persona.get("raza_predominante"))
                    .emocion_predominante((String) persona.get("emocion_predominante"))
                    .tiempo_en_pantalla(tiempoEnPantalla != null ? ((Number) tiempoEnPantalla).floatValue() : 0.0f)
                    .build());
            }
            resumen.setPersonas(personas);
        }
        
        if (resumenRequest.containsKey("id_pantalla")) {
            resumen.setId_pantalla((String) resumenRequest.get("id_pantalla"));
        }
        if (resumenRequest.containsKey("total_personas")) {
            resumen.setTotal_personas((Integer) resumenRequest.get("total_personas"));
        }
        if (resumenRequest.containsKey("fecha")) {
            resumen.setFecha(new Date()); // Usar la fecha actual del servidor
        }
        
        resumen = resumenRepository.save(resumen);
        messagingTemplate.convertAndSend("/topic/resumen", resumen);
        return resumen;
    }
    
    public List<Resumen> getAllResumen() {
        return resumenRepository.findAll();
    }
    
    public List<Resumen> getResumenesPorPantalla(String idPantalla) {
        // Obtener todos los resúmenes de la pantalla
        List<Resumen> resumenes = resumenRepository.findByIdPantalla(idPantalla);
        
        // Agrupar resúmenes por día y calcular estadísticas
        Map<String, List<Resumen>> resumenesPorDia = resumenes.stream()
            .collect(Collectors.groupingBy(resumen -> {
                LocalDateTime fecha = resumen.getFecha().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
                return fecha.format(DateTimeFormatter.ISO_LOCAL_DATE);
            }));

        // Crear resúmenes diarios
        return resumenesPorDia.entrySet().stream()
            .map(entry -> {
                String fecha = entry.getKey();
                List<Resumen> resumenesDelDia = entry.getValue();
                
                // Calcular totales diarios
                int totalPersonas = resumenesDelDia.stream()
                    .mapToInt(Resumen::getTotal_personas)
                    .sum();
                
                // Combinar todas las personas del día
                List<Persona> todasLasPersonas = resumenesDelDia.stream()
                    .flatMap(r -> r.getPersonas().stream())
                    .collect(Collectors.toList());

                // Crear resumen diario
                Resumen resumenDiario = new Resumen();
                resumenDiario.setId_pantalla(idPantalla);
                resumenDiario.setFecha(Date.from(LocalDate.parse(fecha).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                resumenDiario.setTotal_personas(totalPersonas);
                resumenDiario.setPersonas(todasLasPersonas);
                
                return resumenDiario;
            })
            .sorted(Comparator.comparing(Resumen::getFecha).reversed())
            .collect(Collectors.toList());
    }
}

