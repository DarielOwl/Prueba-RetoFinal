package co.com.sofka.pruebaRetoFinal.services.Impl;

import co.com.sofka.pruebaRetoFinal.models.Acudiente;
import co.com.sofka.pruebaRetoFinal.repositories.AcudienteRepository;
import co.com.sofka.pruebaRetoFinal.services.AcudienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AcudienteServiceImpl implements AcudienteService {

    @Autowired
    AcudienteRepository acudienteRepository;

    //-----------------CRUD-----------------//
    @Override
    public Mono<Acudiente> save(Acudiente acudiente) {
        return acudienteRepository.save(acudiente);
    }

    @Override
    public Flux<Acudiente> findAll() {
        return this.acudienteRepository.findAll();
    }

    @Override
    public Mono<Acudiente> update(String id, Acudiente acudiente) {
        return this.acudienteRepository.findById(id)
                .flatMap(acudienteUpdate -> {
                    acudiente.setId(id);
                    return save(acudiente);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Acudiente> delete(String id) {
        return this.acudienteRepository
                .findById(id)
                .flatMap(acudienteDelete -> this.acudienteRepository
                        .deleteById(acudienteDelete.getId())
                        .thenReturn(acudienteDelete));
    }

    @Override
    public Mono<Acudiente> findByDocumentoIdentidad(String documentoIdentidad) {
        return this.acudienteRepository.findByDocumentoIdentidad(documentoIdentidad);
    }

    @Override
    public Mono<Void> deleteAll() {return this.acudienteRepository.deleteAll();}
    //-----------------CRUD-----------------//

}
