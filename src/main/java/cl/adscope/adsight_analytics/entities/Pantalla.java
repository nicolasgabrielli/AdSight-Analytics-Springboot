package cl.adscope.adsight_analytics.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.Builder;

@Document(collection = "pantalla")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Pantalla {
    @Id
    private String id;
    private String direccion;
    private List<String> resumenesIds;
    private String nombre;
    private String email;
    private String telefono;
    private String fecha_instalacion;
}
