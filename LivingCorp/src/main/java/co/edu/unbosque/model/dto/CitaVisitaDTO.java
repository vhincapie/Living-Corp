package co.edu.unbosque.model.dto;

import java.time.LocalDateTime;

public class CitaVisitaDTO {

    private int idCita;
    private String nombreVisitante;
    private String nombreAsesor;
    private LocalDateTime fechaHoraCita;
    private PropiedadDTO propiedad;

    public CitaVisitaDTO() {

    }

    public CitaVisitaDTO(int idCita, String nombreVisitante, String nombreAsesor, LocalDateTime fechaHoraCita, PropiedadDTO propiedad) {
        this.idCita = idCita;
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

    public PropiedadDTO getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadDTO propiedad) {
        this.propiedad = propiedad;
    }
}
