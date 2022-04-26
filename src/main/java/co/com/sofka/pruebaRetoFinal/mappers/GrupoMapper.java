package co.com.sofka.pruebaRetoFinal.mappers;

import co.com.sofka.pruebaRetoFinal.DTOs.GrupoDTO;
import co.com.sofka.pruebaRetoFinal.models.Grupo;
import reactor.core.publisher.Flux;

public class GrupoMapper {

    //Convertir Grupo a GrupoDTO
    public GrupoDTO createGrupoDTO(Grupo grupo) {
        GrupoDTO grupoDTO = new GrupoDTO(
            grupo.getMaestro(),
                grupo.getHorarios(),
                grupo.getGrado(),
                grupo.getMaximoCupos(),
                grupo.getEstudiantes(),
                grupo.getNotas(),
                grupo.getCurso()
        );
        return grupoDTO;
    }

    //Convertir GrupoDTO a Grupo
    public Grupo createGrupo(GrupoDTO grupoDTO){
        Grupo grupo = new Grupo(
                grupoDTO.getMaestro(),
                grupoDTO.getHorarios(),
                grupoDTO.getGrado(),
                grupoDTO.getMaximoCupos(),
                grupoDTO.getEstudiantes(),
                grupoDTO.getNotas(),
                grupoDTO.getCurso(),
                grupoDTO.getNombre()
        );
        return grupo;
    }

    public Flux<GrupoDTO> convertirGrupoDTOs(Flux<Grupo> grupoFlux){
        Flux<GrupoDTO> grupoDTOFlux = grupoFlux.map(this::createGrupoDTO);
        return grupoDTOFlux;
    }
}
