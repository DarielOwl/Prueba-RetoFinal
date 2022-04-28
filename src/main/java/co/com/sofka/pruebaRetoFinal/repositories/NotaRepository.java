package co.com.sofka.pruebaRetoFinal.repositories;

import co.com.sofka.pruebaRetoFinal.models.Nota;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface NotaRepository extends ReactiveMongoRepository<Nota,String> {
    Mono<Void> deleteAll();
}
