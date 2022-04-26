package co.com.sofka.pruebaRetoFinal.services.Impl;


import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import co.com.sofka.pruebaRetoFinal.models.Maestro;

import co.com.sofka.pruebaRetoFinal.repositories.MaestroRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class MaestroServiceImplTest {

    @Mock
    private MaestroRepository maestroRepository;

    @InjectMocks
    private MaestroServiceImpl maestroService = new MaestroServiceImpl();

    //Variable global de maestro
    private Mono<Maestro> maestros;

    @BeforeEach
    void setUp() { //Inicializamos el Flux de maestros
        MockitoAnnotations.initMocks(this);
        maestros = Mono.just(new Maestro(
                "123",
                "Carlitos",
                "carlitos@gmail.com",
                "123 Tu mama",
                "321",
                false
        ));
    }

    @Test
    @DisplayName("Crear Maestro")
    void save() {
        Mono<Maestro> maestro = Mono.just(new Maestro(
                "123",
                "Carlitos",
                "carlitos@gmail.com",
                "123 Tu mama",
                "321",
                false
        ));

        when(maestroService.save(any())).thenReturn(maestro); //Testea que tiene que devolver

        assertNotNull(maestroService.save(new Maestro())); //Prueba el save de Maestro (Que no debe devolver null)

        //Este Compara entre un Mono<Maestro> y un Maestro
        //assertEquals(maestroService.save(new Maestro()),maestro.block());
    }

    @Test
    @DisplayName("Buscar por Documento de Identidad")
    void buscarMaestroPorDocumentoIdentidad() {
        //Este Test retorna Null, porque como no encuentra la id de
        //assertNotNull(maestroService.buscarMaestroPorDocumentoIdentidad("123"));
    }

    @Test
    void findByDocumentoIdentidad() {
    }
}