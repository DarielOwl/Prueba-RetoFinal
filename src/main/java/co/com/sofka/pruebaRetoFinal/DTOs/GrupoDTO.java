package co.com.sofka.pruebaRetoFinal.DTOs;

import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import co.com.sofka.pruebaRetoFinal.models.Nota;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Objects;


@Getter @Setter @NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class GrupoDTO {

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

    //Constructor de Asignacion--------------
    public GrupoDTO(String maestro, String horarios, Integer grado, Integer maximoCupos, List<Estudiante> estudiantes, List<Nota> notas, String curso) {
        this.maestro = maestro;
        this.horarios = horarios;
        this.grado = grado;
        this.maximoCupos = maximoCupos;
        this.estudiantes = estudiantes;
        this.notas = notas;
        this.curso = curso;
        this.nombre = nombre;
        this.estado = true;
    }

    public GrupoDTO(Integer grado, String curso, String nombre) {
        this.grado = grado;
        this.curso = curso;
        this.nombre = nombre;
        this.estado=true;
    }

    //Otros Metodos-------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoDTO grupoDTO = (GrupoDTO) o;
        return Objects.equals(maestro, grupoDTO.maestro) && Objects.equals(horarios, grupoDTO.horarios) && Objects.equals(grado, grupoDTO.grado) && Objects.equals(maximoCupos, grupoDTO.maximoCupos) && Objects.equals(estudiantes, grupoDTO.estudiantes) && Objects.equals(notas, grupoDTO.notas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maestro, horarios, grado, maximoCupos, estudiantes, notas);
    }

    @Override
    public String toString() {
        return "GrupoDTO{" +
                "maestro='" + maestro + '\'' +
                ", horarios='" + horarios + '\'' +
                ", grado=" + grado +
                ", maximoCupos=" + maximoCupos +
                ", estudiantes=" + estudiantes +
                ", notas=" + notas +
                '}';
    }

}
