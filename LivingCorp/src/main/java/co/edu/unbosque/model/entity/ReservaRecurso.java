package co.edu.unbosque.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESOURCE_BOOKINGS")
public class ReservaRecurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKING_ID")
    private int idReserva;

    @ManyToOne
    @JoinColumn(name = "USER_NAME")
    private Usuario nombreUsuario;

    @ManyToOne
    @JoinColumn(name = "PROP_RES_ID")
    private RecursoPropiedad recursoPropiedad;

    @Column(name = "BOOKING_DATETIME")
    private LocalDateTime fechaHoraReserva;

    @Column(name = "BOOKING_START_DATE")
    private LocalDateTime fechaHoraInicioReserva;

    @Column(name = "BOOKING_END_DATE")
    private LocalDateTime fechaHoraFinReserva;

    @Column(name = "BOOKING_COST")
    private double costoReserva;

    @Column(name = "PAYMENT_COMPLETE")
    private boolean pagoCompleto;

    public ReservaRecurso() {

    }

    public ReservaRecurso(Usuario nombreUsuario, RecursoPropiedad recursoPropiedad, LocalDateTime fechaHoraReserva, LocalDateTime fechaHoraInicioReserva, LocalDateTime fechaHoraFinReserva, double costoReserva, boolean pagoCompleto) {
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

    public Usuario getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(Usuario nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public RecursoPropiedad getRecursoPropiedad() {
        return recursoPropiedad;
    }

    public void setRecursoPropiedad(RecursoPropiedad recursoPropiedad) {
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
