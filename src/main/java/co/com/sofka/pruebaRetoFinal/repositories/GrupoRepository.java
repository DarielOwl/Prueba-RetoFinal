package co.com.sofka.pruebaRetoFinal.repositories;

import co.com.sofka.pruebaRetoFinal.models.Grupo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GrupoRepository extends ReactiveMongoRepository<Grupo,String> {

}
