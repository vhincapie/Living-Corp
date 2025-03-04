package co.edu.unbosque.model.dto;

import java.time.LocalDateTime;

public class SolicitudServicioDTO {

    private int idSolicitud;
    private LocalDateTime fechaHoraSolicitud;
    private UsuarioDTO usuario;
    private PropiedadDTO propiedad;
    private ProveedorServicioDTO proveedorServicio;
    private String descripcionSolicitud;
    private LocalDateTime fechaHoraServicio;

    public SolicitudServicioDTO() {

    }

    public SolicitudServicioDTO(int idSolicitud, LocalDateTime fechaHoraSolicitud, UsuarioDTO usuario, PropiedadDTO propiedad, ProveedorServicioDTO proveedorServicio, String descripcionSolicitud, LocalDateTime fechaHoraServicio) {
        this.idSolicitud = idSolicitud;
        this.fechaHoraSolicitud = fechaHoraSolicitud;
        this.usuario = usuario;
        this.propiedad = propiedad;
        this.proveedorServicio = proveedorServicio;
        this.descripcionSolicitud = descripcionSolicitud;
        this.fechaHoraServicio = fechaHoraServicio;
    }

    // Getters y Setters
    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public LocalDateTime getFechaHoraSolicitud() {
        return fechaHoraSolicitud;
    }

    public void setFechaHoraSolicitud(LocalDateTime fechaHoraSolicitud) {
        this.fechaHoraSolicitud = fechaHoraSolicitud;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public PropiedadDTO getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadDTO propiedad) {
        this.propiedad = propiedad;
    }

    public ProveedorServicioDTO getProveedorServicio() {
        return proveedorServicio;
    }

    public void setProveedorServicio(ProveedorServicioDTO proveedorServicio) {
        this.proveedorServicio = proveedorServicio;
    }

    public String getDescripcionSolicitud() {
        return descripcionSolicitud;
    }

    public void setDescripcionSolicitud(String descripcionSolicitud) {
        this.descripcionSolicitud = descripcionSolicitud;
    }

    public LocalDateTime getFechaHoraServicio() {
        return fechaHoraServicio;
    }

    public void setFechaHoraServicio(LocalDateTime fechaHoraServicio) {
        this.fechaHoraServicio = fechaHoraServicio;
    }
}
