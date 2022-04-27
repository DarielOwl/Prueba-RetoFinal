package co.com.sofka.pruebaRetoFinal.DTOs;

import co.com.sofka.pruebaRetoFinal.models.Horario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class ClaseDTO {

    //Atributos de Clase-----------------
    private String idProfesor;
    private String nombreMateria;
    private String nombreProfesor;
    private List<Horario> horario;

    //Constructor de Asignacion---------------
    public ClaseDTO(String idProfesor, String nombreMateria, String nombreProfesor, List<Horario> horario) {
        this.idProfesor = idProfesor;
        this.nombreMateria = nombreMateria;
        this.nombreProfesor = nombreProfesor;
        this.horario = horario;
    }

    //Otros Metodos----------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClaseDTO claseDTO = (ClaseDTO) o;
        return Objects.equals(idProfesor, claseDTO.idProfesor) && Objects.equals(nombreMateria, claseDTO.nombreMateria) && Objects.equals(nombreProfesor, claseDTO.nombreProfesor) && Objects.equals(horario, claseDTO.horario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfesor, nombreMateria, nombreProfesor, horario);
    }

    @Override
    public String toString() {
        return "ClaseDTO{" +
                "idProfesor='" + idProfesor + '\'' +
                ", nombreMateria='" + nombreMateria + '\'' +
                ", nombreProfesor='" + nombreProfesor + '\'' +
                ", horario=" + horario +
                '}';
    }

}
