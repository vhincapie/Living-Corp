package co.edu.unbosque.model.dto;

import java.time.LocalDateTime;

public class ReservaRecursoDTO {

    private int idReserva;
    private UsuarioDTO nombreUsuario;
    private RecursoPropiedadDTO recursoPropiedad;
    private LocalDateTime fechaHoraReserva;
    private LocalDateTime fechaHoraInicioReserva;
    private LocalDateTime fechaHoraFinReserva;
    private double costoReserva;
    private boolean pagoCompleto;

    public ReservaRecursoDTO() {

    }

    public ReservaRecursoDTO(int idReserva, UsuarioDTO nombreUsuario, RecursoPropiedadDTO recursoPropiedad, LocalDateTime fechaHoraReserva, LocalDateTime fechaHoraInicioReserva, LocalDateTime fechaHoraFinReserva, double costoReserva, boolean pagoCompleto) {
        this.idReserva = idReserva;
        this.nombreUsuario = nombreUsuario;
        this.recursoPropiedad = recursoPropiedad;
        this.fechaHoraReserva = fechaHoraReserva;
        this.fechaHoraInicioReserva = fechaHoraInicioReserva;
        this.fechaHoraFinReserva = fechaHoraFinReserva;
        this.costoReserva = costoReserva;
        this.pagoCompleto = pagoCompleto;
    }

    // Getters y Setters
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public UsuarioDTO getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(UsuarioDTO nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public RecursoPropiedadDTO getRecursoPropiedad() {
        return recursoPropiedad;
    }

    public void setRecursoPropiedad(RecursoPropiedadDTO recursoPropiedad) {
        this.recursoPropiedad = recursoPropiedad;
    }

    public LocalDateTime getFechaHoraReserva() {
        return fechaHoraReserva;
    }

    public void setFechaHoraReserva(LocalDateTime fechaHoraReserva) {
        this.fechaHoraReserva = fechaHoraReserva;
    }

    public LocalDateTime getFechaHoraInicioReserva() {
        return fechaHoraInicioReserva;
    }

    public void setFechaHoraInicioReserva(LocalDateTime fechaHoraInicioReserva) {
        this.fechaHoraInicioReserva = fechaHoraInicioReserva;
    }

    public LocalDateTime getFechaHoraFinReserva() {
        return fechaHoraFinReserva;
    }

    public void setFechaHoraFinReserva(LocalDateTime fechaHoraFinReserva) {
        this.fechaHoraFinReserva = fechaHoraFinReserva;
    }

    public double getCostoReserva() {
        return costoReserva;
    }

    public void setCostoReserva(double costoReserva) {
        this.costoReserva = costoReserva;
    }

    public boolean isPagoCompleto() {
        return pagoCompleto;
    }

    public void setPagoCompleto(boolean pagoCompleto) {
        this.pagoCompleto = pagoCompleto;
    }
}
