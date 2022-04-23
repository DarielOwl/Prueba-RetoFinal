package co.com.sofka.pruebaRetoFinal.services;

import co.com.sofka.pruebaRetoFinal.DTOs.EstudianteDTO;
import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EstudianteService {

    //-----------------CRUD-----------------//
    Mono<Estudiante> save(Estudiante estudiante);

    Flux<EstudianteDTO> findAll();

    Mono<Estudiante> update(String id, Estudiante estudiante);

    Mono<Estudiante> delete(String id);

    //-----------------CRUD-----------------//

}
