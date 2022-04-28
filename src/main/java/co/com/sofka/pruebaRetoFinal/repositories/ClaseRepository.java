package co.com.sofka.pruebaRetoFinal.repositories;

import co.com.sofka.pruebaRetoFinal.models.Clase;
import co.com.sofka.pruebaRetoFinal.models.Maestro;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ClaseRepository extends ReactiveMongoRepository<Clase,String> {
    Mono<Void> deleteAll();
}
