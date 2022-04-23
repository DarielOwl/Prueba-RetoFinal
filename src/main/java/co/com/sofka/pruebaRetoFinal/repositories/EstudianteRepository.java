package co.com.sofka.pruebaRetoFinal.repositories;

import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EstudianteRepository extends ReactiveMongoRepository<Estudiante,String> {

}
