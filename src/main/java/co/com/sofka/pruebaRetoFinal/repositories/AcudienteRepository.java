package co.com.sofka.pruebaRetoFinal.repositories;

import co.com.sofka.pruebaRetoFinal.models.Acudiente;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AcudienteRepository extends ReactiveMongoRepository<Acudiente,String> {
}
