package co.com.sofka.pruebaRetoFinal.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class MaestroDTO {

    //Atributos Maestro-----------
    String nombre;
    String correo;
    String direccion;
    String celular;
    Boolean estado;

    //Constructor de Asignacion--------------
    public MaestroDTO(String nombre, String correo, String direccion, String celular, Boolean estado) {
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.celular = celular;
        this.estado = estado;
    }

    //Otros Metodos-------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaestroDTO that = (MaestroDTO) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(correo, that.correo) && Objects.equals(direccion, that.direccion) && Objects.equals(celular, that.celular) && Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, correo, direccion, celular, estado);
    }

    @Override
    public String toString() {
        return "MaestroDTO{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", celular='" + celular + '\'' +
                ", estado=" + estado +
                '}';
    }
}
