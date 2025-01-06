package cl.adscope.adsight_analytics.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import cl.adscope.adsight_analytics.entities.Resumen;
import java.util.List;

public interface ResumenRepository extends MongoRepository<Resumen, String> {
    @Query("{ 'id_pantalla' : ?0 }")
    List<Resumen> findByIdPantalla(String idPantalla);
}
