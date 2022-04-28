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

import java.util.ArrayList;
import java.util.List;

@Service
public class MaestroServiceImpl implements MaestroService {

    @Autowired
    MaestroRepository maestroRepository; //Instancia del repositorio Maestro

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

        //Busca el maestro por su Documento de Identidad, sino retorna vacio el obj
        Mono<Maestro> maestro = maestroRepository.findByDocumentoIdentidad(documentoIdentidad).switchIfEmpty(Mono.empty());

        //Lo convierte a Mono<Maestro> y lo manda
        return maestroMapper.createMaestroDTOMono(maestro);
    }

    //Buscar Documento de Identidad----------------------
    @Override
    public Mono<Maestro> findByDocumentoIdentidad(String documentoIdentidad) {
        return this.maestroRepository.findByDocumentoIdentidad(documentoIdentidad);
    }

    //Buscar Maestro por Materia en Especifico----------------------
    @Override
    public Flux<Maestro> findByMaterias(String materia) {
        return this.maestroRepository.findByMaterias(materia);
    }

    //Listar Maestro por Materia----------------------
    @Override
    public Flux<MaestroDTO> buscarMaestroPorMateria(String materia) {

        //Busca el maestro por su Materia, sino retorna vacio el obj
        Flux<Maestro> maestro = maestroRepository.findByMaterias(materia).switchIfEmpty(Mono.empty());

        //Lo convierte a Mono<Maestro> y lo manda
        return maestroMapper.convertirMaestroDTOs(maestro);
    }
    @Override
    public Mono<Maestro> updateMateriaDelMaestro(String id, String materia) {

        return this.maestroRepository.findById(id).flatMap(maestroUpdate -> {

                    //Verificamos que la lista este vacia
                    if (maestroUpdate.getMaterias() == null) {
                        //Obtener la lista de Materia y añadirle la nueva materia
                        List<String> materiaUpdate = new ArrayList<String>();
                        materiaUpdate.add(materia);

                        //Setiar la Id Maestro y la lista de Materias
                        maestroUpdate.setId(id);
                        maestroUpdate.setMaterias(materiaUpdate);

                        return save(maestroUpdate);
                    }

                    //Si la lista no esta vacia hacemos simplemente añadimos a a lista
                    List<String> materiaUpdate = maestroUpdate.getMaterias();
                    materiaUpdate.add(materia);

                    //Setiar la Id Maestro y la lista de Materias
                    maestroUpdate.setId(id);
                    maestroUpdate.setMaterias(materiaUpdate);

                    return save(maestroUpdate);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Maestro> findById(String id){
        return maestroRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteAll() {return this.maestroRepository.deleteAll();}

}
