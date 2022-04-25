package co.com.sofka.pruebaRetoFinal.services.Impl;

import co.com.sofka.pruebaRetoFinal.DTOs.AcudienteDTO;
import co.com.sofka.pruebaRetoFinal.mappers.AcudienteMapper;
import co.com.sofka.pruebaRetoFinal.models.Acudiente;
import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import co.com.sofka.pruebaRetoFinal.repositories.AcudienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AcudienteServiceImplTest {
    @Mock
    private AcudienteRepository acudienteRepository;
    private AcudienteMapper acudienteMapper = new AcudienteMapper();

    @InjectMocks
    private AcudienteServiceImpl acudienteService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("save Acudiente")
    void save() {
        Mono<Acudiente> acudienteMono = Mono.just(acudienteMapper.createAcudiente(new AcudienteDTO(
                "6-A",
                "52369256",
                "Federico",
                "Calle13",
                null,
                "test@gmail.com")));
        when(acudienteService.save(any())).thenReturn(acudienteMono);
        assertNotNull(acudienteService.save(new Acudiente()));
    }
}