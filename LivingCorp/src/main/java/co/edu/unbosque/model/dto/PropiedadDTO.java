package co.edu.unbosque.model.dto;

public class PropiedadDTO {

    private int idPropiedad;
    private String nombrePropiedad;
    private String ciudadPropiedad;
    private String direccionPropiedad;
    private int areaPropiedad;
    private double precioPropiedad;
    private int habitacionPropiedad;
    private int banioPropiedad;
    private String descripcionPropiedad;
    private UsuarioDTO administradorPropiedad;
    private boolean disponibleParaAlquiler;
    private boolean disponibleParaVenta;

    public PropiedadDTO() {

    }

    public PropiedadDTO(int idPropiedad, String nombrePropiedad, String ciudadPropiedad, String direccionPropiedad, int areaPropiedad, double precioPropiedad, int habitacionPropiedad, int banioPropiedad, String descripcionPropiedad, UsuarioDTO administradorPropiedad, boolean disponibleParaAlquiler, boolean disponibleParaVenta) {
        this.idPropiedad = idPropiedad;
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

    public UsuarioDTO getAdministradorPropiedad() {
        return administradorPropiedad;
    }

    public void setAdministradorPropiedad(UsuarioDTO administradorPropiedad) {
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
