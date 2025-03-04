package co.edu.unbosque.view;

import co.edu.unbosque.exceptions.CrearUsuarioException;
import co.edu.unbosque.model.dto.UsuarioDTO;
import co.edu.unbosque.service.ServicioUsuario;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

    @Inject
    private ServicioUsuario servicioUsuario;
    private UsuarioDTO usuarioDTO;

    @PostConstruct
    public void init() {
        usuarioDTO = new UsuarioDTO();
    }

    public void crearUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            servicioUsuario.crearUsuario(usuarioDTO);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario creado correctamente."));
        } catch (CrearUsuarioException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    // Getters y Setters
    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

}
