package co.edu.unbosque.view;

import co.edu.unbosque.exceptions.CrearPropiedadException;
import co.edu.unbosque.model.dto.PropiedadDTO;
import co.edu.unbosque.model.dto.UsuarioDTO;
import co.edu.unbosque.service.ServicioPropiedad;
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
public class PropiedadBean implements Serializable {

    @Inject
    private ServicioPropiedad servicioPropiedad;
    @Inject
    private ServicioUsuario servicioUsuario;
    private PropiedadDTO propiedadDTO;
    private List<PropiedadDTO> listaPropiedades;
    private UsuarioDTO usuarioDTO;
    private List<UsuarioDTO> listaUsuarios;
    private String disponibilidadPropiedad;

    @PostConstruct
    public void init() {
        propiedadDTO = new PropiedadDTO();
        usuarioDTO = new UsuarioDTO();
        cargarUsuarios();
        cargarPropiedades();
    }

    public void crearPropiedad() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            servicioPropiedad.crearPropiedad(propiedadDTO, usuarioDTO.getNombreUsuario());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Propiedad creada correctamente."));
        } catch (CrearPropiedadException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void buscarPropiedades() {
        if (propiedadDTO.isDisponibleParaAlquiler()) {
            disponibilidadPropiedad = "Arriendo";
        } else if (propiedadDTO.isDisponibleParaVenta()) {
            disponibilidadPropiedad = "Venta";
        }

        listaPropiedades = servicioPropiedad.filtrarPropiedades(propiedadDTO.getCiudadPropiedad(), propiedadDTO.isDisponibleParaAlquiler(), propiedadDTO.isDisponibleParaVenta());
    }

    public void cargarUsuarios(){
        listaUsuarios = servicioUsuario.listarUsuarios();
    }

    public void cargarPropiedades(){
        listaPropiedades = servicioPropiedad.listarPropiedades();
    }

    // Getters y Setters
    public PropiedadDTO getPropiedadDTO() {
        return propiedadDTO;
    }

    public void setPropiedadDTO(PropiedadDTO propiedadDTO) {
        this.propiedadDTO = propiedadDTO;
    }

    public List<PropiedadDTO> getListaPropiedades() {
        return listaPropiedades;
    }

    public void setListaPropiedades(List<PropiedadDTO> listaPropiedades) {
        this.listaPropiedades = listaPropiedades;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public List<UsuarioDTO> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioDTO> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getDisponibilidadPropiedad() {
        return disponibilidadPropiedad;
    }

    public void setDisponibilidadPropiedad(String disponibilidadPropiedad) {
        this.disponibilidadPropiedad = disponibilidadPropiedad;
    }
}
