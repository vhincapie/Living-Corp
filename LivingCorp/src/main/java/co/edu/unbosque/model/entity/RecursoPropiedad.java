package co.edu.unbosque.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PROPERTY_RESOURCES")
public class RecursoPropiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROP_RES_ID")
    private int idRecursoPropiedad;

    @ManyToOne
    @JoinColumn(name = "RESOURCE_ID")
    private Recurso recurso;

    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Propiedad propiedad;

    @Column(name = "RESOURCE_MIN_PRICE")
    private double precioMinimo;

    @Column(name = "RESOURCE_MIN_TIME_HRS")
    private int tiempoMinimo;

    @Column(name = "RESOURCE_AVAILABILITY")
    private String disponibilidadRecurso;

    @Column(name = "RESOURCE_CAPACITY")
    private int capacidadRecurso;

    @Column(name = "BOOKING_EMAIL")
    private String emailResponsable;

    public RecursoPropiedad() {

    }

    public RecursoPropiedad(Recurso recurso, Propiedad propiedad, double precioMinimo, int tiempoMinimo, String disponibilidadRecurso, int capacidadRecurso, String emailResponsable) {
        this.recurso = recurso;
        this.propiedad = propiedad;
        this.precioMinimo = precioMinimo;
        this.tiempoMinimo = tiempoMinimo;
        this.disponibilidadRecurso = disponibilidadRecurso;
        this.capacidadRecurso = capacidadRecurso;
        this.emailResponsable = emailResponsable;
    }

    // Getters y Setters
    public int getIdRecursoPropiedad() {
        return idRecursoPropiedad;
    }

    public void setIdRecursoPropiedad(int idRecursoPropiedad) {
        this.idRecursoPropiedad = idRecursoPropiedad;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public double getPrecioMinimo() {
        return precioMinimo;
    }

    public void setPrecioMinimo(double precioMinimo) {
        this.precioMinimo = precioMinimo;
    }

    public int getTiempoMinimo() {
        return tiempoMinimo;
    }

    public void setTiempoMinimo(int tiempoMinimo) {
        this.tiempoMinimo = tiempoMinimo;
    }

    public String getDisponibilidadRecurso() {
        return disponibilidadRecurso;
    }

    public void setDisponibilidadRecurso(String disponibilidadRecurso) {
        this.disponibilidadRecurso = disponibilidadRecurso;
    }

    public int getCapacidadRecurso() {
        return capacidadRecurso;
    }

    public void setCapacidadRecurso(int capacidadRecurso) {
        this.capacidadRecurso = capacidadRecurso;
    }

    public String getEmailResponsable() {
        return emailResponsable;
    }

    public void setEmailResponsable(String emailResponsable) {
        this.emailResponsable = emailResponsable;
    }
}
