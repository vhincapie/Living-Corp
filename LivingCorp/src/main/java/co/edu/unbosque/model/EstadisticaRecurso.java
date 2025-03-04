package co.edu.unbosque.model;

public class EstadisticaRecurso {

    private String tipoRecurso;
    private int cantidadReservas;
    private long tiempoReservado;

    public EstadisticaRecurso(String tipoRecurso, int cantidadReservas, long tiempoReservado) {
        this.tipoRecurso = tipoRecurso;
        this.cantidadReservas = cantidadReservas;
        this.tiempoReservado = tiempoReservado;
    }

    // Getters y Setters
    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public int getCantidadReservas() {
        return cantidadReservas;
    }

    public void setCantidadReservas(int cantidadReservas) {
        this.cantidadReservas = cantidadReservas;
    }

    public long getTiempoReservado() {
        return tiempoReservado;
    }

    public void setTiempoReservado(long tiempoReservado) {
        this.tiempoReservado = tiempoReservado;
    }
}
