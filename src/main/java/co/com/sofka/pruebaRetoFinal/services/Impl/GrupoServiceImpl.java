package co.com.sofka.pruebaRetoFinal.services.Impl;

import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import co.com.sofka.pruebaRetoFinal.models.Grupo;
import co.com.sofka.pruebaRetoFinal.repositories.EstudianteRepository;
import co.com.sofka.pruebaRetoFinal.repositories.GrupoRepository;
import co.com.sofka.pruebaRetoFinal.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    GrupoRepository grupoRepository;

    @Autowired
    EstudianteRepository estudianteRepository;

    //-----------------CRUD-----------------//
    @Override
    public Mono<Grupo> save(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Override
    public Flux<Grupo> findAll() {
        return this.grupoRepository.findAll();
    }

    @Override
    public Mono<Grupo> update(String id, Grupo grupo) {
        return this.grupoRepository.findById(id)
                .flatMap(grupoUpdate -> {
                    grupo.setId(id);
                    return save(grupo);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Grupo> delete(String id) {
        return this.grupoRepository
                .findById(id)
                .flatMap(grupoDelete -> this.grupoRepository
                        .deleteById(grupoDelete.getId())
                        .thenReturn(grupoDelete));
    }

    @Override
    public Mono<Grupo> findById(String id){
        return this.grupoRepository.findById(id);
    }
    //-----------------CRUD-----------------//

}
