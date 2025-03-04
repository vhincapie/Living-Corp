package co.edu.unbosque.service;

import co.edu.unbosque.exceptions.SolicitudServicioException;
import co.edu.unbosque.model.SendEmail;
import co.edu.unbosque.model.dto.ProveedorServicioDTO;
import co.edu.unbosque.model.dto.SolicitudServicioDTO;
import co.edu.unbosque.model.entity.Propiedad;
import co.edu.unbosque.model.entity.ProveedorServicio;
import co.edu.unbosque.model.entity.SolicitudServicio;
import co.edu.unbosque.model.entity.Usuario;
import co.edu.unbosque.model.persistence.GenericDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Stateless
public class ServicioSolicitudServicio {

    private static final Logger logger = Logger.getLogger(ServicioSolicitudServicio.class);

    @Inject
    private GenericDAO<SolicitudServicio, Integer> daoSolicitudServicio;
    @Inject
    private GenericDAO<Propiedad, Integer> daoPropiedad;
    @Inject
    private GenericDAO<Usuario, String> daoUsuario;
    @Inject
    private ProveedorAPIService proveedorAPIService;
    private ModelMapper modelMapper;


    public ServicioSolicitudServicio(){
        modelMapper = new ModelMapper();
    }

    public void solicitarServicio(SolicitudServicioDTO solicitudServicioDTO, String nombreUsuario, int idPropiedad, int idProveedor) throws SolicitudServicioException {
        logger.info("Iniciando el proceso de solicitud de servicio.");

        Usuario usuario = daoUsuario.leer(nombreUsuario);
        Propiedad propiedad = daoPropiedad.leer(idPropiedad);
        ProveedorServicioDTO proveedorDTO = proveedorAPIService.obtenerProveedorPorId(idProveedor);

        validarDiaServicio(solicitudServicioDTO.getFechaHoraServicio());

        if (propiedad == null){
            logger.error("La propiedad con el ID proporcionado no existe.");
            throw new SolicitudServicioException("La propiedad con el ID proporcionado no existe.");
        }

        SolicitudServicio servicio = modelMapper.map(solicitudServicioDTO, SolicitudServicio.class);
        servicio.setUsuario(usuario);
        servicio.setPropiedad(propiedad);
        servicio.setProveedorServicio(modelMapper.map(proveedorDTO, ProveedorServicio.class));

        daoSolicitudServicio.crear(servicio);
        logger.info("Solicitud de servicio creada correctamente.");

        enviarCorreo(servicio);
    }

    private void enviarCorreo(SolicitudServicio servicio) {
        SendEmail sendEmail = new SendEmail();
        String to = servicio.getUsuario().getCorreoUsuario();
        String subject = "Confirmación de Solicitud de Servicio";
        String content = "Estimado/a " + servicio.getUsuario().getNombreUsuario() + ",\n\n" +
                "Esperamos que este mensaje le encuentre bien.\n\n" +
                "Nos complace informarle que se ha creado la solicitud del servicio " + servicio.getProveedorServicio().getType() + " de manera exitosa.\n\n" +
                "Detalles del recurso:\n\n" +
                "  Propiedad del residente: " + servicio.getPropiedad().getNombrePropiedad() + "\n" +
                "  Fecha de solicitud del servicio: " + servicio.getFechaHoraServicio() + "\n" +
                "  Descripcion de la solicitud: " + servicio.getDescripcionSolicitud() + "\n\n" +
                "Gracias por confiar en nuestro servicio.\n\n" +
                "Atentamente,\n\n" +
                "[Servicios S.A.S]\n\n" +
                "Equipo de Soporte de [Living Corp].";

        sendEmail.sendEmail(to, subject, content);
        logger.info("Correo de confirmación enviado al usuario.");
    }

    public void validarDiaServicio(LocalDateTime inicio) throws SolicitudServicioException {
        if(inicio == null){
            logger.error("Ingrese una fecha para agendar el servicio.");
            throw new SolicitudServicioException("Ingrese una fecha para agendar el servicio");
        }
    }
}
