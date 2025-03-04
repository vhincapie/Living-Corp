package co.edu.unbosque.model.dto;

import java.time.LocalDateTime;

public class UsuarioDTO {

    private String nombreUsuario;
    private String correoUsuario;
    private String contraseniaUsuario;
    private LocalDateTime ultimoInicioSesion;
    private boolean bloqueado;
    private boolean administradorPropiedad;
    private boolean propietarioResidente;

    public UsuarioDTO() {

    }

    public UsuarioDTO(String nombreUsuario, String correoUsuario, String contraseniaUsuario, LocalDateTime ultimoInicioSesion, boolean bloqueado, boolean administradorPropiedad, boolean propietarioResidente) {
        this.nombreUsuario = nombreUsuario;
        this.correoUsuario = correoUsuario;
        this.contraseniaUsuario = contraseniaUsuario;
        this.ultimoInicioSesion = ultimoInicioSesion;
        this.bloqueado = bloqueado;
        this.administradorPropiedad = administradorPropiedad;
        this.propietarioResidente = propietarioResidente;
    }

    // Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContraseniaUsuario() {
        return contraseniaUsuario;
    }

    public void setContraseniaUsuario(String contraseniaUsuario) {
        this.contraseniaUsuario = contraseniaUsuario;
    }

    public LocalDateTime getUltimoInicioSesion() {
        return ultimoInicioSesion;
    }

    public void setUltimoInicioSesion(LocalDateTime ultimoInicioSesion) {
        this.ultimoInicioSesion = ultimoInicioSesion;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public boolean isAdministradorPropiedad() {
        return administradorPropiedad;
    }

    public void setAdministradorPropiedad(boolean administradorPropiedad) {
        this.administradorPropiedad = administradorPropiedad;
    }

    public boolean isPropietarioResidente() {
        return propietarioResidente;
    }

    public void setPropietarioResidente(boolean propietarioResidente) {
        this.propietarioResidente = propietarioResidente;
    }
}
