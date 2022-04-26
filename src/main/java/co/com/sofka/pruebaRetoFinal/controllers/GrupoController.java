package co.com.sofka.pruebaRetoFinal.controllers;

import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import co.com.sofka.pruebaRetoFinal.models.Grupo;
import co.com.sofka.pruebaRetoFinal.models.Maestro;
import co.com.sofka.pruebaRetoFinal.services.Impl.GrupoServiceImpl;
import co.com.sofka.pruebaRetoFinal.services.Impl.MaestroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class GrupoController {

    @Autowired
    GrupoServiceImpl grupoService;

    //-----------------CRUD-----------------//
    //Guardar un Grupo
    @PostMapping("/addGrupo")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<Grupo> save(@RequestBody Grupo grupo) {
        return this.grupoService.save(new Grupo(
                grupo.getGrado(),
                grupo.getCurso(),
                grupo.getNombre()));
    }

    //Mostrar Todos los Grupos
    @GetMapping(value = "/allGrupo")
    private Flux<Grupo> findAll() {
        return this.grupoService.findAll();
    }

    //Actualizar Grupo
    @PutMapping("/updateGrupo/{id}")
    private Mono<Grupo> update(@PathVariable("id") String id, @RequestBody Grupo grupo) {
        return this.grupoService.update(id, grupo)
                .flatMap(grupo1 -> Mono.just(grupo1)).switchIfEmpty(Mono.empty());
    }

    //Eliminar Grupo
    @DeleteMapping("/removeGrupo/{id}")
    private Mono<Grupo> delete(@PathVariable("id") String id) {
        return this.grupoService.delete(id)
                .flatMap(grupo1 -> Mono.just((grupo1)).switchIfEmpty(Mono.empty()));

    }

    //Obtener grupos con estado TRUE y con todos sus datos incluida la lista de estudiantes.
    @GetMapping("/allGruposActivos")
    private Flux<Grupo> allGruposActivos(){
        return this.grupoService.findAll().filter(g -> g.isEstado()!=false);
    }

    //Borrar grupo de forma lógica mediante su ID
    @PutMapping("/deleteGrupo/{id}")
    private Mono<Grupo> logicDeleteGrupoById(@PathVariable("id") String id){
        Grupo grupo = this.grupoService.findById(id).block();
        grupo.setEstado(false);
        return this.grupoService.save(grupo);
    }

    //Eliminar estudiante de un grupo mediante ID estudiante e ID grupo.
    @PutMapping("/deleteEstudianteFromGrupo/{idEstudiante}/{idGrupo}")
    private Mono<Grupo> deleteEstudianteFromGrupo(@PathVariable("idEstudiante") String idEstudiante,@PathVariable("idGrupo") String idGrupo){
        Grupo grupo = this.grupoService.findById(idGrupo).block();
        List<Estudiante> estudianteList = grupo.getEstudiantes();
        Iterator iterator = estudianteList.iterator();
        while(iterator.hasNext()){
            Estudiante e =(Estudiante) iterator.next();
            if(e.getId().equalsIgnoreCase(idEstudiante)){
                iterator.remove();
            }
        }
        return this.grupoService.update(idGrupo,grupo);
    }

    //Agregar un estudiante
    //-----------------CRUD-----------------//

}
