package co.edu.unbosque.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PROPERTY_VISITOR_APPOINTMENT")
public class CitaVisita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPOINTMENT_ID")
    private int idCita;

    @Column(name = "VISITOR_NAME")
    private String nombreVisitante;

    @Column(name = "ADVISOR_NAME")
    private String nombreAsesor;

    @Column(name = "APPOINTMENT_DATETIME")
    private LocalDateTime fechaHoraCita;

    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Propiedad propiedad;

    public CitaVisita() {

    }

    public CitaVisita(String nombreVisitante, String nombreAsesor, LocalDateTime fechaHoraCita, Propiedad propiedad) {
        this.nombreVisitante = nombreVisitante;
        this.nombreAsesor = nombreAsesor;
        this.fechaHoraCita = fechaHoraCita;
        this.propiedad = propiedad;
    }

    // Getters y Setters
    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getNombreVisitante() {
        return nombreVisitante;
    }

    public void setNombreVisitante(String nombreVisitante) {
        this.nombreVisitante = nombreVisitante;
    }

    public String getNombreAsesor() {
        return nombreAsesor;
    }

    public void setNombreAsesor(String nombreAsesor) {
        this.nombreAsesor = nombreAsesor;
    }

    public LocalDateTime getFechaHoraCita() {
        return fechaHoraCita;
    }

    public void setFechaHoraCita(LocalDateTime fechaHoraCita) {
        this.fechaHoraCita = fechaHoraCita;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }
}
