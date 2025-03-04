package co.edu.unbosque.service;

import co.edu.unbosque.exceptions.CotizacionException;
import co.edu.unbosque.model.SendEmail;
import co.edu.unbosque.model.dto.CotizacionServicioDTO;
import co.edu.unbosque.model.dto.ProveedorServicioDTO;
import co.edu.unbosque.model.entity.CotizacionServicio;
import co.edu.unbosque.model.entity.Propiedad;
import co.edu.unbosque.model.entity.ProveedorServicio;
import co.edu.unbosque.model.entity.Usuario;
import co.edu.unbosque.model.persistence.GenericDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;

@Stateless
public class ServicioCotizacion implements Serializable {

    private static final Logger logger = Logger.getLogger(ServicioCotizacion.class);

    @Inject
    private GenericDAO<CotizacionServicio, Integer> cotizacionDAO;
    @Inject
    private GenericDAO<Usuario, String> usuarioDAO;
    @Inject
    private GenericDAO<Propiedad, Integer> propiedadDAO;
    @Inject
    private ProveedorAPIService proveedorAPIService;
    private ModelMapper modelMapper;

    public ServicioCotizacion() {
        modelMapper = new ModelMapper();
    }

    public CotizacionServicioDTO crearCotizacion(CotizacionServicioDTO cotizacionServicioDTO, String nombreUsuario, int idPropiedad, int idProveedor) throws CotizacionException {
        logger.info("Iniciando el proceso de cotización.");

        Usuario usuario = usuarioDAO.leer(nombreUsuario);
        Propiedad propiedad = propiedadDAO.leer(idPropiedad);

        ProveedorServicioDTO proveedorDTO = proveedorAPIService.obtenerProveedorPorId(idProveedor);
        if (proveedorDTO == null) {
            logger.error("Proveedor no encontrado.");
            throw new CotizacionException("Proveedor no encontrado");
        }

        CotizacionServicio cotizacion = modelMapper.map(cotizacionServicioDTO, CotizacionServicio.class);

        cotizacion.setUsuario(usuario);
        cotizacion.setPropiedad(propiedad);
        cotizacion.setProveedorServicio(modelMapper.map(proveedorDTO, ProveedorServicio.class));

        cotizacionDAO.crear(cotizacion);
        logger.info("Cotización creada correctamente.");

        enviarCorreo(cotizacion);

        return modelMapper.map(cotizacion, CotizacionServicioDTO.class);
    }

    public void enviarCorreo(CotizacionServicio cotizacion) throws CotizacionException {
        SendEmail sendEmail = new SendEmail();
        String to = cotizacion.getProveedorServicio().getEmail();
        String subject = "Confirmación de Cotizacion de Servicio";
        String content = "Estimado Proveedor,\n\n" +
                "Esperamos que este mensaje le encuentre bien.\n\n" +
                "Nos complace informarle que se ha generado una cotización para el servicio " + cotizacion.getProveedorServicio().getType() + " de manera exitosa.\n\n" +
                "Detalles de la cotización:\n\n" +
                "  Usuario que hace la solicitud: " + cotizacion.getUsuario().getNombreUsuario() + "\n" +
                "  Propiedad del residente: " + cotizacion.getPropiedad().getNombrePropiedad() + "\n" +
                "  Fecha y Hora de Solicitud de Cotización: "+ cotizacion.getFechaHoraSolicitud() + "\n" +
                "  Descripción de la solicitud: " + cotizacion.getDescripcionSolicitud() + "\n\n" +
                "Gracias por confiar en nuestro servicio.\n\n" +
                "Atentamente,\n\n" +
                "[Servicios S.A.S]\n\n" +
                "Equipo de Soporte de [Living Corp]";

        sendEmail.sendEmail(to, subject, content);
        logger.info("Correo de confirmación enviado al proveedor.");
    }
}
