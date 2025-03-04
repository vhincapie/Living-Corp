package co.edu.unbosque.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "WEB_USERS")
public class Usuario {

    @Id
    @Column(name = "USER_NAME")
    private String nombreUsuario;

    @Column(name = "USER_EMAIL")
    private String correoUsuario;

    @Column(name = "USER_PASSWORD")
    private String contraseniaUsuario;

    @Column(name = "LAST_LOGIN")
    private LocalDateTime ultimoInicioSesion;

    @Column(name = "IS_BLOCKED")
    private boolean bloqueado;

    @Column(name = "IS_PROPERTY_ADMIN")
    private boolean administradorPropiedad;

    @Column(name = "IS_RESIDENT_PPRTY_OWNER")
    private boolean propietarioResidente;

    public Usuario(){

    }

    public Usuario(String nombreUsuario, String correoUsuario, String contraseniaUsuario, LocalDateTime ultimoInicioSesion, boolean bloqueado, boolean administradorPropiedad, boolean propietarioResidente) {
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


