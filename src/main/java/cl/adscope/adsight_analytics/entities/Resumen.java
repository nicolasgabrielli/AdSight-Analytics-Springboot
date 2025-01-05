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
    private String id;
    private Date fecha;
    private Integer totalPersonas;
    private List<Persona> personas;

}
