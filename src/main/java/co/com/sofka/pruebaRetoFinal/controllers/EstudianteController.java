package co.com.sofka.pruebaRetoFinal.controllers;

import co.com.sofka.pruebaRetoFinal.DTOs.EstudianteDTO;
import co.com.sofka.pruebaRetoFinal.mappers.EstudianteMapper;
import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import co.com.sofka.pruebaRetoFinal.services.Impl.EstudianteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
public class EstudianteController {

    @Autowired
    EstudianteServiceImpl estudianteService;
    EstudianteMapper estudianteMapper = new EstudianteMapper();

    //-----------------CRUD-----------------//
    //Guardar un Estudiante
    @PostMapping("/addEstudiante")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<EstudianteDTO> save(@RequestBody EstudianteDTO estudianteDTO) {
        return this.estudianteService.save(estudianteMapper.createEstudiante(estudianteDTO)).thenReturn(estudianteDTO);
    }

    //Mostrar Todos los Estudiantes
    @GetMapping(value = "/allEstudiante")
    private Flux<EstudianteDTO> findAll() {
        return estudianteMapper.convertirEstudianteDTOs(this.estudianteService.findAll());
    }

    //Actualizar Estudiante
    @PutMapping("/updateEstudiante/{id}")
    private Mono<Estudiante> update(@PathVariable("id") String id, @RequestBody Estudiante estudiante) {
        return this.estudianteService.update(id, estudiante)
                .flatMap(estudiante1 -> Mono.just(estudiante1)).switchIfEmpty(Mono.empty());
    }

    //Eliminar Estudiante
    @DeleteMapping("/removeEstudiante/{id}")
    private Mono<Estudiante> delete(@PathVariable("id") String id) {
        return this.estudianteService.delete(id)
                .flatMap(estudiante1 -> Mono.just((estudiante1)).switchIfEmpty(Mono.empty()));

    }
    //-----------------CRUD-----------------//



}
