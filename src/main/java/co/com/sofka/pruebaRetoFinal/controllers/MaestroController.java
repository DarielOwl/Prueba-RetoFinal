package co.com.sofka.pruebaRetoFinal.controllers;

import co.com.sofka.pruebaRetoFinal.DTOs.MaestroDTO;
import co.com.sofka.pruebaRetoFinal.mappers.MaestroMapper;
import co.com.sofka.pruebaRetoFinal.models.Maestro;
import co.com.sofka.pruebaRetoFinal.repositories.MaestroRepository;
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

    @Autowired
    MaestroRepository maestroRepository; //Instancia del repositorio Maestro

    //-----------------CRUD-----------------//
    //Guardar un Maestro
    @PostMapping("/addMaestro")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<Maestro> save(@RequestBody Maestro maestro) {
        return this.maestroService.save(maestro);
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
    public Mono<MaestroDTO> buscarMaestroPorDocumentoIdentidad(@PathVariable("documentoIdentidad") String documentoIdentidad) {
        return this.maestroService.buscarMaestroPorDocumentoIdentidad(documentoIdentidad);
    }

    //Listar Maestro por Materia----------------------
    @GetMapping(value = "/searchMaestroByMateria/{materia}")
    public Flux<MaestroDTO> buscarMaestroPorMateria(@PathVariable("materia") String materia){
        return this.maestroService.buscarMaestroPorMateria(materia);
    }

    //Actualizar Lista de Materias de Maestro
    @PutMapping("/updateMateriaMaestro/{id}/{materia}")
    private Mono<Maestro> updateMateriaDelMaestro(@PathVariable("id") String id, @PathVariable String materia) {
        return this.maestroService.updateMateriaDelMaestro(id, materia);
    }

    //Eliminar Materia de la Lista de Materias de Maestro
    @PutMapping("/removeMateriaMaestro/{id}/{materia}")
    private Mono<Maestro> deleteMateriaDelMaestro(@PathVariable("id") String id, @PathVariable String materia) {

        Maestro maestro = this.maestroRepository.findById(id).block(); //Obtener el maestro

        if (maestro.getMaterias() == null) //Si no hay materias a remover, sale
            return Mono.just(maestro);

        //Si tiene materias, eliminar la materia en especifico
        Boolean materiaBorrada = maestro.getMaterias().removeIf(m -> m.contains(materia));
        if (materiaBorrada) { //Si se borro la materia
            return this.maestroService.update(id, maestro)
                    .flatMap(maestro1 -> Mono.just(maestro1)).switchIfEmpty(Mono.empty());
        }

        return Mono.just(maestro);
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
            //Si el maestro no tiene materias, entonces retorna todas las materias del sistema
            List<String> materias = new Materias().getMateria(); //Obtener materias del sistema (Lista de String)

            //Crear una lista de materias y retornar en flux<Materia>
            List<Materia> materiaList = new ArrayList<Materia>();
            for(String materia: materias){
                materiaList.add(new Materia(materia));
            }
            return Flux.fromIterable(materiaList);
        }
    }

    //Obtener todas las Materias que tenga un Maestro
    @GetMapping("/allMateriasFromMaestro{idMaestro}")
    public Flux<Materia> allMateriasFromMaestro(@PathVariable("idMaestro") String idMaestro){
       try{
           List<String> materias= this.maestroService.findById(idMaestro).block().getMaterias();
           List<Materia> materiaList = new ArrayList<Materia>();
           for(String materia : materias){
               materiaList.add(new Materia(materia));
           }
           return Flux.fromIterable(materiaList);
       }catch (Exception e){
           return Flux.empty();
       }
    }

}
