package co.com.sofka.pruebaRetoFinal.controllers;

import co.com.sofka.pruebaRetoFinal.models.*;
import co.com.sofka.pruebaRetoFinal.models.values.Materia;
import co.com.sofka.pruebaRetoFinal.services.Impl.ClaseServiceImpl;
import co.com.sofka.pruebaRetoFinal.services.Impl.EstudianteServiceImpl;
import co.com.sofka.pruebaRetoFinal.services.Impl.GrupoServiceImpl;
import co.com.sofka.pruebaRetoFinal.services.Impl.MaestroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class GrupoController {

    @Autowired
    GrupoServiceImpl grupoService = new GrupoServiceImpl();
    @Autowired
    EstudianteServiceImpl estudianteService = new EstudianteServiceImpl();
    @Autowired
    MaestroServiceImpl maestroService = new MaestroServiceImpl();

    @Autowired
    ClaseServiceImpl claseService;

    //-----------------CRUD-----------------//
    //Guardar un Grupo
    @PostMapping("/addGrupo")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<Grupo> save(@RequestBody Grupo grupo) {
        return this.grupoService.save(new Grupo(
                grupo.getGrado(),
                grupo.getCurso(),
                grupo.getNombre()));
    }

    //Mostrar Todos los Grupos
    @GetMapping(value = "/allGrupo")
    private Flux<Grupo> findAll() {
        return this.grupoService.findAll();
    }

    //Actualizar Grupo
    @PutMapping("/updateGrupo/{id}")
    private Mono<Grupo> update(@PathVariable("id") String id, @RequestBody Grupo grupo) {
        try {
            Grupo grupo1 = this.grupoService.findById(id).block();
            grupo1.setEstado(true);
            grupo1.setNombre(grupo.getNombre());
            grupo1.setGrado(grupo.getGrado());
            grupo1.setCurso(grupo.getCurso());
            return this.grupoService.update(id, grupo1)
                    .flatMap(grupo2 -> Mono.just(grupo1)).switchIfEmpty(Mono.empty());
        } catch (Exception e) {
            return Mono.error(new Throwable("No existe el grupo con la id" + id));
        }

    }

    //Eliminar Grupo
    @DeleteMapping("/removeGrupo/{id}")
    private Mono<Grupo> delete(@PathVariable("id") String id) {
        return this.grupoService.delete(id)
                .flatMap(grupo1 -> Mono.just((grupo1)).switchIfEmpty(Mono.empty()));

    }
    //-----------------CRUD-----------------//

    //Obtener grupos con estado TRUE y con todos sus datos incluida la lista de estudiantes.
    @GetMapping("/allGruposActivos")
    private Flux<Grupo> allGruposActivos() {
        return this.grupoService.findAll().filter(g -> g.isEstado() != false);
    }

    //Borrar grupo de forma lógica mediante su ID
    @PutMapping("/deleteGrupo/{id}")
    private Mono<Grupo> logicDeleteGrupoById(@PathVariable("id") String id) {
        try{
            Grupo grupo = this.grupoService.findById(id).block();
            grupo.setEstado(false);
            //Si grupo no tiene una lista de estudiante, continau
            if(grupo.getEstudiantes()!=null){
                List<Estudiante> estudianteList = grupo.getEstudiantes();
                estudianteList.forEach( e -> {
                    Estudiante estudiante = this.estudianteService.findById(e.getId()).block();
                    estudiante.setGrupo("");
                    this.estudianteService.update(estudiante.getId(),estudiante);
                });
                grupo.setEstudiantes(null);
            }
            if(grupo.getDirector()!=null){
                Maestro maestro = this.maestroService.findById(grupo.getDirector().getId()).block();
                maestro.setIdGrupoDirector("");
                this.maestroService.update(maestro.getId(),maestro);
                grupo.setDirector(null);
            }
            return this.grupoService.update(id,grupo);
        }catch (Exception e){
            return Mono.empty();
        }


    }

    //Eliminar estudiante de un grupo mediante ID estudiante e ID grupo.
    @PutMapping("/deleteEstudianteFromGrupo/{idEstudiante}/{idGrupo}")
    private Mono<Estudiante> deleteEstudianteFromGrupo(@PathVariable("idEstudiante") String idEstudiante, @PathVariable("idGrupo") String idGrupo) {
        try {
            Grupo grupo = this.grupoService.findById(idGrupo).block();
            Estudiante estudiante = this.estudianteService.findById(idEstudiante).block();
            List<Estudiante> estudianteList = grupo.getEstudiantes();
            Iterator iterator = estudianteList.iterator();
            while (iterator.hasNext()) {
                Estudiante e = (Estudiante) iterator.next();
                if (e.getId().equalsIgnoreCase(idEstudiante)) {
                    iterator.remove();
                }
            }
            estudiante.setGrupo("");
            return this.grupoService.update(idGrupo, grupo).then(this.estudianteService.save(estudiante));
        } catch (Exception e) {
            return Mono.empty();
        }
    }

    //Agregar estudiante a un grupo mediante ID estudiante e ID grupo.
    @PutMapping("/addEstudianteToGrupo/{idEstudiante}/{idGrupo}")
    private Mono<Estudiante> addEstudianteToGrupo(@PathVariable("idEstudiante") String idDocumentoIdentidad, @PathVariable("idGrupo") String idGrupo) {
        try {
            Grupo grupo = this.grupoService.findById(idGrupo).block();
            Estudiante estudiante = this.estudianteService.findById(idDocumentoIdentidad).block();
            estudiante.setGrupo(grupo.getId());
            List<Estudiante> estudianteList = grupo.getEstudiantes();
            estudianteList.add(estudiante);
            grupo.setEstudiantes(estudianteList);
            return this.grupoService.save(grupo).then(this.estudianteService.save(estudiante));
        } catch (Exception e) {
            Grupo grupo = this.grupoService.findById(idGrupo).block();
            Estudiante estudiante = this.estudianteService.findById(idDocumentoIdentidad).block();
            estudiante.setGrupo(grupo.getId());
            List<Estudiante> estudianteList = new ArrayList<Estudiante>();
            estudianteList.add(estudiante);
            grupo.setEstudiantes(estudianteList);
            return this.grupoService.save(grupo).then(this.estudianteService.save(estudiante));
        }
    }

    //Obtener lista de estudiantes de un grupo mediante el ID del grupo.
    @GetMapping("/allEstudiantesFromGrupo/{idGrupo}")
    private Flux<Estudiante> allEstudiantesFromGrupo(@PathVariable("idGrupo") String id) {
        try {
            return Flux.fromIterable(grupoService.findById(id).block().getEstudiantes());
        } catch (Exception e) {
            return Flux.empty();
        }

    }

    //Agregar un estudiante


    //Añadir horario a un Grupo de Clase-------------
    @PutMapping("/addHorarioClase/{id}")
    private Mono<Clase> addHorarioDeClase(@PathVariable("id") String id, @RequestBody Clase clase) {

        //Primero buscamos el grupo a añadir la Clase
        Grupo grupo = this.grupoService.findById(id).block();

        //Si no tiene clase, añadirle una nueva
        if (grupo.getClases() == null) {
            List<Clase> claseNueva = new ArrayList<Clase>();
            claseNueva.add(clase);
            grupo.setClases(claseNueva);
            return this.grupoService.update(id, grupo).thenReturn(clase);
        }

        //Obtener el grupo y asignarle la nueva clase
        List<Clase> claseNueva = grupo.getClases();
        claseNueva.add(clase); //Añade la clase que mandan del Front
        grupo.setClases(claseNueva);

        //Si ya tiene grupo de clase, añade uno nuevo
        return this.grupoService.update(id, grupo).thenReturn(clase);
    }

    //Listar horarios de un grupo en especifico
    @GetMapping("/allHorariosGrupos/{id}")
    private Flux<Clase> allHorariosDeGrupos(@PathVariable("id") String id) {
        try{
            Flux<Clase> claseFlux = Flux.fromIterable(this.grupoService.findById(id).block().getClases());
            return claseFlux;
        }catch (Exception e){
            return Flux.empty();
        }
    }


    //Obtener lista de estudiantes de un grupo mediante el ID del grupo.
    @GetMapping("/findGrupo/{idGrupo}")
    private Mono<Grupo> findGrupo(@PathVariable("idGrupo") String id) {
        return this.grupoService.findById(id);
    }
    //Añadir horario a un Grupo de Clase-------------
    @PutMapping("/addHorarioClase/{idGrupo}/{idMaestro}/{nombreMateria}")
    private Mono<Grupo> addHorarioDeClase(
            @PathVariable("idGrupo") String idGrupo,
            @PathVariable("idMaestro") String idMaestro,
            @PathVariable("nombreMateria") String nombreMateria,
            @RequestBody List<Horario> horarios) {
        try {
            Grupo grupoMono = this.grupoService.findById(idGrupo).block();
            Clase clase = new Clase(
                    new Materia(nombreMateria),
                    horarios,
                    this.maestroService.findById(idMaestro).block());

            if (grupoMono.getClases() == null) {
                List<Clase> claseList = new ArrayList<Clase>();
                claseList.add(clase);
                grupoMono.setClases(claseList);
                return this.grupoService.update(idGrupo, grupoMono);
                //return this.grupoService.save(grupoMono).thenReturn(clase);
            }
            grupoMono.getClases().add(clase);
            return this.grupoService.update(idGrupo, grupoMono);
        } catch (Exception e) {
            return Mono.empty();
        }
    }

    //URL para hacer PUT de maestro, que vincule al maestro como director de grupo a un grupo,
    @PutMapping("/addDirectorToGrupo/{idGrupo}/{idMaestro}")
    public Mono<Maestro> addDirectorToGrupo(@PathVariable("idGrupo") String idGrupo, @PathVariable("idMaestro") String idMaestro) {
        try {
            Maestro maestro = this.maestroService.findById(idMaestro).block();
            Grupo grupo = this.grupoService.findById(idGrupo).block();
            maestro.setIdGrupoDirector(grupo.getId());
            grupo.setDirector(maestro);
            return this.grupoService.update(idGrupo, grupo).then(this.maestroService.update(idMaestro, maestro));
        } catch (Exception e) {
            return Mono.empty();
        }
    }

    //Eliminar director de un grupo segúin su idGrupo, idMaestro
    @PutMapping("/removeDirectorFromGrupo/{idGrupo}/{idMaestro}")
    public Mono<Maestro> removeDirectorFromGrupo(@PathVariable("idGrupo") String idGrupo, @PathVariable("idMaestro") String idMaestro) {
        try {
            Maestro maestro = this.maestroService.findById(idMaestro).block();
            Grupo grupo = this.grupoService.findById(idGrupo).block();
            grupo.setDirector(null);
            maestro.setIdGrupoDirector("");
            return this.grupoService.update(idGrupo, grupo).then(this.maestroService.update(idMaestro, maestro));
        } catch (Exception e) {
            return Mono.empty();
        }
    }

    @GetMapping("/findGrupoFromMaestroDirector/{correo}")
    public Mono<Grupo> findGrupoFromMaestroDirector(@PathVariable("correo") String correo) {
        try {
            return this.grupoService.findById(this.maestroService.findByCorreo(correo).block().getIdGrupoDirector())
                    .filter(g -> g.isEstado() == true);
        } catch (Exception e) {
            return Mono.empty();
        }
    }

    //Agregar nota al estudiante
    @PutMapping("/addNotaToEstudiante/{correoMaestro}/{idEstudiante}")
    public Mono<Nota> addNotaToEstudiante(@PathVariable("correoMaestro") String correoMaestro, @PathVariable("idEstudiante") String idEstudiante, @RequestBody Nota notaNueva) {

        try {
            //Primero buscamos el maestro

            Grupo grupo = this.grupoService.findById(this.maestroService.findByCorreo(correoMaestro).block().getIdGrupoDirector()).block();

            //Si la lista de Estudiantes esta vacia
            if (grupo.getEstudiantes() == null)
                return Mono.empty();

            List<Estudiante> estudiantes = grupo.getEstudiantes(); //Obtenemos la lista de estudiantes de ese grupo

            estudiantes.stream().map(estudiante -> {

                //Si encuentra el estudiante, inserta la notas
                if (estudiante.getId().equalsIgnoreCase(idEstudiante)) {

                    //Si las notas estan vacias, inserta una nueva lista de Notas
                    if (estudiante.getNotas() == null) {
                        List<Nota> notas = new ArrayList<Nota>();
                        notas.add(notaNueva);
                        estudiante.setNotas(notas);
                    }

                    //Si ya tiene notas, le añadimos
                    estudiante.getNotas().add(notaNueva);
                }
                return estudiante;
            });

            //Setiamos la lista de estudiantes a Grupo
            grupo.setEstudiantes(estudiantes);
            return this.grupoService.update(grupo.getId(),grupo).thenReturn(notaNueva); //Actualizamos el grupo

        } catch (Exception e) {
            return Mono.empty();
        }
    }

    //Obtener lista de materias
    @GetMapping("/allGruposFromMaestro/{correoMaestro}")
    public Flux<Grupo> allGruposFromMaestro(@PathVariable("correoMaestro") String correoMaestro){
        try{

            return this.grupoService.findAll()
                    .filter(g-> g.getDirector()!=null)
                    .filter(g-> g.getDirector().getCorreo().equals(correoMaestro));
        }catch (Exception e){
            return Flux.empty();
        }
    }




}
