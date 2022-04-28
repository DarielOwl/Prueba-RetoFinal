package co.com.sofka.pruebaRetoFinal.controllers;

import co.com.sofka.pruebaRetoFinal.services.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@CrossOrigin(origins = "*")
@RestController
public class QAUtilitiesController {
    @Autowired
    GrupoServiceImpl grupoService = new GrupoServiceImpl();
    @Autowired
    EstudianteServiceImpl estudianteService = new EstudianteServiceImpl();
    @Autowired
    AcudienteServiceImpl acudienteService = new AcudienteServiceImpl();
    @Autowired
    MaestroServiceImpl maestroService = new MaestroServiceImpl();
    @Autowired
    NotaServiceImpl notaService = new NotaServiceImpl();
    @Autowired
    ClaseServiceImpl claseService = new ClaseServiceImpl();

    @GetMapping("/clearDB")
    public Mono<Void> limpiarBaseDeDatos(){
        try{
            return this.grupoService.deleteAll()
                    .then(this.estudianteService.deleteAll())
                    .then(this.acudienteService.deleteAll())
                    .then(this.maestroService.deleteAll())
                    .then(this.notaService.deleteAll())
                    .then(this.claseService.deleteAll());
        }catch (Exception e){
            return Mono.empty();
        }
    }
}
