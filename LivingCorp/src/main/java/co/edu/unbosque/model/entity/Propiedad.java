package co.edu.unbosque.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PROPERTIES")
public class Propiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROPERTY_ID")
    private int idPropiedad;

    @Column(name = "PROPERTY_NAME")
    private String nombrePropiedad;

    @Column(name = "PROPERTY_CITY")
    private String ciudadPropiedad;

    @Column(name = "PROPERTY_ADDRESS")
    private String direccionPropiedad;

    @Column(name = "PROPERTY_AREA")
    private int areaPropiedad;

    @Column(name = "PROPERTY_PRICE")
    private double precioPropiedad;

    @Column(name = "PROPERTY_ROOMS")
    private int habitacionPropiedad;

    @Column(name = "PROPERTY_BATHROOMS")
    private int banioPropiedad;

    @Column(name = "PROPERTY_DESCRIPCION")
    private String descripcionPropiedad;

    @ManyToOne
    @JoinColumn(name = "PROPERTY_ADMIN")
    private Usuario administradorPropiedad;

    @Column(name = "IS_AVAILABLE_FOR_RENT")
    private boolean disponibleParaAlquiler;

    @Column(name = "IS_AVAILABLE_FOR_SALE")
    private boolean disponibleParaVenta;

    public Propiedad() {

    }

    public Propiedad(String nombrePropiedad, String ciudadPropiedad, String direccionPropiedad, int areaPropiedad, double precioPropiedad, int habitacionPropiedad, int banioPropiedad, String descripcionPropiedad, Usuario administradorPropiedad, boolean disponibleParaAlquiler, boolean disponibleParaVenta) {
        this.nombrePropiedad = nombrePropiedad;
        this.ciudadPropiedad = ciudadPropiedad;
        this.direccionPropiedad = direccionPropiedad;
        this.areaPropiedad = areaPropiedad;
        this.precioPropiedad = precioPropiedad;
        this.habitacionPropiedad = habitacionPropiedad;
        this.banioPropiedad = banioPropiedad;
        this.descripcionPropiedad = descripcionPropiedad;
        this.administradorPropiedad = administradorPropiedad;
        this.disponibleParaAlquiler = disponibleParaAlquiler;
        this.disponibleParaVenta = disponibleParaVenta;
    }

    // Getters y Setters
    public int getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public String getNombrePropiedad() {
        return nombrePropiedad;
    }

    public void setNombrePropiedad(String nombrePropiedad) {
        this.nombrePropiedad = nombrePropiedad;
    }

    public String getCiudadPropiedad() {
        return ciudadPropiedad;
    }

    public void setCiudadPropiedad(String ciudadPropiedad) {
        this.ciudadPropiedad = ciudadPropiedad;
    }

    public String getDireccionPropiedad() {
        return direccionPropiedad;
    }

    public void setDireccionPropiedad(String direccionPropiedad) {
        this.direccionPropiedad = direccionPropiedad;
    }

    public int getAreaPropiedad() {
        return areaPropiedad;
    }

    public void setAreaPropiedad(int areaPropiedad) {
        this.areaPropiedad = areaPropiedad;
    }

    public double getPrecioPropiedad() {
        return precioPropiedad;
    }

    public void setPrecioPropiedad(double precioPropiedad) {
        this.precioPropiedad = precioPropiedad;
    }

    public int getHabitacionPropiedad() {
        return habitacionPropiedad;
    }

    public void setHabitacionPropiedad(int habitacionPropiedad) {
        this.habitacionPropiedad = habitacionPropiedad;
    }

    public int getBanioPropiedad() {
        return banioPropiedad;
    }

    public void setBanioPropiedad(int banioPropiedad) {
        this.banioPropiedad = banioPropiedad;
    }

    public String getDescripcionPropiedad() {
        return descripcionPropiedad;
    }

    public void setDescripcionPropiedad(String descripcionPropiedad) {
        this.descripcionPropiedad = descripcionPropiedad;
    }

    public Usuario getAdministradorPropiedad() {
        return administradorPropiedad;
    }

    public void setAdministradorPropiedad(Usuario administradorPropiedad) {
        this.administradorPropiedad = administradorPropiedad;
    }

    public boolean isDisponibleParaAlquiler() {
        return disponibleParaAlquiler;
    }

    public void setDisponibleParaAlquiler(boolean disponibleParaAlquiler) {
        this.disponibleParaAlquiler = disponibleParaAlquiler;
    }

    public boolean isDisponibleParaVenta() {
        return disponibleParaVenta;
    }

    public void setDisponibleParaVenta(boolean disponibleParaVenta) {
        this.disponibleParaVenta = disponibleParaVenta;
    }
}
