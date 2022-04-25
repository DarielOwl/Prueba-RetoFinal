package co.com.sofka.pruebaRetoFinal.mappers;

import co.com.sofka.pruebaRetoFinal.DTOs.EstudianteDTO;
import co.com.sofka.pruebaRetoFinal.DTOs.MaestroDTO;
import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import co.com.sofka.pruebaRetoFinal.models.Maestro;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class MaestroMapper {

    //Crea MaestroDTO a partir del objeto original Maestro
    public MaestroDTO createMaestroDTO(Maestro maestro) {

        //Creamos el MaestroDTO a partir del maestroOriginal
        MaestroDTO maestroDTO = new MaestroDTO(
                maestro.getNombre(),
                maestro.getCorreo(),
                maestro.getDireccion(),
                maestro.getCelular(),
                maestro.getEstado()
        );

        /*TODO: tener en cuenta alguna verificacion a futuro para crear el DTO*/

        return maestroDTO; //Retornamos el dto ya creado
    }

    //Mapper para convertir una lista de maestros a una lista de maestroDTOs
    public Flux<MaestroDTO> convertirMaestroDTOs(Flux<Maestro> maestros) {
        Flux<MaestroDTO> maestosDTOs = maestros.map(this::createMaestroDTO);
        return maestosDTOs;
    }

    //Mapper para convertir Maestro a partir del EstudianteDTO
    public Maestro createMaestro(MaestroDTO maestroDTO){

        //Creamos el Estudiante a partir del EstudianteDTO
        Maestro maestro = new Maestro(
                maestroDTO.getNombre(),
                maestroDTO.getCorreo(),
                maestroDTO.getDireccion(),
                maestroDTO.getCelular(),
                maestroDTO.getEstado()
        );

        return maestro; //Y retornamos el nuevo objeto
    }

}
