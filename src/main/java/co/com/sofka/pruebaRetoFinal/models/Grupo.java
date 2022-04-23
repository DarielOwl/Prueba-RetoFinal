package co.com.sofka.pruebaRetoFinal.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "grupos")
@Getter @Setter @NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class Grupo {

    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);

    //Atributos Grupo-----------
    private String maestro; //-> Esto seria maestroID
    private String horarios;
    private Integer grado;
    private Integer maximoCupos;
    private List<Estudiante> estudiantes;
    private List<Nota> notas;

    //Constructor de Asignacion--------------
    public Grupo(String maestro, String horarios, Integer grado, Integer maximoCupos, List<Estudiante> estudiantes, List<Nota> notas) {
        this.maestro = maestro;
        this.horarios = horarios;
        this.grado = grado;
        this.maximoCupos = maximoCupos;
        this.estudiantes = estudiantes;
        this.notas = notas;
    }

    //Otros Metodos-------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grupo grupo = (Grupo) o;
        return Objects.equals(id, grupo.id) && Objects.equals(maestro, grupo.maestro) && Objects.equals(horarios, grupo.horarios) && Objects.equals(grado, grupo.grado) && Objects.equals(maximoCupos, grupo.maximoCupos) && Objects.equals(estudiantes, grupo.estudiantes) && Objects.equals(notas, grupo.notas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maestro, horarios, grado, maximoCupos, estudiantes, notas);
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id='" + id + '\'' +
                ", maestro='" + maestro + '\'' +
                ", horarios='" + horarios + '\'' +
                ", grado=" + grado +
                ", maximoCupos=" + maximoCupos +
                ", estudiantes=" + estudiantes +
                ", notas=" + notas +
                '}';
    }

}
