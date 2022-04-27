package co.com.sofka.pruebaRetoFinal.services;

import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import co.com.sofka.pruebaRetoFinal.models.Grupo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GrupoService {

    //-----------------CRUD-----------------//
    Mono<Grupo> save(Grupo grupo);

    Flux<Grupo> findAll();

    Mono<Grupo> update(String id, Grupo grupo);

    Mono<Grupo> delete(String id);

    Mono<Grupo> findById(String id);
    //-----------------CRUD-----------------//
}
