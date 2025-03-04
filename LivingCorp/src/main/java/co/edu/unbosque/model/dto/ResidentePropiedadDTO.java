package co.edu.unbosque.model.dto;

public class ResidentePropiedadDTO {

    private int idResidentePropiedad;
    private PropiedadDTO propiedad;
    private UsuarioDTO usuario;
    private boolean residente;

    public ResidentePropiedadDTO() {

    }

    public ResidentePropiedadDTO(int idResidentePropiedad, PropiedadDTO propiedad, UsuarioDTO usuario, boolean residente) {
        this.idResidentePropiedad = idResidentePropiedad;
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

    public PropiedadDTO getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadDTO propiedad) {
        this.propiedad = propiedad;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public boolean isResidente() {
        return residente;
    }

    public void setResidente(boolean residente) {
        this.residente = residente;
    }
}
