package co.edu.unbosque.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "SERVICE_RFQ")
public class CotizacionServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RFQ_ID")
    private int idCotizacion;

    @Column(name = "RFQ_DATETIME")
    private LocalDateTime fechaHoraSolicitud;

    @ManyToOne
    @JoinColumn(name = "USER_NAME")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Propiedad propiedad;

    @ManyToOne
    @JoinColumn(name = "SVC_PROVIDER_ID")
    private ProveedorServicio proveedorServicio;

    @Column(name = "REQUEST_DESCRIPTION")
    private String descripcionSolicitud;

    public CotizacionServicio() {

    }

    public CotizacionServicio(LocalDateTime fechaHoraSolicitud, Usuario usuario, Propiedad propiedad, ProveedorServicio proveedorServicio, String descripcionSolicitud) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public ProveedorServicio getProveedorServicio() {
        return proveedorServicio;
    }

    public void setProveedorServicio(ProveedorServicio proveedorServicio) {
        this.proveedorServicio = proveedorServicio;
    }

    public String getDescripcionSolicitud() {
        return descripcionSolicitud;
    }

    public void setDescripcionSolicitud(String descripcionSolicitud) {
        this.descripcionSolicitud = descripcionSolicitud;
    }
}
