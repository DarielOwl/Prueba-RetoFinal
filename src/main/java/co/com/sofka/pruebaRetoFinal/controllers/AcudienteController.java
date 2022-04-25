package co.com.sofka.pruebaRetoFinal.controllers;

import co.com.sofka.pruebaRetoFinal.models.Acudiente;
import co.com.sofka.pruebaRetoFinal.models.Maestro;
import co.com.sofka.pruebaRetoFinal.services.Impl.AcudienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
public class AcudienteController {

    @Autowired
    AcudienteServiceImpl acudienteService;

    //-----------------CRUD-----------------//
    //Guardar un Acudiente
    @PostMapping("/addAcudiente")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<Acudiente> save(@RequestBody Acudiente acudiente) {
        return this.acudienteService.save(acudiente);
    }

    //Mostrar Todos los Acudientes
    @GetMapping(value = "/allAcudiente")
    private Flux<Acudiente> findAll() {
        return this.acudienteService.findAll();
    }

    //Actualizar Maestro
    @PutMapping("/updateAcudiente/{id}")
    private Mono<Acudiente> update(@PathVariable("id") String id, @RequestBody Acudiente acudiente) {
        return this.acudienteService.update(id, acudiente)
                .flatMap(acudiente1 -> Mono.just(acudiente1)).switchIfEmpty(Mono.empty());
    }

    //Eliminar Maestro
    @DeleteMapping("/removeAcudiente/{id}")
    private Mono<Acudiente> delete(@PathVariable("id") String id) {
        return this.acudienteService.delete(id)
                .flatMap(acudiente1 -> Mono.just((acudiente1)).switchIfEmpty(Mono.empty()));

    }
    @GetMapping(value = "/searchAcudiente/{documentoIdentidad}")
    public Mono<Acudiente> buscarMaestroPorDocumentoIdentidad(@PathVariable("documentoIdentidad") String documentoIdentidad){
        return this.acudienteService.findByDocumentoIdentidad(documentoIdentidad);
    }
    //-----------------CRUD-----------------//

}
