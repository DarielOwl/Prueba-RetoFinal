package co.com.sofka.pruebaRetoFinal.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document(collection = "maestros")
@Getter @Setter @NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class Maestro {

    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);

    //Atributos Maestro-----------
    private String nombre;
    private String correo;
    private String direccion;
    private String celular;
    private Boolean estado;

    //Constructor de Asignacion--------------
    public Maestro(String nombre, String correo, String direccion, String celular, Boolean estado) {
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
        Maestro maestro = (Maestro) o;
        return Objects.equals(id, maestro.id) && Objects.equals(nombre, maestro.nombre) && Objects.equals(correo, maestro.correo) && Objects.equals(direccion, maestro.direccion) && Objects.equals(celular, maestro.celular) && Objects.equals(estado, maestro.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, correo, direccion, celular, estado);
    }

    @Override
    public String toString() {
        return "Maestro{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", celular='" + celular + '\'' +
                ", estado=" + estado +
                '}';
    }

}
