package co.edu.unbosque.view;

import co.edu.unbosque.exceptions.ResidentePropiedadException;
import co.edu.unbosque.model.dto.PropiedadDTO;
import co.edu.unbosque.model.dto.ResidentePropiedadDTO;
import co.edu.unbosque.model.dto.UsuarioDTO;
import co.edu.unbosque.service.ServicioPropiedad;
import co.edu.unbosque.service.ServicioResidente;
import co.edu.unbosque.service.ServicioUsuario;
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
public class ResidenteBean implements Serializable {

    @Inject
    private ServicioResidente servicioResidente;
    @Inject
    private ServicioPropiedad servicioPropiedad;
    @Inject
    private ServicioUsuario servicioUsuario;
    private ResidentePropiedadDTO residentePropiedadDTO;
    private PropiedadDTO propiedadDTO;
    private UsuarioDTO usuarioDTO;
    private List<PropiedadDTO> listaPropiedades;
    private List<UsuarioDTO> listaUsuarios;
    private List<ResidentePropiedadDTO> listaResidentePropiedades;

    @PostConstruct
    public void init() {
        residentePropiedadDTO = new ResidentePropiedadDTO();
        propiedadDTO = new PropiedadDTO();
        usuarioDTO = new UsuarioDTO();
        cargarPropiedades();
        cargarUsuarios();
        cargarResidentes();
    }

    public void asociarResidenteConPropiedad() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            servicioResidente.asociarResidenteConPropiedad(residentePropiedadDTO, propiedadDTO.getIdPropiedad(), usuarioDTO.getNombreUsuario());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Residente asociado a la propiedad correctamente."));
        } catch (ResidentePropiedadException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void cargarPropiedades() {
        listaPropiedades = servicioPropiedad.listarPropiedades();
    }

    public void cargarUsuarios() {
        listaUsuarios = servicioUsuario.listarUsuarios();
    }

    public void cargarResidentes(){
        listaResidentePropiedades = servicioResidente.listarResidentes();
    }

    public ResidentePropiedadDTO getResidentePropiedadDTO() {
        return residentePropiedadDTO;
    }

    public void setResidentePropiedadDTO(ResidentePropiedadDTO residentePropiedadDTO) {
        this.residentePropiedadDTO = residentePropiedadDTO;
    }

    public PropiedadDTO getPropiedadDTO() {
        return propiedadDTO;
    }

    public void setPropiedadDTO(PropiedadDTO propiedadDTO) {
        this.propiedadDTO = propiedadDTO;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public List<PropiedadDTO> getListaPropiedades() {
        return listaPropiedades;
    }

    public void setListaPropiedades(List<PropiedadDTO> listaPropiedades) {
        this.listaPropiedades = listaPropiedades;
    }

    public List<UsuarioDTO> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioDTO> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<ResidentePropiedadDTO> getListaResidentePropiedades() {
        return listaResidentePropiedades;
    }

    public void setListaResidentePropiedades(List<ResidentePropiedadDTO> listaResidentePropiedades) {
        this.listaResidentePropiedades = listaResidentePropiedades;
    }
}
