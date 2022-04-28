package co.com.sofka.pruebaRetoFinal.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "maestros")
@Getter @Setter @NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class Maestro {

    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);

    //Atributos Maestro-----------
    private String documentoIdentidad;
    private String nombre;
    private String correo;
    private String direccion;
    private String celular;
    private String especialidad;
    private List<String> materias = null;
    private List<String> grupos = null;
    private Boolean estado = true;

    //Constructor de Asignacion--------------
    public Maestro(String documentoIdentidad, String nombre, String correo, String direccion, String celular, Boolean estado) {
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.celular = celular;
        this.estado = estado;
    }

    //Constructor Completo de Maestro (tiene Especialidad y Lista de Materias)--------------
    public Maestro(String documentoIdentidad, String nombre, String correo, String direccion, String celular, String especialidad, List<String> materias, Boolean estado) {
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.celular = celular;
        this.especialidad = especialidad;
        this.materias = materias;
        this.estado = estado;
    }

    //Constructor de Maestro (tiene Lista de Grupos)--------------
    public Maestro(String id, String documentoIdentidad, String nombre, String correo, String direccion, String celular, String especialidad, List<String> materias, List<String> grupos, Boolean estado) {
        this.id=id;
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.celular = celular;
        this.especialidad = especialidad;
        this.materias = materias;
        this.grupos = grupos;
        this.estado = estado;
    }

    //Otros Metodos-------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maestro maestro = (Maestro) o;
        return Objects.equals(id, maestro.id) && Objects.equals(documentoIdentidad, maestro.documentoIdentidad) && Objects.equals(nombre, maestro.nombre) && Objects.equals(correo, maestro.correo) && Objects.equals(direccion, maestro.direccion) && Objects.equals(celular, maestro.celular) && Objects.equals(especialidad, maestro.especialidad) && Objects.equals(materias, maestro.materias) && Objects.equals(grupos, maestro.grupos) && Objects.equals(estado, maestro.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentoIdentidad, nombre, correo, direccion, celular, especialidad, materias, grupos, estado);
    }

    @Override
    public String toString() {
        return "Maestro{" +
                "id='" + id + '\'' +
                ", documentoIdentidad='" + documentoIdentidad + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", celular='" + celular + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", materias=" + materias +
                ", grupos=" + grupos +
                ", estado=" + estado +
                '}';
    }

}
