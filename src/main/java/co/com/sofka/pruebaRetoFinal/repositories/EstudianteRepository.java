package co.com.sofka.pruebaRetoFinal.repositories;

import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface EstudianteRepository extends ReactiveMongoRepository<Estudiante,String> {
    Mono<Estudiante> findById(String id);
    Mono<Estudiante> findByDocumentoIdentidad(String documentoIdentidad);
    
}
