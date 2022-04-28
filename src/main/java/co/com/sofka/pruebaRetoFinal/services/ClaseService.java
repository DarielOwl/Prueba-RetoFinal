package co.com.sofka.pruebaRetoFinal.services;

import co.com.sofka.pruebaRetoFinal.models.Clase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClaseService {

    //-----------------CRUD-----------------//
    Mono<Clase> save(Clase clase);

    Flux<Clase> findAll();

    Mono<Void> deleteAll();
    //-----------------CRUD-----------------//

}
