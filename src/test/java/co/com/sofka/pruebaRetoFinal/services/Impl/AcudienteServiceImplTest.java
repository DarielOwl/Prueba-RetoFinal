package co.com.sofka.pruebaRetoFinal.services.Impl;

import co.com.sofka.pruebaRetoFinal.DTOs.AcudienteDTO;
import co.com.sofka.pruebaRetoFinal.controllers.AcudienteController;
import co.com.sofka.pruebaRetoFinal.mappers.AcudienteMapper;
import co.com.sofka.pruebaRetoFinal.models.Acudiente;
import co.com.sofka.pruebaRetoFinal.repositories.AcudienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(AcudienteController.class)
class AcudienteServiceImplTest {
    @Autowired
    private WebTestClient webTestClient;

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
                "nombreAcudiente",
                "52369256",
                "0995658744",
                "Calle13",
                null,
                "test@gmail.com")));
        when(acudienteService.save(any())).thenReturn(acudienteMono);
        assertNotNull(acudienteService.save(new Acudiente()));
    }

    @Test
    public void getAcudienteByDocumentoIdentidadTest(){
        Mono<Acudiente> acudienteMono=Mono.just(new Acudiente(
                "Andrea Pereira",
                "777777777",
                "09999998",
                "Barrio Pirata2",
                "test@gmail.com"));
        when(acudienteService.findByDocumentoIdentidad("777777777")).thenReturn(acudienteMono);

        Flux<Acudiente> responseBody = webTestClient.get().uri("/searchAcudiente/777777777")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Acudiente.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(a->a.getNombre().equals("nombreAcudiente"))
                .verifyComplete();
    }
}