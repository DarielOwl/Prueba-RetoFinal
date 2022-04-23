package co.com.sofka.pruebaRetoFinal.repositories;

import co.com.sofka.pruebaRetoFinal.models.Maestro;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MaestroRepository extends ReactiveMongoRepository<Maestro,String> {
}
