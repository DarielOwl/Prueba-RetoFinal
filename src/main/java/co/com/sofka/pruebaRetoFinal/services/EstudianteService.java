package co.com.sofka.pruebaRetoFinal.services;

import co.com.sofka.pruebaRetoFinal.DTOs.EstudianteDTO;
import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EstudianteService {

    //-----------------CRUD-----------------//
    Mono<Estudiante> save(Estudiante estudiante);

    Flux<Estudiante> findAll();

    Mono<Estudiante> update(String id, Estudiante estudiante);

    Mono<Estudiante> delete(String id);

    Mono<Estudiante> findById(String id);

    Mono<Estudiante> findByDocumentoIdentidad(String documentoIdentidad);


    //-----------------CRUD-----------------//

}
