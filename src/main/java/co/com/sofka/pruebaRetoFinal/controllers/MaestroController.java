package co.com.sofka.pruebaRetoFinal.controllers;

import co.com.sofka.pruebaRetoFinal.DTOs.MaestroDTO;
import co.com.sofka.pruebaRetoFinal.mappers.MaestroMapper;
import co.com.sofka.pruebaRetoFinal.models.Maestro;
import co.com.sofka.pruebaRetoFinal.models.values.Materia;
import co.com.sofka.pruebaRetoFinal.models.values.Materias;
import co.com.sofka.pruebaRetoFinal.services.Impl.MaestroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class MaestroController {

    @Autowired
    MaestroServiceImpl maestroService;
    MaestroMapper maestroMapper = new MaestroMapper();

    //-----------------CRUD-----------------//
    //Guardar un Maestro
    @PostMapping("/addMaestro")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<MaestroDTO> save(@RequestBody MaestroDTO maestroDTO) {
        return this.maestroService.save(maestroMapper.createMaestro(maestroDTO)).thenReturn(maestroDTO);
    }

    //Mostrar Todos los Maestros
    @GetMapping(value = "/allMaestro")
    private Flux<Maestro> findAll() {
        //return maestroMapper.convertirMaestroDTOs(this.maestroService.findAll());
        return this.maestroService.findAll();
    }

    //Actualizar Maestro
    @PutMapping("/updateMaestro/{id}")
    private Mono<Maestro> update(@PathVariable("id") String id, @RequestBody Maestro maestro) {
        return this.maestroService.update(id, maestro)
                .flatMap(maestro1 -> Mono.just(maestro1)).switchIfEmpty(Mono.empty());
    }

    //Eliminar Maestro
    @DeleteMapping("/removeMaestro/{id}")
    private Mono<Maestro> delete(@PathVariable("id") String id) {
        return this.maestroService.delete(id)
                .flatMap(maestro1 -> Mono.just((maestro1)).switchIfEmpty(Mono.empty()));

    }
    //-----------------CRUD-----------------//

    //Listar Maestro por Documento de Identidad----------------------
    @GetMapping(value = "/searchMaestro/{documentoIdentidad}")
    public Mono<MaestroDTO> buscarMaestroPorDocumentoIdentidad(@PathVariable("documentoIdentidad") String documentoIdentidad){
        return this.maestroService.buscarMaestroPorDocumentoIdentidad(documentoIdentidad);
    }

    //Listar Maestro por Materia----------------------
    @GetMapping(value = "/searchMaestroByMateria/{materia}")
    public Flux<MaestroDTO> buscarMaestroPorMateria(@PathVariable("materia") String materia){
        return this.maestroService.buscarMaestroPorMateria(materia);
    }

    //buscarMateriasQueNoImparteMaestroById
    @GetMapping("/notMateriasFromMaestro/{idMaestro}")
    public Flux<Materia> buscarMateriasQueNoImparteMaestroById(@PathVariable("idMaestro") String idMaestro){
        try{
            List<String> materias = new Materias().getMateria();
            List<String> materiasMaestro = this.maestroService.findById(idMaestro).block().getMaterias();
            List<Materia> materiaList = new ArrayList<Materia>();
            materias.removeAll(materiasMaestro);
            for(String materia: materias){
                materiaList.add(new Materia(materia));
            }
            return Flux.fromIterable(materiaList);
        }catch (Exception e){
            return Flux.empty();
        }
    }

}
