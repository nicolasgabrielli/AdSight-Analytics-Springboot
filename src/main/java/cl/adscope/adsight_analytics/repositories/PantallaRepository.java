package cl.adscope.adsight_analytics.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import cl.adscope.adsight_analytics.entities.Pantalla;

public interface PantallaRepository extends MongoRepository<Pantalla, String> {
    
}
