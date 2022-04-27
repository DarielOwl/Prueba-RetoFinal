package co.com.sofka.pruebaRetoFinal.services.Impl;

import co.com.sofka.pruebaRetoFinal.DTOs.MaestroDTO;
import co.com.sofka.pruebaRetoFinal.mappers.MaestroMapper;
import co.com.sofka.pruebaRetoFinal.models.Maestro;
import co.com.sofka.pruebaRetoFinal.repositories.MaestroRepository;
import co.com.sofka.pruebaRetoFinal.services.MaestroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MaestroServiceImpl implements MaestroService {

    @Autowired
    MaestroRepository maestroRepository; //Instancia del repositorio Estudiante

    @Autowired
    MaestroMapper maestroMapper; //Instancia del repositorio Estudiante

    //-----------------CRUD-----------------//
    @Override
    public Mono<Maestro> save(Maestro maestro) {
        return maestroRepository.save(maestro);
    }

    @Override
    public Flux<Maestro> findAll() {
        return this.maestroRepository.findAll();
    }

    @Override
    public Mono<Maestro> update(String id, Maestro maestro) {
        return this.maestroRepository.findById(id)
                .flatMap(grupoUpdate -> {
                    maestro.setId(id);
                    return save(maestro);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Maestro> delete(String id) {
        return this.maestroRepository
                .findById(id)
                .flatMap(maestroDelete -> this.maestroRepository
                        .deleteById(maestroDelete.getId())
                        .thenReturn(maestroDelete));
    }
    //-----------------CRUD-----------------//

    //Listar Maestro por Documento de Identidad----------------------
    @Override
    public Mono<MaestroDTO> buscarMaestroPorDocumentoIdentidad(String documentoIdentidad) {

        //Busca el grupo por su ID, si no existe devuelve vacio
        Mono<Maestro> maestro = maestroRepository.findByDocumentoIdentidad(documentoIdentidad).switchIfEmpty(Mono.empty());

        //Lo convierte a Mono<Maestro> y lo manda
        return maestroMapper.createMaestroDTOMono(maestro);
    }

    //Buscar Documento de Identidad----------------------
    @Override
    public Mono<Maestro> findByDocumentoIdentidad(String documentoIdentidad) {
        return this.maestroRepository.findByDocumentoIdentidad(documentoIdentidad);
    }

    @Override
    public Mono<Maestro> updateMateriaDelMaestro(String id, String materia) {

        //Buscamos el maestro para actualizar la lista de materias
        //Maestro updateMaestro = this.maestroRepository.findById(id).block();

        //A la lista de materias le añadimos la nueva materia
        //updateMaestro.getMaterias().add(materia);

        //return null;

        return this.maestroRepository.findById(id).flatMap(maestroUpdate -> {
                    maestroUpdate.setId(id);
                    maestroUpdate.getMaterias().add(materia);
                    return save(maestroUpdate);
                })
                .switchIfEmpty(Mono.empty());
    }

}
