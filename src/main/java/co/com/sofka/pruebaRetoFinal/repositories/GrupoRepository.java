package co.com.sofka.pruebaRetoFinal.repositories;

import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import co.com.sofka.pruebaRetoFinal.models.Grupo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface GrupoRepository extends ReactiveMongoRepository<Grupo,String> {
    Mono<Grupo> findById(String id);
}
