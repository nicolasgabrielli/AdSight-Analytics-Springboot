package cl.adscope.adsight_analytics.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import cl.adscope.adsight_analytics.models.Persona;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "resumen")
public class Resumen {
    @Id
    private String id;  // Id del resumen
    private Date fecha; // Fecha del resumen, se genera automaticamente en Spring Boot
    private Integer total_personas; // Total de personas en el resumen
    private List<Persona> personas; // Lista de personas en el resumen
    private String id_pantalla; // Id de la pantalla a la que pertenece el resumen
}
