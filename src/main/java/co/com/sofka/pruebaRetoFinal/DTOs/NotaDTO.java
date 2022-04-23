package co.com.sofka.pruebaRetoFinal.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class NotaDTO {

    //Atributos Nota-----------
    private String nombreMateria;
    private LocalDate fecha;
    private Double puntaje;

    //Constructor de Asignacion--------------
    public NotaDTO(String nombreMateria, LocalDate fecha, Double puntaje) {
        this.nombreMateria = nombreMateria;
        this.fecha = fecha;
        this.puntaje = puntaje;
    }

    //Otros Metodos-------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotaDTO notaDTO = (NotaDTO) o;
        return Objects.equals(nombreMateria, notaDTO.nombreMateria) && Objects.equals(fecha, notaDTO.fecha) && Objects.equals(puntaje, notaDTO.puntaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreMateria, fecha, puntaje);
    }

    @Override
    public String toString() {
        return "NotaDTO{" +
                "nombreMateria='" + nombreMateria + '\'' +
                ", fecha=" + fecha +
                ", puntaje=" + puntaje +
                '}';
    }

}
