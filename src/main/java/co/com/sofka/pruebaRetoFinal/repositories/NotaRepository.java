package co.com.sofka.pruebaRetoFinal.repositories;

import co.com.sofka.pruebaRetoFinal.models.Nota;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface NotaRepository extends ReactiveMongoRepository<Nota,String> {
}
