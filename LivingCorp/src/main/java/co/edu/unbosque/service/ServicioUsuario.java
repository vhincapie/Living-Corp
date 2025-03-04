package co.edu.unbosque.service;

import co.edu.unbosque.exceptions.CrearUsuarioException;
import co.edu.unbosque.model.SendEmail;
import co.edu.unbosque.model.entity.Usuario;
import co.edu.unbosque.model.dto.UsuarioDTO;
import co.edu.unbosque.model.persistence.GenericDAO;
import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ServicioUsuario implements Serializable {

    private static final Logger logger = Logger.getLogger(ServicioUsuario.class);

    @Inject
    private GenericDAO<Usuario, String> usuarioDAO;
    private ModelMapper modelMapper;
    private int contadorContrasenia;

    public ServicioUsuario() {
        modelMapper = new ModelMapper();
        contadorContrasenia = 0;
    }

    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) throws CrearUsuarioException {
        logger.info("Creando nuevo usuario");
        validarUsuario(usuarioDTO.getCorreoUsuario(), usuarioDTO.getNombreUsuario());

        String contraseniaCifrada;
        try {
            contraseniaCifrada = cifrarContrasenia(usuarioDTO.getContraseniaUsuario());
        } catch (NoSuchAlgorithmException e) {
            throw new CrearUsuarioException("Error al cifrar la contraseña.");
        }

        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuario.setContraseniaUsuario(contraseniaCifrada);
        usuarioDAO.crear(usuario);
        logger.info("Usuario creado correctamente.");

        enviarCorreo(usuario, usuarioDTO);

        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public void enviarCorreo(Usuario usuario, UsuarioDTO usuarioDTO){
        SendEmail sendEmail = new SendEmail();
        String to = usuario.getCorreoUsuario();
        String subject = "Confirmación de Creación de Usuario";
        String content = "Estimado/a " + usuario.getNombreUsuario() + ",\n\n" +
                "Esperamos que este mensaje le encuentre bien.\n\n" +
                "Nos complace informarle que se ha creado su usuario en LivingCorp de manera exitosa.\n\n" +
                "Detalles del usuario:\n\n" +
                "  Usuario: " +  usuario.getNombreUsuario() + "\n" +
                "  Contraseña: " +  usuarioDTO.getContraseniaUsuario() + "\n\n" +
                "Gracias por confiar en nuestro servicio.\n\n" +
                "Atentamente,\n\n" +
                "[Servicios S.A.S]\n\n" +
                "Equipo de Soporte de [Living Corp].";

        sendEmail.sendEmail(to, subject, content);
        logger.info("Correo de confirmación enviado al usuario.");
    }

    public void validarUsuario(String correoUsuario, String nombreUsuario) throws CrearUsuarioException {
        logger.info("Validando nuevo usuario.");
        List<Usuario> usuarios = usuarioDAO.obtenerTodos();

        boolean correoExistente = usuarios.stream()
                .anyMatch(usuario -> usuario.getCorreoUsuario().equalsIgnoreCase(correoUsuario));

        if (correoExistente) {
            logger.error("Ya existe un usuario con el mismo correo electrónico.");
            throw new CrearUsuarioException("Ya existe un usuario con el mismo correo electrónico.");
        }

        boolean nombreExistente = usuarios.stream()
                .anyMatch(usuario -> usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario));

        if (nombreExistente) {
            logger.error("Ya existe un usuario con el mismo nombre de usuario.");
            throw new CrearUsuarioException("Ya existe un usuario con el mismo nombre de usuario.");
        }
    }

    public UsuarioDTO leerUsuario(String nombreUsuario) {
        logger.info("Buscando usuario por nombre.");
        Usuario usuario = usuarioDAO.leer(nombreUsuario);
        if (usuario == null) {
            logger.warn("El usuario no existe.");
            return null;
        }
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) {
        logger.info("Actualizando usuario.");
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuarioDAO.actualizar(usuario);
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public List<UsuarioDTO> listarUsuarios() {
        logger.info("Listando todos los usuarios.");
        List<Usuario> usuarios = usuarioDAO.obtenerTodos();
        List<UsuarioDTO> usuariosDTO = usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
        logger.info("Usuarios listados exitosamente.");
        return usuariosDTO;
    }

    public void bloquearUsuario(String nombreUsuario) {
        logger.info("Bloqueando usuario.");
        Usuario usuario = usuarioDAO.leer(nombreUsuario);
        if (usuario != null) {
            usuario.setBloqueado(true);
            usuarioDAO.actualizar(usuario);
            logger.info("Usuario bloqueado exitosamente.");
        } else {
            logger.warn("No se encontró el usuario.");
        }
    }

    public String cifrarContrasenia(String contrasenia) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytesCifrados = digest.digest(contrasenia.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : bytesCifrados) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public boolean validarCredenciales(UsuarioDTO usuarioDTO) {
        UsuarioDTO usuario = leerUsuario(usuarioDTO.getNombreUsuario());
        if (usuario == null) {
            return false;
        }

        String contraseniaCifrada;
        try {
            contraseniaCifrada = cifrarContrasenia(usuarioDTO.getContraseniaUsuario());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return usuario.getContraseniaUsuario().equals(contraseniaCifrada);
    }

    public void iniciarSesion(UsuarioDTO usuario, ExternalContext externalContext) throws IOException {
        logger.info("Iniciando sesión.");
        HttpSession session = (HttpSession) externalContext.getSession(true);
        session.setAttribute("rol", usuario.isAdministradorPropiedad() ? "administrador" : "cliente");
        session.setAttribute("user", usuario);

        String redirectPage = usuario.isAdministradorPropiedad() ? "panel_administrador.xhtml" : "panel_cliente.xhtml";
        logger.info("Redirigiendo a la página: " + redirectPage);
        externalContext.redirect(redirectPage);
    }

    public void manejarSesionExitosa(UsuarioDTO usuarioDTO, ExternalContext externalContext) throws IOException {
        contadorContrasenia = 0;
        UsuarioDTO usuario = leerUsuario(usuarioDTO.getNombreUsuario());
        if (usuario != null) {
            actualizarUltimoInicioSesion(usuario);
            iniciarSesion(usuario, externalContext);
            logger.info("Sesión iniciada correctamente.");
        } else {
            logger.warn("El usuario no existe.");
        }
    }

    private void actualizarUltimoInicioSesion(UsuarioDTO usuario) {
        usuario.setUltimoInicioSesion(LocalDateTime.now());
        actualizarUsuario(usuario);
    }

    public void manejarErrorDeSesion(String nombreUsuario) {
        contadorContrasenia++;
        if (contadorContrasenia >= 3) {
            bloquearUsuario(nombreUsuario);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cuenta bloqueada. Contacte al administrador."));
            logger.info("La cuenta de usuario ha sido bloqueada debido a múltiples intentos fallidos.");
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Credenciales inválidas."));
            logger.info("Intento de inicio de sesión fallido: credenciales inválidas.");
        }
    }
}
