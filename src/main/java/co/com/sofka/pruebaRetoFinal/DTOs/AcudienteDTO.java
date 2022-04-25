package co.com.sofka.pruebaRetoFinal.DTOs;

import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class AcudienteDTO {

    //Atributos Acudiente-----------
    private String nombre;
    private String documentoIdentidad;
    private String celular;
    private String direccion;
    private List<Estudiante> estudiantes; // -> el Acudiente puede tener varios hijos
    private String correo;

    //Constructor de Asignacion--------------
    public AcudienteDTO(String nombre, String documentoIdentidad, String celular, String direccion, List<Estudiante> estudiantes) {
        this.nombre = nombre;
        this.documentoIdentidad = documentoIdentidad;
        this.celular = celular;
        this.direccion = direccion;
        this.estudiantes = estudiantes;
    }

    public AcudienteDTO(String nombre, String documentoIdentidad, String celular, String direccion, List<Estudiante> estudiantes, String correo) {
        this.nombre = nombre;
        this.documentoIdentidad = documentoIdentidad;
        this.celular = celular;
        this.direccion = direccion;
        this.estudiantes = estudiantes;
        this.correo = correo;
    }

    //Otros Metodos-------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcudienteDTO that = (AcudienteDTO) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(documentoIdentidad, that.documentoIdentidad) && Objects.equals(celular, that.celular) && Objects.equals(direccion, that.direccion) && Objects.equals(estudiantes, that.estudiantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, documentoIdentidad, celular, direccion, estudiantes);
    }

    @Override
    public String toString() {
        return "AcudienteDTO{" +
                "nombre='" + nombre + '\'' +
                ", documentoIdentidad='" + documentoIdentidad + '\'' +
                ", celular='" + celular + '\'' +
                ", direccion='" + direccion + '\'' +
                ", estudiantes=" + estudiantes +
                ", correo='" + correo + '\'' +
                '}';
    }
}
