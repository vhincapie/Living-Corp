package co.edu.unbosque.service;

import co.edu.unbosque.exceptions.RecursoPropiedadException;
import co.edu.unbosque.model.SendEmail;
import co.edu.unbosque.model.dto.RecursoPropiedadDTO;
import co.edu.unbosque.model.entity.Propiedad;
import co.edu.unbosque.model.entity.Recurso;
import co.edu.unbosque.model.entity.RecursoPropiedad;
import co.edu.unbosque.model.persistence.GenericDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ServicioRecursoPropiedad implements Serializable {

    private static final Logger logger = Logger.getLogger(ServicioRecursoPropiedad.class);

    @Inject
    private GenericDAO<RecursoPropiedad, Integer> recursoPropiedadDAO;
    @Inject
    private GenericDAO<Propiedad, Integer> propiedadDAO;
    @Inject
    private GenericDAO<Recurso, Integer> recursoDAO;
    private ModelMapper modelMapper;

    public ServicioRecursoPropiedad() {
        modelMapper = new ModelMapper();
    }

    public RecursoPropiedadDTO crearRecursoPropiedad(RecursoPropiedadDTO recursoPropiedadDTO, int idRecurso, int idPropiedad) throws RecursoPropiedadException {
        logger.info("Iniciando el proceso de creación de un recurso en una propiedad.");

        Recurso recursoCreado = recursoDAO.leer(idRecurso);
        validarRecurso(recursoCreado);

        Propiedad propiedadCreada = propiedadDAO.leer(idPropiedad);
        validarPropiedad(propiedadCreada);

        RecursoPropiedad recursoPropiedad = modelMapper.map(recursoPropiedadDTO, RecursoPropiedad.class);
        recursoPropiedad.setRecurso(recursoCreado);
        recursoPropiedad.setPropiedad(propiedadCreada);

        recursoPropiedad = recursoPropiedadDAO.crear(recursoPropiedad);
        logger.info("Recurso propiedad creado correctamente.");

        enviarCorreo(recursoPropiedad);

        return modelMapper.map(recursoPropiedad, RecursoPropiedadDTO.class);
    }

    public void validarRecurso(Recurso recursoCreado) throws RecursoPropiedadException {
        if (recursoCreado == null) {
            logger.error("El recurso no existe.");
            throw new RecursoPropiedadException("El recurso no existe.");
        }
    }

    public void validarPropiedad(Propiedad propiedadCreada) throws RecursoPropiedadException {
        if (propiedadCreada == null) {
            logger.error("La propiedad no existe.");
            throw new RecursoPropiedadException("La propiedad no existe.");
        }
    }

    public void enviarCorreo(RecursoPropiedad recursoPropiedad){
        SendEmail sendEmail = new SendEmail();
        String to = recursoPropiedad.getEmailResponsable();
        String subject = "Confirmación de Creación de Recurso";
        String content = "Estimado Administrador,\n\n" +
                "Esperamos que este mensaje le encuentre bien.\n\n" +
                "Nos complace informarle que se ha creado el recurso " + recursoPropiedad.getRecurso().getTipoRecurso() + " de manera exitosa.\n\n" +
                "Detalles del recurso:\n\n" +
                "  Propiedad donde se encuentra el recurso: " + recursoPropiedad.getPropiedad().getNombrePropiedad() + "\n" +
                "  Costo mínimo de reserva: " + recursoPropiedad.getPrecioMinimo() + "\n" +
                "  Tiempo mínimo de reserva: " + recursoPropiedad.getTiempoMinimo() + "\n\n" +
                "Gracias por confiar en nuestro servicio.\n\n" +
                "Atentamente,\n\n" +
                "[Servicios S.A.S]\n\n" +
                "Equipo de Soporte de [Living Corp].";

        sendEmail.sendEmail(to, subject, content);
        logger.info("Correo de confirmación enviado al responsable del recurso.");
    }

    public List<RecursoPropiedadDTO> listarRecursosPropiedad() {
        logger.info("Listando todos los recursos propiedad.");

        List<RecursoPropiedad> recursosPropiedades = recursoPropiedadDAO.obtenerTodos();
        List<RecursoPropiedadDTO> recursoPropiedadDTOs = recursosPropiedades.stream()
                .map(recursoPropiedad -> modelMapper.map(recursoPropiedad, RecursoPropiedadDTO.class))
                .collect(Collectors.toList());

        logger.info("Listado de recursos propiedad completado.");
        return recursoPropiedadDTOs;
    }
}
