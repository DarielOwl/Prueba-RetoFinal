package co.com.sofka.pruebaRetoFinal.controllers;

import co.com.sofka.pruebaRetoFinal.services.Impl.MaestroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class MaestroController {

    @Autowired
    MaestroServiceImpl maestroService;
}
