package co.com.sofka.pruebaRetoFinal.services;

import co.com.sofka.pruebaRetoFinal.models.Acudiente;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AcudienteService {

    //-----------------CRUD-----------------//
    Mono<Acudiente> save(Acudiente acudiente);

    Flux<Acudiente> findAll();

    Mono<Acudiente> update(String id, Acudiente acudiente);

    Mono<Acudiente> delete(String id);

    Mono<Acudiente> findByDocumentoIdentidad(String documentoIdentidad);

    Mono<Void> deleteAll();
    //-----------------CRUD-----------------//

}
