package co.com.sofka.pruebaRetoFinal.services.Impl;

import co.com.sofka.pruebaRetoFinal.models.Nota;
import co.com.sofka.pruebaRetoFinal.repositories.NotaRepository;
import co.com.sofka.pruebaRetoFinal.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class NotaServiceImpl implements NotaService {

    @Autowired
    NotaRepository notaRepository;

    //-----------------CRUD-----------------//
    @Override
    public Mono<Nota> save(Nota nota) {
        return notaRepository.save(nota);
    }

    @Override
    public Flux<Nota> findAll() {
        return this.notaRepository.findAll();
    }

    @Override
    public Mono<Nota> update(String id, Nota nota) {
        return this.notaRepository.findById(id)
                .flatMap(notaUpdate -> {
                    nota.setId(id);
                    return save(nota);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Nota> delete(String id) {
        return this.notaRepository
                .findById(id)
                .flatMap(notaDelete -> this.notaRepository
                        .deleteById(notaDelete.getId())
                        .thenReturn(notaDelete));
    }

    @Override
    public Mono<Void> deleteAll() {return this.notaRepository.deleteAll();}
    //-----------------CRUD-----------------//

}
