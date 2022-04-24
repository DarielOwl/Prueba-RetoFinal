package co.com.sofka.pruebaRetoFinal.services.Impl;

import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import co.com.sofka.pruebaRetoFinal.repositories.EstudianteRepository;
import co.com.sofka.pruebaRetoFinal.services.EstudianteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EstudianteServiceImplTest {
    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteServiceImpl estudianteService;

    private Flux<Estudiante> estudianteFlux;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        estudianteFlux = Flux.just(new Estudiante(
                "6-A",
                "52369256",
                "Federico",
                6,
                21,
                true));
    }

    @Test
    @DisplayName("findAll Estudiantes")
    void findAll() {
        when(estudianteService.findAll()).thenReturn(estudianteFlux);
        assertNotNull(estudianteService.findAll());
    }

    @Test
    @DisplayName("Crear nuevo Estudiante")
    void save() {
        Mono<Estudiante> estudianteMono = Mono.just(new Estudiante(
                "6-A",
                "52369256",
                "Federico",
                6,
                21,
                true));
        when(estudianteService.save(any())).thenReturn(estudianteMono);
        assertNotNull(estudianteService.save(new Estudiante()));
    }

    /*@Test
    void update() {
        Mono<Estudiante> estudianteMono = Mono.just(new Estudiante(
                "7bb8fada-e",
                "1",
                "50686191",
                "Dariel de Sosa",
                6,
                22,
                true));
        when(estudianteService.update("7bb8fada-e",estudianteMono.block()).thenReturn(estudianteMono));
    }*/

    @Test
    void delete() {
    }
}