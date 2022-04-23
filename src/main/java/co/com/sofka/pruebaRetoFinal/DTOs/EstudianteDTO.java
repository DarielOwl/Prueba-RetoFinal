package co.com.sofka.pruebaRetoFinal.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class EstudianteDTO {

    //Atributos Estudiante-----------
    String grupo; //-> Esto seria GrupoID
    String documentoIdentidad;
    String nombre;
    Integer grado;
    Integer edad;
    Boolean estado;

    //Constructor de Asignacion--------------
    public EstudianteDTO(String grupo, String documentoIdentidad, String nombre, Integer grado, Integer edad, Boolean estado) {
        this.grupo = grupo;
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
        this.grado = grado;
        this.edad = edad;
        this.estado = estado;
    }

    //Otros Metodos-------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstudianteDTO that = (EstudianteDTO) o;
        return Objects.equals(grupo, that.grupo) && Objects.equals(documentoIdentidad, that.documentoIdentidad) && Objects.equals(nombre, that.nombre) && Objects.equals(grado, that.grado) && Objects.equals(edad, that.edad) && Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grupo, documentoIdentidad, nombre, grado, edad, estado);
    }

    @Override
    public String toString() {
        return "EstudianteDTO{" +
                "grupo='" + grupo + '\'' +
                ", documentoIdentidad='" + documentoIdentidad + '\'' +
                ", nombre='" + nombre + '\'' +
                ", grado=" + grado +
                ", edad=" + edad +
                ", estado=" + estado +
                '}';
    }

}
