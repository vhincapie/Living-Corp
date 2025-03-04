package co.edu.unbosque.service;

import co.edu.unbosque.exceptions.CrearRecursoException;
import co.edu.unbosque.model.dto.RecursoDTO;
import co.edu.unbosque.model.entity.Recurso;
import co.edu.unbosque.model.persistence.GenericDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ServicioRecurso implements Serializable {

    private static final Logger logger = Logger.getLogger(ServicioRecurso.class);

    @Inject
    private GenericDAO<Recurso, Integer> recursoDAO;
    private ModelMapper modelMapper;

    public ServicioRecurso() {
        modelMapper = new ModelMapper();
    }

    public RecursoDTO crearRecurso(RecursoDTO recursoDTO) throws CrearRecursoException {
        logger.info("Iniciando el proceso de creación de un recurso.");

        validarRecurso(recursoDTO);

        Recurso recurso = modelMapper.map(recursoDTO, Recurso.class);
        recurso = recursoDAO.crear(recurso);

        logger.info("Recurso creado correctamente.");

        return modelMapper.map(recurso, RecursoDTO.class);
    }

    public void validarRecurso(RecursoDTO recursoDTO) throws CrearRecursoException {
        logger.info("Validando el tipo de recurso.");

        List<Recurso> recursos = recursoDAO.obtenerTodos()
                .stream()
                .filter(recurso -> recurso.getTipoRecurso().equalsIgnoreCase(recursoDTO.getTipoRecurso()))
                .collect(Collectors.toList());

        if (!recursos.isEmpty()) {
            logger.error("Ya existe un recurso con el mismo tipo.");
            throw new CrearRecursoException("Ya existe un recurso con el mismo tipo.");
        }

        logger.info("Validación de recurso completada.");
    }

    public List<RecursoDTO> listarRecursos() {
        logger.info("Listando todos los recursos.");

        List<Recurso> recursos = recursoDAO.obtenerTodos();
        List<RecursoDTO> recursoDTOs = recursos.stream()
                .map(recurso -> modelMapper.map(recurso, RecursoDTO.class))
                .collect(Collectors.toList());

        logger.info("Listado de recursos completado.");
        return recursoDTOs;
    }
}
