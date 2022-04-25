package co.com.sofka.pruebaRetoFinal.mappers;

import co.com.sofka.pruebaRetoFinal.DTOs.EstudianteDTO;
import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Component
public class EstudianteMapper {

    //Crea EstudianteDTO a partir del objeto original Estudiante
    public EstudianteDTO createEstudianteDTO(Estudiante estudiante) {

        //Creamos el EstudianteDTO a partir del estudianteOriginal
        EstudianteDTO estudianteDTO = new EstudianteDTO(
                estudiante.getGrupo(),
                estudiante.getDocumentoIdentidad(),
                estudiante.getNombre(),
                estudiante.getGrado(),
                estudiante.getEdad(),
                estudiante.getEstado(),
                estudiante.getDocumentoIdentidadAcudiente()
        );

        /*TODO: tener en cuenta alguna verificacion a futuro para crear el DTO*/

        return estudianteDTO; //Retornamos el dto ya creado
    }

    //Mapper para convertir una lista de estudiantes a una lista de estudianteDTOs
    public Flux<EstudianteDTO> convertirEstudianteDTOs(Flux<Estudiante> estudiantes) {
        Flux<EstudianteDTO> estudianteDTOs = estudiantes.map(this::createEstudianteDTO);
        return estudianteDTOs;
    }

    //Mapper para convertir Estudiante a partir del EstudianteDTO
    public Estudiante createEstudiante(EstudianteDTO estudianteDTO){

        //Creamos el Estudiante a partir del EstudianteDTO
        Estudiante estudiante = new Estudiante(
                estudianteDTO.getGrupo(),
                estudianteDTO.getDocumentoIdentidad(),
                estudianteDTO.getNombre(),
                estudianteDTO.getGrado(),
                estudianteDTO.getEdad(),
                estudianteDTO.getEstado()
        );

        return estudiante; //Y retornamos el nuevo objeto
    }

}
