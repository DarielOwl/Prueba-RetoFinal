package co.com.sofka.pruebaRetoFinal.services;

import co.com.sofka.pruebaRetoFinal.models.Nota;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotaService {

    //-----------------CRUD-----------------//
    Mono<Nota> save(Nota nota);

    Flux<Nota> findAll();

    Mono<Nota> update(String id, Nota nota);

    Mono<Nota> delete(String id);

    Mono<Void> deleteAll();
    //-----------------CRUD-----------------//
}
