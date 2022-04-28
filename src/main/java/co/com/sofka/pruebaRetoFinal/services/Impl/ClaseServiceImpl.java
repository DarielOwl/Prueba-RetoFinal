package co.com.sofka.pruebaRetoFinal.services.Impl;

import co.com.sofka.pruebaRetoFinal.models.Clase;
import co.com.sofka.pruebaRetoFinal.repositories.ClaseRepository;
import co.com.sofka.pruebaRetoFinal.services.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClaseServiceImpl implements ClaseService {

    @Autowired
    ClaseRepository claseRepository;

    //-----------------CRUD-----------------//
    @Override
    public Mono<Clase> save(Clase clase) {
        return claseRepository.save(clase);
    }

    @Override
    public Flux<Clase> findAll() {
        return claseRepository.findAll();
    }

    @Override
    public Mono<Void> deleteAll() {return this.claseRepository.deleteAll();}
    //-----------------CRUD-----------------//





}
