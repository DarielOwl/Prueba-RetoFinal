package co.com.sofka.pruebaRetoFinal.services;

import co.com.sofka.pruebaRetoFinal.models.Maestro;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MaestroService {

    //-----------------CRUD-----------------//
    Mono<Maestro> save(Maestro maestro);

    Flux<Maestro> findAll();

    Mono<Maestro> update(String id, Maestro maestro);

    Mono<Maestro> delete(String id);
    //-----------------CRUD-----------------//

    //Mono<Maestro> buscarDocumentoIdentidadMaestro(String id);
}
