package co.com.sofka.pruebaRetoFinal.models.values;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Materias {
    private List<String> materia = new ArrayList<>();

    public Materias() {
        materia.add(("Trigonometria"));
        materia.add(("Filosofía"));
        materia.add(("Español y Literatura"));
        materia.add(("Inglés"));
        materia.add(("Política y Economía"));
        materia.add(("Religión"));
        materia.add(("Física"));
        materia.add(("Química"));
    }
}