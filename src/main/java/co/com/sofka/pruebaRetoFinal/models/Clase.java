package co.com.sofka.pruebaRetoFinal.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "clases")
@Getter @Setter @NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class Clase {

    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);

    //Atributos de Clase-----------------
    private String nombreMateria;
    private String nombreProfesor;
    private List<Horario> horario;

    //Constructor de Asignacion---------------
    public Clase(String nombreMateria, String nombreProfesor, List<Horario> horario) {
        this.nombreMateria = nombreMateria;
        this.nombreProfesor = nombreProfesor;
        this.horario = horario;
    }

    //Otros Metodos----------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clase clase = (Clase) o;
        return Objects.equals(id, clase.id) && Objects.equals(nombreMateria, clase.nombreMateria) && Objects.equals(nombreProfesor, clase.nombreProfesor) && Objects.equals(horario, clase.horario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreMateria, nombreProfesor, horario);
    }

    @Override
    public String toString() {
        return "Clase{" +
                "id='" + id + '\'' +
                ", nombreMateria='" + nombreMateria + '\'' +
                ", nombreProfesor='" + nombreProfesor + '\'' +
                ", horario=" + horario +
                '}';
    }
}
