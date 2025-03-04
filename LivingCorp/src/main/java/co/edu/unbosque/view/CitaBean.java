package co.edu.unbosque.view;

import co.edu.unbosque.exceptions.AgendarCitaException;
import co.edu.unbosque.model.dto.CitaVisitaDTO;
import co.edu.unbosque.service.ServicioCita;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;

@Named
@ViewScoped
public class CitaBean implements Serializable {

    @Inject
    private ServicioCita servicioCita;
    private CitaVisitaDTO citaVisitaDTO;
    private int idPropiedadSeleccionada;
    private LocalDateTime fechaMinima;

    @PostConstruct
    public void init() {
        citaVisitaDTO = new CitaVisitaDTO();
        fechaMinima = LocalDateTime.now();
    }

    public void agendarCita() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            servicioCita.agendarCita(citaVisitaDTO, idPropiedadSeleccionada);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cita agendada correctamente."));
        } catch (AgendarCitaException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public CitaVisitaDTO getCitaVisitaDTO() {
        return citaVisitaDTO;
    }

    public void setCitaVisitaDTO(CitaVisitaDTO citaVisitaDTO) {
        this.citaVisitaDTO = citaVisitaDTO;
    }

    public int getIdPropiedadSeleccionada() {
        return idPropiedadSeleccionada;
    }

    public void setIdPropiedadSeleccionada(int idPropiedadSeleccionada) {
        this.idPropiedadSeleccionada = idPropiedadSeleccionada;
    }

    public LocalDateTime getFechaMinima() {
        return fechaMinima;
    }

    public void setFechaMinima(LocalDateTime fechaMinima) {
        this.fechaMinima = fechaMinima;
    }
}
