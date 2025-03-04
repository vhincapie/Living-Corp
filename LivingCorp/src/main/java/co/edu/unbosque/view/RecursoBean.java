package co.edu.unbosque.view;

import co.edu.unbosque.exceptions.RecursoPropiedadException;
import co.edu.unbosque.exceptions.CrearRecursoException;
import co.edu.unbosque.model.dto.PropiedadDTO;
import co.edu.unbosque.model.dto.RecursoDTO;
import co.edu.unbosque.model.dto.RecursoPropiedadDTO;
import co.edu.unbosque.service.ServicioPropiedad;
import co.edu.unbosque.service.ServicioRecurso;
import co.edu.unbosque.service.ServicioRecursoPropiedad;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class RecursoBean implements Serializable {

    @Inject
    private ServicioRecurso servicioRecurso;
    @Inject
    private ServicioRecursoPropiedad servicioRecursoPropiedad;
    @Inject
    private ServicioPropiedad servicioPropiedad;
    private RecursoDTO recursoDTO;
    private PropiedadDTO propiedadDTO;
    private RecursoPropiedadDTO recursoPropiedadDTO;
    private List<RecursoDTO> listaRecursos;
    private List <PropiedadDTO> listaPropiedades;
    private List<RecursoPropiedadDTO> listaRecursosPropiedades;
    private int idRecurso;
    private int idPropiedad;

    @PostConstruct
    public void init(){
        recursoDTO = new RecursoDTO();
        propiedadDTO = new PropiedadDTO();
        recursoPropiedadDTO = new RecursoPropiedadDTO();
        cargarRecursos();
        cargarPropiedades();
        cargarRecursosPropiedades();
    }

    public void crearRecurso(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            servicioRecurso.crearRecurso(recursoDTO);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Recurso creado correctamente."));
        } catch (CrearRecursoException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void crearRecursoPropiedad(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            servicioRecursoPropiedad.crearRecursoPropiedad(recursoPropiedadDTO, idRecurso, idPropiedad);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Recurso asociado correctamente."));
        }catch (RecursoPropiedadException e){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void cargarRecursos(){
        listaRecursos = servicioRecurso.listarRecursos();
    }

    public void cargarPropiedades(){
       listaPropiedades = servicioPropiedad.listarPropiedades();
    }

    public void cargarRecursosPropiedades(){
        listaRecursosPropiedades = servicioRecursoPropiedad.listarRecursosPropiedad();
    }

    // Getters y Setters
    public RecursoDTO getRecursoDTO() {
        return recursoDTO;
    }

    public void setRecursoDTO(RecursoDTO recursoDTO) {
        this.recursoDTO = recursoDTO;
    }

    public PropiedadDTO getPropiedadDTO() {
        return propiedadDTO;
    }

    public void setPropiedadDTO(PropiedadDTO propiedadDTO) {
        this.propiedadDTO = propiedadDTO;
    }

    public RecursoPropiedadDTO getRecursoPropiedadDTO() {
        return recursoPropiedadDTO;
    }

    public void setRecursoPropiedadDTO(RecursoPropiedadDTO recursoPropiedadDTO) {
        this.recursoPropiedadDTO = recursoPropiedadDTO;
    }

    public List<RecursoDTO> getListaRecursos() {
        return listaRecursos;
    }

    public void setListaRecursos(List<RecursoDTO> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public List<PropiedadDTO> getListaPropiedades() {
        return listaPropiedades;
    }

    public void setListaPropiedades(List<PropiedadDTO> listaPropiedades) {
        this.listaPropiedades = listaPropiedades;
    }

    public List<RecursoPropiedadDTO> getListaRecursosPropiedades() {
        return listaRecursosPropiedades;
    }

    public void setListaRecursosPropiedades(List<RecursoPropiedadDTO> listaRecursosPropiedades) {
        this.listaRecursosPropiedades = listaRecursosPropiedades;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public int getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }
}
