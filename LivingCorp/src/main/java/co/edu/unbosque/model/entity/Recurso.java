package co.edu.unbosque.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "RESOURCES")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESOURCE_ID")
    private int idRecurso;

    @Column(name = "RESOURCE_DESCRIPCION")
    private String descripcionRecurso;

    @Column(name = "RESOURCE_TYPE")
    private String tipoRecurso;

    public Recurso() {

    }

    public Recurso(String descripcionRecurso, String tipoRecurso) {
        this.descripcionRecurso = descripcionRecurso;
        this.tipoRecurso = tipoRecurso;
    }

    // Getters y Setters
    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getDescripcionRecurso() {
        return descripcionRecurso;
    }

    public void setDescripcionRecurso(String descripcionRecurso) {
        this.descripcionRecurso = descripcionRecurso;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }
}
