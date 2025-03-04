package co.edu.unbosque.model.dto;

import java.time.LocalDateTime;

public class CotizacionServicioDTO {

    private int idCotizacion;
    private LocalDateTime fechaHoraSolicitud;
    private UsuarioDTO usuario;
    private PropiedadDTO propiedad;
    private ProveedorServicioDTO proveedorServicio;
    private String descripcionSolicitud;

    public CotizacionServicioDTO() {

    }

    public CotizacionServicioDTO(int idCotizacion, LocalDateTime fechaHoraSolicitud, UsuarioDTO usuario, PropiedadDTO propiedad, ProveedorServicioDTO proveedorServicio, String descripcionSolicitud) {
        this.idCotizacion = idCotizacion;
        this.fechaHoraSolicitud = fechaHoraSolicitud;
        this.usuario = usuario;
        this.propiedad = propiedad;
        this.proveedorServicio = proveedorServicio;
        this.descripcionSolicitud = descripcionSolicitud;
    }

    // Getters y Setters
    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
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
}
