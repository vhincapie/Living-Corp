package co.edu.unbosque.model.dto;

public class RecursoDTO {

    private int idRecurso;
    private String descripcionRecurso;
    private String tipoRecurso;

    public RecursoDTO() {

    }

    public RecursoDTO(int idRecurso, String descripcionRecurso, String tipoRecurso) {
        this.idRecurso = idRecurso;
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
