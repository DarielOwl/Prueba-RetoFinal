package co.com.sofka.pruebaRetoFinal.repositories;

import co.com.sofka.pruebaRetoFinal.models.Maestro;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MaestroRepository extends ReactiveMongoRepository<Maestro,String> {

    Mono<Maestro> findByDocumentoIdentidad(String documentoIdentidad);

    Flux<Maestro> findByMaterias(String materia);
}
