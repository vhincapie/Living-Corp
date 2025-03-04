package co.edu.unbosque.service;

import co.edu.unbosque.exceptions.AgendarCitaException;
import co.edu.unbosque.model.entity.CitaVisita;
import co.edu.unbosque.model.dto.CitaVisitaDTO;
import co.edu.unbosque.model.entity.Propiedad;
import co.edu.unbosque.model.persistence.GenericDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ServicioCita {

    private static final Logger logger = Logger.getLogger(ServicioCita.class);

    @Inject
    private GenericDAO<CitaVisita, Integer> citaDAO;
    @Inject
    private GenericDAO<Propiedad, Integer> propiedadDAO;
    private ModelMapper modelMapper;

    public ServicioCita() {
        modelMapper = new ModelMapper();
    }

    public void agendarCita(CitaVisitaDTO citaDTO, int idPropiedad) throws AgendarCitaException {
        logger.info("Iniciando el proceso de agendar una cita.");

        CitaVisita cita = modelMapper.map(citaDTO, CitaVisita.class);

        Propiedad propiedad = propiedadDAO.leer(idPropiedad);
        if (propiedad == null) {
            logger.error("La propiedad no existe.");
            throw new AgendarCitaException("La propiedad con el ID proporcionado no existe.");
        }

        logger.info("Propiedad encontrada.");
        cita.setPropiedad(propiedad);

        validarCita(cita);

        citaDAO.crear(cita);
        logger.info("Cita agendada correctamente.");
    }

    public void validarCita(CitaVisita cita) throws AgendarCitaException {
        logger.info("Validando la cita.");

        List<CitaVisita> citasExistente = citaDAO.obtenerTodos()
                .stream()
                .filter(citaVisita -> citaVisita.getFechaHoraCita().isEqual(cita.getFechaHoraCita()) && citaVisita.getPropiedad().getIdPropiedad() == cita.getPropiedad().getIdPropiedad())
                .collect(Collectors.toList());

        if (!citasExistente.isEmpty()) {
            logger.warn("Ya existe una cita para la fecha y hora seleccionadas.");
            throw new AgendarCitaException("Ya existe una cita para la fecha y hora seleccionadas en esta propiedad.");
        }

        logger.info("La cita ha sido validada correctamente.");
    }
}
