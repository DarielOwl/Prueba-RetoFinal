package co.com.sofka.pruebaRetoFinal.models.values;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Materia {
    String nombreMateria;
    public Materia(String nombre) {
        this.nombreMateria = nombre;
    }
}
