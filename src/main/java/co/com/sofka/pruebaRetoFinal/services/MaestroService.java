package co.com.sofka.pruebaRetoFinal.services;

import co.com.sofka.pruebaRetoFinal.DTOs.MaestroDTO;
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

    //Listar Maestro por Documento de Identidad----------------------
    Mono<MaestroDTO> buscarMaestroPorDocumentoIdentidad(String documentoIdentidad);

    //Buscar Maestro por Documento de Identidad
    Mono<Maestro> findByDocumentoIdentidad(String documentoIdentidad);

    //Buscar Maestro por Materia en Especifico
    Flux<Maestro> findByMaterias(String materia);

    //Listar Maestro por Materia----------------------
    Flux<MaestroDTO> buscarMaestroPorMateria(String materia);
}
