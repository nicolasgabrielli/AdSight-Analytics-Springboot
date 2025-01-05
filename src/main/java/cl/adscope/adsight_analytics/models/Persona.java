package cl.adscope.adsight_analytics.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Persona {
    private String id;
    private String edad_promedio;
    private String genero_predominante;
    private String raza_predominante;
    private String emocion_predominante;
    private String tiempo_en_pantalla;
}
