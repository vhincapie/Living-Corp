package co.edu.unbosque.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PROPERTY_RESIDENTS")
public class ResidentePropiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROPERTY_RESIDENT_ID")
    private int idResidentePropiedad;

    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Propiedad propiedad;

    @ManyToOne
    @JoinColumn(name = "USER_NAME")
    private Usuario usuario;

    @Column(name = "IS_OWNER")
    private boolean residente;

    public ResidentePropiedad() {

    }

    public ResidentePropiedad(Propiedad propiedad, Usuario usuario, boolean residente) {
        this.propiedad = propiedad;
        this.usuario = usuario;
        this.residente = residente;
    }

    // Getters y Setters
    public int getIdResidentePropiedad() {
        return idResidentePropiedad;
    }

    public void setIdResidentePropiedad(int idResidentePropiedad) {
        this.idResidentePropiedad = idResidentePropiedad;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isResidente() {
        return residente;
    }

    public void setResidente(boolean residente) {
        this.residente = residente;
    }
}
