package co.edu.unbosque.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "SERVICE_REQUESTS")
public class SolicitudServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RQST_ID")
    private int idSolicitud;

    @Column(name = "RQST_DATETIME")
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

    @Column(name = "SVC_DATETIME")
    private LocalDateTime fechaHoraServicio;

    public SolicitudServicio() {

    }

    public SolicitudServicio(LocalDateTime fechaHoraSolicitud, Usuario usuario, Propiedad propiedad, ProveedorServicio proveedorServicio, String descripcionSolicitud, LocalDateTime fechaHoraServicio) {
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

    public LocalDateTime getFechaHoraServicio() {
        return fechaHoraServicio;
    }

    public void setFechaHoraServicio(LocalDateTime fechaHoraServicio) {
        this.fechaHoraServicio = fechaHoraServicio;
    }
}
