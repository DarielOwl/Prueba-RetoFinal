package co.com.sofka.pruebaRetoFinal.controllers;

import co.com.sofka.pruebaRetoFinal.DTOs.EstudianteDTO;
import co.com.sofka.pruebaRetoFinal.DTOs.MaestroDTO;
import co.com.sofka.pruebaRetoFinal.models.Clase;
import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import co.com.sofka.pruebaRetoFinal.services.Impl.ClaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
public class ClaseController {

    @Autowired
    ClaseServiceImpl claseService;

    //-----------------CRUD-----------------//
    //Guardar una Clase
    @PostMapping("/addClase")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<Clase> save(@RequestBody Clase clase) {
        return this.claseService.save(clase);
    }

    //Mostrar Todos las Clases
    @GetMapping(value = "/allClase")
    private Flux<Clase> findAll() {
        return this.claseService.findAll();
    }
    //-----------------CRUD-----------------//

}
