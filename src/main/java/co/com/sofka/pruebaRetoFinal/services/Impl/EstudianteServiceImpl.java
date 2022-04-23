package co.com.sofka.pruebaRetoFinal.services.Impl;

import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import co.com.sofka.pruebaRetoFinal.repositories.EstudianteRepository;
import co.com.sofka.pruebaRetoFinal.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    EstudianteRepository estudianteRepository;

    //-----------------CRUD-----------------//
    @Override
    public Mono<Estudiante> save(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Flux<Estudiante> findAll() {
        return this.estudianteRepository.findAll();
    }

    @Override
    public Mono<Estudiante> update(String id, Estudiante estudiante) {
        return this.estudianteRepository.findById(id)
                .flatMap(clienteUpdate -> {
                    estudiante.setId(id);
                    return save(estudiante);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Estudiante> delete(String id) {
        return this.estudianteRepository
                .findById(id)
                .flatMap(estudianteDelete -> this.estudianteRepository.deleteById(estudianteDelete.getId()).thenReturn(estudianteDelete));
    }
    //-----------------CRUD-----------------//
}
