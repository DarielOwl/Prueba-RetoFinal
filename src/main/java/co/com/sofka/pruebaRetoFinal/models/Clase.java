package co.com.sofka.pruebaRetoFinal.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "materias")
@Getter
@Setter
@NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class Clase {
    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);
    private String idProfesor;
    private String nombreMateria;
    private String nombreProfesor;
    private Horario horario;

    public Clase(String idProfesor, String nombreMateria, String nombreProfesor, Horario horario) {
        this.idProfesor = idProfesor;
        this.nombreMateria = nombreMateria;
        this.nombreProfesor = nombreProfesor;
        this.horario = horario;
    }
}
