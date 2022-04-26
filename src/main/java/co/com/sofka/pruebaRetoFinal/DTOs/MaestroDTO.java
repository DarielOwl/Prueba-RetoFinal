package co.com.sofka.pruebaRetoFinal.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class MaestroDTO {

    //Atributos Maestro-----------
    private String documentoIdentidad;
    private String nombre;
    private String correo;
    private String direccion;
    private String celular;
    private String especialidad;
    private List<String> materias;
    private Boolean estado;

    //Constructor de Maestro--------------
    public MaestroDTO(String documentoIdentidad, String nombre, String correo, String direccion, String celular, Boolean estado) {
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.celular = celular;
        this.estado = estado;
    }

    //Constructor Completo de Maestro (tiene Especialidad y Lista de Materias)--------------
    public MaestroDTO(String documentoIdentidad, String nombre, String correo, String direccion, String celular, String especialidad, List<String> materias, Boolean estado) {
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.celular = celular;
        this.especialidad = especialidad;
        this.materias = materias;
        this.estado = estado;
    }

    //Otros Metodos-------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaestroDTO that = (MaestroDTO) o;
        return Objects.equals(documentoIdentidad, that.documentoIdentidad) && Objects.equals(nombre, that.nombre) && Objects.equals(correo, that.correo) && Objects.equals(direccion, that.direccion) && Objects.equals(celular, that.celular) && Objects.equals(especialidad, that.especialidad) && Objects.equals(materias, that.materias) && Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentoIdentidad, nombre, correo, direccion, celular, especialidad, materias, estado);
    }

    @Override
    public String toString() {
        return "MaestroDTO{" +
                "documentoIdentidad='" + documentoIdentidad + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", celular='" + celular + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", materias=" + materias +
                ", estado=" + estado +
                '}';
    }

}
