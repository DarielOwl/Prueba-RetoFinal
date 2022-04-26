package co.com.sofka.pruebaRetoFinal.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class Horario {

    private String horarioInicial;
    private String horarioFinal;
    private List<String> dias;

    public Horario(String horarioInicial, String horarioFinal, List<String> dias) {
        this.horarioInicial = horarioInicial;
        this.horarioFinal = horarioFinal;
        this.dias = dias;
    }
}
