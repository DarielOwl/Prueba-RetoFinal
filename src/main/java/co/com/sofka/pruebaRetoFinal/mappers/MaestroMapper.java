package co.com.sofka.pruebaRetoFinal.mappers;

import co.com.sofka.pruebaRetoFinal.DTOs.EstudianteDTO;
import co.com.sofka.pruebaRetoFinal.DTOs.MaestroDTO;
import co.com.sofka.pruebaRetoFinal.models.Estudiante;
import co.com.sofka.pruebaRetoFinal.models.Maestro;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MaestroMapper {

    //Crea MaestroDTO a partir del objeto original Maestro
    public MaestroDTO createMaestroDTO(Maestro maestro) {

        //Creamos el MaestroDTO a partir del maestroOriginal
        MaestroDTO maestroDTO = new MaestroDTO(
                maestro.getDocumentoIdentidad(),
                maestro.getNombre(),
                maestro.getCorreo(),
                maestro.getDireccion(),
                maestro.getCelular(),
                maestro.getEstado()
        );

        /*TODO: tener en cuenta alguna verificacion a futuro para crear el DTO*/

        return maestroDTO; //Retornamos el dto ya creado
    }

    //Mapper para convertir Maestro a partir del EstudianteDTO
    public Maestro createMaestro(MaestroDTO maestroDTO){

        //Creamos el Estudiante a partir del EstudianteDTO
        Maestro maestro = new Maestro(
                maestroDTO.getDocumentoIdentidad(),
                maestroDTO.getNombre(),
                maestroDTO.getCorreo(),
                maestroDTO.getDireccion(),
                maestroDTO.getCelular(),
                maestroDTO.getEstado()
        );

        return maestro; //Y retornamos el nuevo objeto
    }

    //Mapper para convertir una lista de maestros a una lista de maestroDTOs
    public Flux<MaestroDTO> convertirMaestroDTOs(Flux<Maestro> maestros) {
        Flux<MaestroDTO> maestosDTOs = maestros.map(this::createMaestroDTO);
        return maestosDTOs;
    }


    //Mapper (CON MONO) para convertir Mono<Maestro> a partir del Mono<MaestroDTO>
    public Mono<Maestro> createMaestroMono(Mono<MaestroDTO> maestroDTO){

        //Crear Maestro con MONO
        return maestroDTO.map(maestroDTO1 -> {
            Maestro maestro = new Maestro(
                    maestroDTO1.getDocumentoIdentidad(),
                    maestroDTO1.getNombre(),
                    maestroDTO1.getCorreo(),
                    maestroDTO1.getDireccion(),
                    maestroDTO1.getCelular(),
                    maestroDTO1.getEstado()
            );
            return maestro;
        });
    }

    //Crea Mono<MaestroDTO> a partir del objeto original Mono<Maestro>
    public Mono<MaestroDTO> createMaestroDTOMono(Mono<Maestro> maestro) {

        //Crear MaestroDTO con MONO
        return maestro.map(maestro1 -> {
            MaestroDTO maestroDTO = new MaestroDTO(
                    maestro1.getDocumentoIdentidad(),
                    maestro1.getNombre(),
                    maestro1.getCorreo(),
                    maestro1.getDireccion(),
                    maestro1.getCelular(),
                    maestro1.getEstado()
            );
            return maestroDTO;
        });
    }

}
