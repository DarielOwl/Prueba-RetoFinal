package co.com.sofka.pruebaRetoFinal.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "estudiantes")
@Getter @Setter @NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class Estudiante {

    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);

    //Atributos Estudiante-----------
    private String grupo; //-> Esto seria GrupoID
    private String documentoIdentidad;
    private String nombre;
    private Integer grado;
    private Integer edad;
    private Boolean estado;
    private String documentoIdentidadAcudiente;

    private List<Nota> notas; //Lista de notas

    //Constructor de Asignacion--------------
    public Estudiante(String id, String grupo, String documentoIdentidad, String nombre, Integer grado, Integer edad, Boolean estado) {
        this.id = id;
        this.grupo = grupo;
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
        this.grado = grado;
        this.edad = edad;
        this.estado = estado;
    }

    public Estudiante(String documentoIdentidad, String nombre, Integer grado, Integer edad, Boolean estado, String documentoIdentidadAcudiente) {
        this.grupo = "";
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
        this.grado = grado;
        this.edad = edad;
        this.estado = estado;
        this.documentoIdentidadAcudiente = documentoIdentidadAcudiente;
    }

    //Cosntructor sin ID----------------
    public Estudiante(String grupo, String documentoIdentidad, String nombre, Integer grado, Integer edad, Boolean estado, String documentoIdentidadAcudiente) {
        this.grupo = grupo;
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
        this.grado = grado;
        this.edad = edad;
        this.estado = estado;
        this.documentoIdentidadAcudiente = documentoIdentidadAcudiente;
    }

    //Constructor de Estudiante con  LISTA de Notas
    public Estudiante(String id, String grupo, String documentoIdentidad, String nombre, Integer grado, Integer edad, Boolean estado, String documentoIdentidadAcudiente, List<Nota> notas) {
        this.id = id;
        this.grupo = grupo;
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
        this.grado = grado;
        this.edad = edad;
        this.estado = estado;
        this.documentoIdentidadAcudiente = documentoIdentidadAcudiente;
        this.notas = notas;
    }

    //Otros Metodos-------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return Objects.equals(id, that.id) && Objects.equals(grupo, that.grupo) && Objects.equals(documentoIdentidad, that.documentoIdentidad) && Objects.equals(nombre, that.nombre) && Objects.equals(grado, that.grado) && Objects.equals(edad, that.edad) && Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grupo, documentoIdentidad, nombre, grado, edad, estado);
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id='" + id + '\'' +
                ", grupo='" + grupo + '\'' +
                ", documentoIdentidad='" + documentoIdentidad + '\'' +
                ", nombre='" + nombre + '\'' +
                ", grado=" + grado +
                ", edad=" + edad +
                ", estado=" + estado +
                ", documentoIdentidadAcudiente='" + documentoIdentidadAcudiente + '\'' +
                '}';
    }
}
