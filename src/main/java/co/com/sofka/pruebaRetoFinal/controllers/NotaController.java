package co.com.sofka.pruebaRetoFinal.controllers;

import co.com.sofka.pruebaRetoFinal.models.Acudiente;
import co.com.sofka.pruebaRetoFinal.models.Nota;
import co.com.sofka.pruebaRetoFinal.services.Impl.NotaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
public class NotaController {

    @Autowired
    NotaServiceImpl notaService;

    //-----------------CRUD-----------------//
    //Guardar un Nota
    @PostMapping("/addNota")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<Nota> save(@RequestBody Nota nota) {
        return this.notaService.save(nota);
    }

    //Mostrar Todos los Notas
    @GetMapping(value = "/allNota")
    private Flux<Nota> findAll() {
        return this.notaService.findAll();
    }

    //Actualizar Nota
    @PutMapping("/updateNota/{id}")
    private Mono<Nota> update(@PathVariable("id") String id, @RequestBody Nota nota) {
        return this.notaService.update(id, nota)
                .flatMap(nota1 -> Mono.just(nota1)).switchIfEmpty(Mono.empty());
    }

    //Eliminar Nota
    @DeleteMapping("/removeNota/{id}")
    private Mono<Nota> delete(@PathVariable("id") String id) {
        return this.notaService.delete(id)
                .flatMap(nota1 -> Mono.just((nota1)).switchIfEmpty(Mono.empty()));

    }
    //-----------------CRUD-----------------//

}
