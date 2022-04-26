package co.com.sofka.pruebaRetoFinal.repositories;

import co.com.sofka.pruebaRetoFinal.models.Clase;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClaseRepository extends ReactiveMongoRepository<Clase,String> {
}
