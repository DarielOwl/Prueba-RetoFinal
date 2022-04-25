package co.com.sofka.pruebaRetoFinal.mappers;

import co.com.sofka.pruebaRetoFinal.DTOs.AcudienteDTO;
import co.com.sofka.pruebaRetoFinal.models.Acudiente;
import reactor.core.publisher.Flux;

public class AcudienteMapper {

    //Creamos el AcudienteDTO a partir de Acudiente
    public AcudienteDTO createAcudienteDTO(Acudiente acudiente) {
        AcudienteDTO acudienteDTO = new AcudienteDTO(
                acudiente.getNombre(),
                acudiente.getDocumentoIdentidad(),
                acudiente.getCelular(),
                acudiente.getDireccion(),
                acudiente.getEstudiantes(),
                acudiente.getCorreo()
                );
        return acudienteDTO;
    }

    //Mapper para convertir una lista de acudientes a una lista de acudientesDTOs
    public Flux<AcudienteDTO> convertirAcudienteDTOs(Flux<Acudiente> acudienteFlux) {
        Flux<AcudienteDTO> acudienteDTOFlux = acudienteFlux.map(this::createAcudienteDTO);
        return acudienteDTOFlux;
    }

    //Mapper para convertir Acudiente a partir del AcudienteDTO
    public Acudiente createAcudiente(AcudienteDTO acudienteDTO){
        Acudiente acudiente = new Acudiente(
                acudienteDTO.getNombre(),
                acudienteDTO.getDocumentoIdentidad(),
                acudienteDTO.getCelular(),
                acudienteDTO.getDireccion(),
                acudienteDTO.getEstudiantes(),
                acudienteDTO.getCorreo()
        );

        return acudiente;
    }




}
