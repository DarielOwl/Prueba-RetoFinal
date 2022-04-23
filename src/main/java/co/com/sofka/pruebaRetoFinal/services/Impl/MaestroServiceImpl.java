package co.com.sofka.pruebaRetoFinal.services.Impl;

import co.com.sofka.pruebaRetoFinal.repositories.MaestroRepository;
import co.com.sofka.pruebaRetoFinal.services.MaestroService;
import org.springframework.beans.factory.annotation.Autowired;

public class MaestroServiceImpl implements MaestroService {

    @Autowired
    MaestroRepository maestroRepository; //Instancia del repositorio Estudiante

}
