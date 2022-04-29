package co.com.sofka.pruebaRetoFinal.models;

import co.com.sofka.pruebaRetoFinal.models.values.Materia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "clases")
@Getter @Setter @NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class Clase {

    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);

    //Atributos de Clase-----------------
    private Materia materia;
    private List<Horario> horarios;
    private Maestro maestro;
    //private List<Estudiante> estudiantes = new ArrayList<Estudiante>();
    //private Grupo grupo;

    //Constructor de Asignacion---------------
    public Clase(Materia materia, List<Horario> horarios, Maestro maestro) {
        this.materia = materia;
        this.horarios = horarios;
        this.maestro = maestro;
    }

    //Clase con lista de Estudiantes
   /* public Clase(Materia materia, List<Horario> horarios, Maestro maestro, List<Estudiante> estudiantes) {
        this.materia = materia;
        this.horarios = horarios;
        this.maestro = maestro;
        this.estudiantes = estudiantes;
    }

    //Clase con grupo
    public Clase(Materia materia, List<Horario> horarios, Maestro maestro, Grupo grupo) {
        this.materia = materia;
        this.horarios = horarios;
        this.maestro = maestro;
        this.estudiantes = new ArrayList<Estudiante>();
        this.grupo = grupo;
    }
*/
    //Otros Metodos------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clase clase = (Clase) o;
        return Objects.equals(id, clase.id) && Objects.equals(materia, clase.materia) && Objects.equals(horarios, clase.horarios) && Objects.equals(maestro, clase.maestro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, materia, horarios, maestro);
    }

    @Override
    public String toString() {
        return "Clase{" +
                "id='" + id + '\'' +
                ", materia=" + materia +
                ", horarios=" + horarios +
                ", maestro=" + maestro +
                '}';
    }
}
