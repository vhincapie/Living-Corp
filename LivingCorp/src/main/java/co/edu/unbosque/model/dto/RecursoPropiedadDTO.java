package co.edu.unbosque.model.dto;

public class RecursoPropiedadDTO {

    private int idRecursoPropiedad;
    private RecursoDTO recurso;
    private PropiedadDTO propiedad;
    private double precioMinimo;
    private int tiempoMinimo;
    private String disponibilidadRecurso;
    private int capacidadRecurso;
    private String emailResponsable;

    public RecursoPropiedadDTO() {

    }

    public RecursoPropiedadDTO(int idRecursoPropiedad, RecursoDTO recurso, PropiedadDTO propiedad, double precioMinimo, int tiempoMinimo, String disponibilidadRecurso, int capacidadRecurso, String emailResponsable) {
        this.idRecursoPropiedad = idRecursoPropiedad;
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

    public RecursoDTO getRecurso() {
        return recurso;
    }

    public void setRecurso(RecursoDTO recurso) {
        this.recurso = recurso;
    }

    public PropiedadDTO getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadDTO propiedad) {
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
