package co.edu.unbosque.view;

import co.edu.unbosque.exceptions.ReservarRecursoException;
import co.edu.unbosque.model.dto.ReservaRecursoDTO;
import co.edu.unbosque.model.EstadisticaRecurso;
import co.edu.unbosque.model.dto.UsuarioDTO;
import co.edu.unbosque.service.ServicioReservaRecurso;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named
@ViewScoped
public class ReservaRecursoBean implements Serializable {

    @Inject
    private ServicioReservaRecurso servicioReservaRecurso;
    @Inject
    private LoginBean loginBean;
    private ReservaRecursoDTO reservaRecursoDTO;
    private int idRecursoPropiedadSeleccionado;
    private String nombreUsuario;
    private double costoReserva;
    private LocalDateTime fechaMinima;
    private List<EstadisticaRecurso> estadisticaRecursos;

    @PostConstruct
    public void init() {
        reservaRecursoDTO = new ReservaRecursoDTO();
        reservaRecursoDTO.setFechaHoraReserva(LocalDateTime.now());
        fechaMinima = LocalDateTime.now();
        cargarEstadisticaRecursos();
    }

    public void agendarRecurso() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("user");

            nombreUsuario = usuarioDTO.getNombreUsuario();

            double costoCalculado = servicioReservaRecurso.calcularCostoReserva(reservaRecursoDTO.getFechaHoraInicioReserva(), reservaRecursoDTO.getFechaHoraFinReserva());
            setCostoReserva(costoCalculado);

            servicioReservaRecurso.agendarReserva(reservaRecursoDTO, idRecursoPropiedadSeleccionado, nombreUsuario);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Reserva agendada correctamente."));
        } catch (ReservarRecursoException e) {

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void actualizarCosto() {
        if (reservaRecursoDTO.getFechaHoraInicioReserva() != null && reservaRecursoDTO.getFechaHoraFinReserva() != null) {
            double costoCalculado = servicioReservaRecurso.calcularCostoReserva(reservaRecursoDTO.getFechaHoraInicioReserva(), reservaRecursoDTO.getFechaHoraFinReserva());
            setCostoReserva(costoCalculado);
        }
    }

    private void cargarEstadisticaRecursos() {
        estadisticaRecursos = servicioReservaRecurso.estadisticaRecursos();
    }

    // Getters y setters
    public double getCostoReserva() {
        return costoReserva;
    }

    public void setCostoReserva(double costoReserva) {
        this.costoReserva = costoReserva;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdRecursoPropiedadSeleccionado() {
        return idRecursoPropiedadSeleccionado;
    }

    public void setIdRecursoPropiedadSeleccionado(int idRecursoPropiedadSeleccionado) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("user");
        nombreUsuario = usuarioDTO.getNombreUsuario();
        this.idRecursoPropiedadSeleccionado = idRecursoPropiedadSeleccionado;
    }

    public ReservaRecursoDTO getReservaRecursoDTO() {
        return reservaRecursoDTO;
    }

    public void setReservaRecursoDTO(ReservaRecursoDTO reservaRecursoDTO) {
        this.reservaRecursoDTO = reservaRecursoDTO;
    }

    public LocalDateTime getFechaMinima() {
        return fechaMinima;
    }

    public void setFechaMinima(LocalDateTime fechaMinima) {
        this.fechaMinima = fechaMinima;
    }

    public List<EstadisticaRecurso> getEstadisticaRecursos() {
        return estadisticaRecursos;
    }

    public void setEstadisticaRecursos(List<EstadisticaRecurso> estadisticaRecursos) {
        this.estadisticaRecursos = estadisticaRecursos;
    }
}
