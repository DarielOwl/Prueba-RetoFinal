package co.com.sofka.pruebaRetoFinal.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class Horario {

    //Atributos Horarios------------
    private String horarioInicial;
    private String horarioFinal;
    private List<String> dias;

    //Constructor de Asignacion--------------
    public Horario(String horarioInicial, String horarioFinal, List<String> dias) {
        this.horarioInicial = horarioInicial;
        this.horarioFinal = horarioFinal;
        this.dias = dias;
    }

    //Otros Metodos-------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horario horario = (Horario) o;
        return Objects.equals(horarioInicial, horario.horarioInicial) && Objects.equals(horarioFinal, horario.horarioFinal) && Objects.equals(dias, horario.dias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horarioInicial, horarioFinal, dias);
    }

    @Override
    public String toString() {
        return "Horario{" +
                "horarioInicial='" + horarioInicial + '\'' +
                ", horarioFinal='" + horarioFinal + '\'' +
                ", dias=" + dias +
                '}';
    }

}
