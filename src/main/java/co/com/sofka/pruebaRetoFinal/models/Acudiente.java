package co.com.sofka.pruebaRetoFinal.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "acudientes")
@Getter @Setter @NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class Acudiente {

    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);

    //Atributos Acudiente-----------
    private String nombre;
    private String documentoIdentidad;
    private String celular;
    private String direccion;
    private List<Estudiante> estudiantes; // -> el Acudiente puede tener varios hijos
    private String correo;

    //Constructor de Asignacion--------------
    public Acudiente(String nombre, String documentoIdentidad, String celular, String direccion, List<Estudiante> estudiantes) {
        this.nombre = nombre;
        this.documentoIdentidad = documentoIdentidad;
        this.celular = celular;
        this.direccion = direccion;
        this.estudiantes = estudiantes;
    }

    public Acudiente(String nombre, String documentoIdentidad, String celular, String direccion, List<Estudiante> estudiantes, String correo) {
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
        Acudiente acudiente = (Acudiente) o;
        return Objects.equals(id, acudiente.id) && Objects.equals(nombre, acudiente.nombre) && Objects.equals(documentoIdentidad, acudiente.documentoIdentidad) && Objects.equals(celular, acudiente.celular) && Objects.equals(direccion, acudiente.direccion) && Objects.equals(estudiantes, acudiente.estudiantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, documentoIdentidad, celular, direccion, estudiantes);
    }

    @Override
    public String toString() {
        return "Acudiente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", documentoIdentidad='" + documentoIdentidad + '\'' +
                ", celular='" + celular + '\'' +
                ", direccion='" + direccion + '\'' +
                ", estudiantes=" + estudiantes +
                ", correo='" + correo + '\'' +
                '}';
    }
}
