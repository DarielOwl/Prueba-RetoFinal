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
    private String id = UUID.randomUUID().toString().substring(0, 5);

    //Atributos Grupo-----------
    private String maestro; //-> Esto seria maestroID
    private String horarios;
    private Integer grado;
    private Integer maximoCupos;
    private List<Estudiante> estudiantes;
    private List<Nota> notas;

    private String curso;
    private String nombre;
    private boolean estado;

    /*TODO: Borrar horarios, maximoCupos y notas*/
    //Horarios y Materias (o sea una Lista de Clases)
    List<Clase> clases;

    //Constructor de Asignacion--------------

    public Grupo(String maestro, String horarios, Integer grado, Integer maximoCupos, List<Estudiante> estudiantes, List<Nota> notas, String curso, String nombre) {
        this.maestro = maestro;
        this.horarios = horarios;
        this.grado = grado;
        this.maximoCupos = maximoCupos;
        this.estudiantes = estudiantes;
        this.notas = notas;
        this.curso = curso;
        this.nombre = nombre;
        this.estado=true;
    }
    public Grupo(Integer grado, String curso, String nombre) {
        this.grado = grado;
        this.curso = curso;
        this.nombre = nombre;
        this.estado = true;
    }

    public Grupo(String maestro, Integer grado, List<Estudiante> estudiantes, String curso, String nombre, boolean estado, List<Clase> clases) {
        this.maestro = maestro; //Pepe
        this.grado = grado; //8
        this.estudiantes = estudiantes; //carlitox
        this.curso = curso; //E
        this.nombre = nombre; //8E (grado+curso)
        this.estado = true;
        this.clases = clases; //Horarios, materia y profesor
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
