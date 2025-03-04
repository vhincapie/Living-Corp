package co.edu.unbosque.service;

import co.edu.unbosque.exceptions.CrearPropiedadException;
import co.edu.unbosque.model.entity.Propiedad;
import co.edu.unbosque.model.dto.PropiedadDTO;
import co.edu.unbosque.model.entity.Usuario;
import co.edu.unbosque.model.persistence.GenericDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ServicioPropiedad implements Serializable {

    private static final Logger logger = Logger.getLogger(ServicioPropiedad.class);

    @Inject
    private GenericDAO<Propiedad, Integer> propiedadDAO;
    @Inject
    private GenericDAO<Usuario, String> usuarioDAO;
    private ModelMapper modelMapper;

    public ServicioPropiedad() {
        modelMapper = new ModelMapper();
    }

    public PropiedadDTO crearPropiedad(PropiedadDTO propiedadDTO, String adminPropiedad) throws CrearPropiedadException {
        logger.info("Iniciando el proceso de registro de una propiedad.");

        Usuario administradorPropiedad = usuarioDAO.leer(adminPropiedad);
        if (administradorPropiedad == null) {
            logger.error("El administrador de la propiedad no existe.");
            throw new CrearPropiedadException("El administrador de la propiedad no existe.");
        }

        validarNombrePropiedad(propiedadDTO.getNombrePropiedad(), propiedadDTO.getCiudadPropiedad());

        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad.setAdministradorPropiedad(administradorPropiedad);
        propiedad = propiedadDAO.crear(propiedad);
        logger.info("Propiedad creada correctamente.");

        return modelMapper.map(propiedad, PropiedadDTO.class);
    }

    public void validarNombrePropiedad(String nombrePropiedad, String ciudadPropiedad) throws CrearPropiedadException {
        logger.info("Validando nombre de la propiedad en la ciudad especificada.");

        List<Propiedad> propiedades = propiedadDAO.obtenerTodos()
                .stream()
                .filter(propiedad -> propiedad.getNombrePropiedad().equalsIgnoreCase(nombrePropiedad) &&
                        propiedad.getCiudadPropiedad().equalsIgnoreCase(ciudadPropiedad))
                .collect(Collectors.toList());

        if (!propiedades.isEmpty()) {
            logger.error("Ya existe una propiedad con el mismo nombre en la misma ciudad.");
            throw new CrearPropiedadException("Ya existe una propiedad con el mismo nombre en la misma ciudad.");
        }
        logger.info("Validación de nombre de propiedad completada.");
    }

    public List<PropiedadDTO> listarPropiedades() {
        logger.info("Listando todas las propiedades.");

        List<Propiedad> propiedades = propiedadDAO.obtenerTodos();
        List<PropiedadDTO> propiedadDTOs = propiedades.stream()
                .map(propiedad -> modelMapper.map(propiedad, PropiedadDTO.class))
                .collect(Collectors.toList());

        logger.info("Listado de propiedades completado.");
        return propiedadDTOs;
    }

    public List<PropiedadDTO> filtrarPropiedades(String ciudad, boolean disponibleParaAlquiler, boolean disponibleParaVenta) {
        logger.info("Filtrando propiedades por ciudad y disponibilidad.");

        List<Propiedad> propiedades = propiedadDAO.obtenerTodos();
        List<PropiedadDTO> propiedadDTOs = propiedades.stream()
                .filter(propiedad -> propiedad.getCiudadPropiedad().equalsIgnoreCase(ciudad))
                .filter(propiedad -> disponibleParaAlquiler ? propiedad.isDisponibleParaAlquiler() : true)
                .filter(propiedad -> disponibleParaVenta ? propiedad.isDisponibleParaVenta() : true)
                .map(propiedad -> modelMapper.map(propiedad, PropiedadDTO.class))
                .collect(Collectors.toList());

        logger.info("Filtrado de propiedades completado.");
        return propiedadDTOs;
    }
}
