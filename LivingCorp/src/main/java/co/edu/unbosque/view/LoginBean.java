package co.edu.unbosque.view;

import co.edu.unbosque.model.dto.UsuarioDTO;
import co.edu.unbosque.service.ServicioUsuario;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private static final Logger logger = Logger.getLogger(LoginBean.class);

    @Inject
    private ServicioUsuario servicioUsuario;
    private UsuarioDTO usuarioDTO;

    @PostConstruct
    public void init() {
        usuarioDTO = new UsuarioDTO();
    }

    public void iniciarSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (servicioUsuario.validarCredenciales(usuarioDTO)) {
                servicioUsuario.manejarSesionExitosa(usuarioDTO, context.getExternalContext());
            } else {
                servicioUsuario.manejarErrorDeSesion(usuarioDTO.getNombreUsuario());
            }
        } catch (IOException e) {
            logger.error("Error al iniciar sesión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void verificarSesion() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        if (usuarioDTO == null || usuarioDTO.getNombreUsuario() == null) {
            externalContext.redirect(externalContext.getRequestContextPath() + "/iniciar_sesion.xhtml");
            logger.warn("Intento de acceso no autorizado: Redirigiendo a la página de inicio de sesión.");
        }
    }

    public void cerrarSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        externalContext.invalidateSession();

        try {
            externalContext.redirect(externalContext.getRequestContextPath() + "/iniciar_sesion.xhtml");
            logger.info("Se cerró la sesión correctamente.");
        } catch (IOException e) {
            logger.error("Error al redirigir a la página de inicio de sesión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Getters y setters
    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }
}
