package co.com.sofka.pruebaRetoFinal.services.Impl;


import co.com.sofka.pruebaRetoFinal.DTOs.MaestroDTO;
import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import co.com.sofka.pruebaRetoFinal.models.Maestro;

import co.com.sofka.pruebaRetoFinal.repositories.MaestroRepository;

import co.com.sofka.pruebaRetoFinal.services.MaestroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes ={Maestro.class, MaestroDTO.class, MaestroService.class})
class MaestroServiceImplTest {



    @Autowired
    private WebTestClient webTestClient;

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

    //Este test tambien anda
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

        Mono<Maestro> resultado = maestroService.save(maestro.block());

        Assertions.assertEquals("123",resultado.block().getDocumentoIdentidad());
        Assertions.assertEquals("Carlitos",resultado.block().getNombre());
        Assertions.assertEquals("carlitos@gmail.com",resultado.block().getCorreo());
        Assertions.assertEquals("123 Tu mama",resultado.block().getDireccion());
        Assertions.assertEquals("321",resultado.block().getCelular());
        Assertions.assertEquals(false,resultado.block().getEstado());
    }

    //Este Test anda
    @Test
    @DisplayName("Buscar por Documento de Identidad")
    void buscarMaestroPorDocumentoIdentidad() {

        //Crear OBJ para test buscarMaestroPorDocumentoIdentidad
        Maestro maestro = new Maestro("123",
                "Henry",
                "henry@gmail.com",
                "andres cheveste 430",
                "099",
                true
        );
        Mono<Maestro> maestroMono = Mono.just(maestro);

        Mockito.when(maestroRepository.findByDocumentoIdentidad("123")).thenReturn(maestroMono);

        Mono<Maestro> resultado = maestroService.findByDocumentoIdentidad("123");

        Assertions.assertEquals("123",resultado.block().getDocumentoIdentidad());
        Assertions.assertEquals("Henry",resultado.block().getNombre());
        Assertions.assertEquals("henry@gmail.com",resultado.block().getCorreo());
        Assertions.assertEquals("andres cheveste 430",resultado.block().getDireccion());
        Assertions.assertEquals("099",resultado.block().getCelular());
        Assertions.assertEquals(true,resultado.block().getEstado());
    }

    @Test
    void findByDocumentoIdentidad() {
    }
}