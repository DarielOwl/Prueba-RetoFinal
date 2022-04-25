package co.com.sofka.pruebaRetoFinal.controllers;

import co.com.sofka.pruebaRetoFinal.mappers.AcudienteMapper;
import co.com.sofka.pruebaRetoFinal.models.Acudiente;
import co.com.sofka.pruebaRetoFinal.repositories.AcudienteRepository;
import co.com.sofka.pruebaRetoFinal.services.AcudienteService;
import co.com.sofka.pruebaRetoFinal.services.Impl.AcudienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(AcudienteController.class)
class AcudienteControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AcudienteServiceImpl service;

    @Test
    void buscarMaestroPorDocumentoIdentidad() {
            Mono<Acudiente> acudienteMono=Mono.just(new Acudiente(
                    "Andrea Pereira",
                    "777777777",
                    "09999998",
                    "Barrio Pirata2",
                    "test@gmail.com"));
            when(service.findByDocumentoIdentidad("777777777")).thenReturn(acudienteMono);

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