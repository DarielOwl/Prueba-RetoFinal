package co.com.sofka.pruebaRetoFinal.controllers;

import co.com.sofka.pruebaRetoFinal.DTOs.EstudianteDTO;
import co.com.sofka.pruebaRetoFinal.mappers.EstudianteMapper;
import co.com.sofka.pruebaRetoFinal.models.Acudiente;
import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import co.com.sofka.pruebaRetoFinal.services.Impl.AcudienteServiceImpl;
import co.com.sofka.pruebaRetoFinal.services.Impl.EstudianteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class EstudianteController {

    @Autowired
    EstudianteServiceImpl estudianteService;
    @Autowired
    AcudienteServiceImpl acudienteService = new AcudienteServiceImpl();
    EstudianteMapper estudianteMapper = new EstudianteMapper();

    //-----------------CRUD-----------------//
    //Guardar un Estudiante
    @PostMapping("/addEstudiante")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<EstudianteDTO> save(@RequestBody EstudianteDTO estudianteDTO) {
        try{
            Acudiente acudiente = acudienteService.findByDocumentoIdentidad(estudianteDTO.getDocumentoIdentidadAcudiente()).block();
            acudiente.getEstudiantes().add(estudianteMapper.createEstudiante(estudianteDTO));
            return this.acudienteService.save(acudiente)
                    .then(this.estudianteService.save(estudianteMapper.createEstudiante(estudianteDTO)))
                    .thenReturn(estudianteDTO);
        }catch (Exception e){
            Acudiente acudiente = acudienteService.findByDocumentoIdentidad(estudianteDTO.getDocumentoIdentidadAcudiente()).block();
            List<Estudiante> estudianteList = new ArrayList<Estudiante>();
            estudianteList.add(estudianteMapper.createEstudiante(estudianteDTO));
            acudiente.setEstudiantes(estudianteList);
            return this.acudienteService.save(acudiente)
                    .then(this.estudianteService.save(estudianteMapper.createEstudiante(estudianteDTO)))
                    .thenReturn(estudianteDTO);
        }

    }

    //Mostrar Todos los Estudiantes
    @GetMapping(value = "/allEstudiante")
    private Flux<Estudiante> findAll() {
        return this.estudianteService.findAll();
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
    @GetMapping("/allEstudiantesSinGrupo")
    private Flux<Estudiante> findAllEstudiantesSinGrupo(){
        try{
            return this.estudianteService.findAll().filter(e -> e.getGrupo()==null || e.getGrupo().equalsIgnoreCase(""));
        }catch (Exception e){
            return Flux.empty();
        }
    }

    @GetMapping("/allEstudiantesSinGrupoByGrado/{grado}")
    private Flux<Estudiante> findAllEstudiantesSinGrupoByGrado(@PathVariable("grado") Integer grado){
        try{
            return this.estudianteService.findAll()
                    .filter(e -> e.getGrado().equals(grado))
                    .filter(e2 -> e2.getGrupo()==null || e2.getGrupo().equalsIgnoreCase(""));
            //return this.estudianteService.findAll().filter(e -> e.getGrupo()==null || e.getGrupo().equalsIgnoreCase(""));
        }catch (Exception e){
            return Flux.empty();
        }
    }

    @GetMapping("/allEstudiantesFromAcudiente/{documentoIdentidadAcudiente}")
    private Flux<Estudiante> allEstudiantesFromAcudiente(@PathVariable("documentoIdentidadAcudiente") String documentoIdentidadAcudiente){
        try{
            return Flux.fromIterable(this.acudienteService.
                    findByDocumentoIdentidad(documentoIdentidadAcudiente)
                    .block()
                    .getEstudiantes());
        }catch (Exception e){
            return Flux.empty();
        }
    }



    //-----------------CRUD-----------------//



}
