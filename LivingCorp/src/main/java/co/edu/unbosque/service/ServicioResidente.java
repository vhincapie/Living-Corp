package co.edu.unbosque.service;

import co.edu.unbosque.exceptions.ResidentePropiedadException;
import co.edu.unbosque.model.dto.ResidentePropiedadDTO;
import co.edu.unbosque.model.entity.Propiedad;
import co.edu.unbosque.model.entity.ResidentePropiedad;
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
public class ServicioResidente implements Serializable {

    private static final Logger logger = Logger.getLogger(ServicioResidente.class);

    @Inject
    private GenericDAO<ResidentePropiedad, Integer> residentePropiedadDAO;
    @Inject
    private GenericDAO<Propiedad, Integer> propiedadDAO;
    @Inject
    private GenericDAO<Usuario, String> usuarioDAO;
    private ModelMapper modelMapper;

    public ServicioResidente() {
        modelMapper = new ModelMapper();
    }

    public ResidentePropiedadDTO asociarResidenteConPropiedad(ResidentePropiedadDTO residentePropiedadDTO, Integer idPropiedad, String nombreUsuario) throws ResidentePropiedadException {
        logger.info("Iniciando el proceso de asociar residente con propiedad.");

        Propiedad propiedad = propiedadDAO.leer(idPropiedad);
        Usuario usuario = usuarioDAO.leer(nombreUsuario);

        if (propiedad != null && usuario != null) {
            ResidentePropiedad residentePropiedad = modelMapper.map(residentePropiedadDTO, ResidentePropiedad.class);

            residentePropiedad.setPropiedad(propiedad);
            residentePropiedad.setUsuario(usuario);

            residentePropiedadDAO.crear(residentePropiedad);
            logger.info("Residente asociado con propiedad correctamente.");

            return modelMapper.map(residentePropiedad, ResidentePropiedadDTO.class);
        } else {
            logger.error("No se encontró la propiedad o el usuario correspondiente.");
            throw new ResidentePropiedadException("No se encontró la propiedad o el usuario correspondiente.");
        }
    }

    public List<ResidentePropiedadDTO> listarResidentes(){
        logger.info("Listando residentes.");

        List<ResidentePropiedad> residentes = residentePropiedadDAO.obtenerTodos();
        return residentes.stream()
                .map(residente -> modelMapper.map(residente, ResidentePropiedadDTO.class)).
                collect(Collectors.toList());
    }

}
