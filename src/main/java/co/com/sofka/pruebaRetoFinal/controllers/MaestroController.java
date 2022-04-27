package co.com.sofka.pruebaRetoFinal.controllers;

import co.com.sofka.pruebaRetoFinal.DTOs.EstudianteDTO;
import co.com.sofka.pruebaRetoFinal.DTOs.MaestroDTO;
import co.com.sofka.pruebaRetoFinal.mappers.EstudianteMapper;
import co.com.sofka.pruebaRetoFinal.mappers.MaestroMapper;
import co.com.sofka.pruebaRetoFinal.models.Maestro;
import co.com.sofka.pruebaRetoFinal.services.Impl.MaestroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    private Mono<Maestro> save(@RequestBody Maestro maestro) {
        return this.maestroService.save(maestro);
    }

    //Mostrar Todos los Maestros
    @GetMapping(value = "/allMaestro")
    private Flux<Maestro> findAll() {
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

    //Actualizar Lista de Materias de Maestro
    @PutMapping("/updateMateriaMaestro/{id}/{materia}")
    private Mono<Maestro> updateMateriaDelMaestro(@PathVariable("id") String id, @PathVariable String materia) {

        return this.maestroService.updateMateriaDelMaestro(id, materia);
                //.flatMap(maestro1 -> Mono.just(maestro1)).switchIfEmpty(Mono.empty());

    }


}
