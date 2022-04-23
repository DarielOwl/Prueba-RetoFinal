package co.com.sofka.pruebaRetoFinal.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "notas")
@Getter @Setter @NoArgsConstructor //-> Nos ahorra en escribir codigo repetitivo
public class Nota {

    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);

    //Atributos Nota-----------
    private String nombreMateria;
    private LocalDate fecha;
    private Double puntaje;

    //Constructor de Asignacion--------------
    public Nota(String nombreMateria, LocalDate fecha, Double puntaje) {
        this.nombreMateria = nombreMateria;
        this.fecha = fecha;
        this.puntaje = puntaje;
    }

    //Otros Metodos-------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return Objects.equals(id, nota.id) && Objects.equals(nombreMateria, nota.nombreMateria) && Objects.equals(fecha, nota.fecha) && Objects.equals(puntaje, nota.puntaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreMateria, fecha, puntaje);
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id='" + id + '\'' +
                ", nombreMateria='" + nombreMateria + '\'' +
                ", fecha=" + fecha +
                ", puntaje=" + puntaje +
                '}';
    }

}
