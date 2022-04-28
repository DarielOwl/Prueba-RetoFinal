package co.com.sofka.pruebaRetoFinal.repositories;

import co.com.sofka.pruebaRetoFinal.models.Acudiente;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AcudienteRepository extends ReactiveMongoRepository<Acudiente,String> {
    Mono<Acudiente> findByDocumentoIdentidad(String documentoIdentidad);
    Mono<Void> deleteAll();
}
