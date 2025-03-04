package co.edu.unbosque.service;

import co.edu.unbosque.exceptions.ReservarRecursoException;
import co.edu.unbosque.model.SendEmail;
import co.edu.unbosque.model.dto.ReservaRecursoDTO;
import co.edu.unbosque.model.EstadisticaRecurso;
import co.edu.unbosque.model.entity.RecursoPropiedad;
import co.edu.unbosque.model.entity.ReservaRecurso;
import co.edu.unbosque.model.entity.Usuario;
import co.edu.unbosque.model.persistence.GenericDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
public class ServicioReservaRecurso {

    private static final Logger logger = Logger.getLogger(ServicioReservaRecurso.class);

    @Inject
    private GenericDAO<ReservaRecurso, Integer> daoReserva;
    @Inject
    private GenericDAO<RecursoPropiedad, Integer> daoRecurso;
    @Inject
    private GenericDAO<Usuario, String> daoUsuario;

    private ModelMapper modelMapper;

    public ServicioReservaRecurso() {
        modelMapper = new ModelMapper();
    }

    public void agendarReserva(ReservaRecursoDTO reservaRecursoDTO, int idRecurso, String usuarioRecurso) throws ReservarRecursoException {
        logger.info("Iniciando el proceso de agendar una reserva.");

        validarDuracionReserva(reservaRecursoDTO.getFechaHoraInicioReserva(), reservaRecursoDTO.getFechaHoraFinReserva());
        validarDiasDeAnticipacion(reservaRecursoDTO.getFechaHoraInicioReserva());

        double costoReserva = calcularCostoReserva(reservaRecursoDTO.getFechaHoraInicioReserva(), reservaRecursoDTO.getFechaHoraFinReserva());

        ReservaRecurso reserva = modelMapper.map(reservaRecursoDTO, ReservaRecurso.class);
        RecursoPropiedad recurso = daoRecurso.leer(idRecurso);

        if (recurso == null) {
            logger.error("El recurso con el ID proporcionado no existe.");
            throw new ReservarRecursoException("El recurso con el ID proporcionado no existe.");
        }

        Usuario usuario = daoUsuario.leer(usuarioRecurso);
        reserva.setNombreUsuario(usuario);
        reserva.setRecursoPropiedad(recurso);
        reserva.setCostoReserva(costoReserva);

        validarReserva(reserva);

        daoReserva.crear(reserva);
        logger.info("Reserva creada correctamente.");

        enviarCorreo(reserva);
    }

    public void enviarCorreo(ReservaRecurso reserva){
        SendEmail sendEmail = new SendEmail();
        String to = reserva.getNombreUsuario().getCorreoUsuario();
        String subject = "Confirmación de Reserva";
        String content = "Estimado/a " + reserva.getNombreUsuario().getNombreUsuario() + ",\n\n" +
                "Esperamos que este mensaje le encuentre bien.\n\n" +
                "Nos complace informarle que su reserva para el recurso " + reserva.getRecursoPropiedad().getRecurso().getTipoRecurso() + " ha sido confirmada.\n\n" +
                "Detalles de la reserva:\n\n" +
                "  Fecha y Hora de Inicio: " + reserva.getFechaHoraInicioReserva() + "\n" +
                "  Fecha y Hora de Fin: " + reserva.getFechaHoraFinReserva() + "\n" +
                "  Costo: " + reserva.getCostoReserva() + "\n\n" +
                "Gracias por confiar en nuestro servicio.\n\n" +
                "Atentamente,\n\n" +
                "[Servicios S.A.S]\n\n" +
                "Equipo de Soporte de [Living Corp]";

        sendEmail.sendEmail(to, subject, content);
        logger.info("Correo de confirmación enviado al usuario.");
    }

    public void validarReserva(ReservaRecurso reserva) throws ReservarRecursoException {
        logger.info("Validando la reserva.");

        List<ReservaRecurso> reservasExistente = daoReserva.obtenerTodos().stream()
                .filter(reservaRecurso -> reservaRecurso.getFechaHoraInicioReserva().isEqual(reserva.getFechaHoraInicioReserva()) &&
                        reservaRecurso.getFechaHoraFinReserva().isEqual(reserva.getFechaHoraFinReserva()) &&
                        reservaRecurso.getRecursoPropiedad().getIdRecursoPropiedad() == reserva.getRecursoPropiedad().getIdRecursoPropiedad())
                .collect(Collectors.toList());

        if (!reservasExistente.isEmpty()) {
            logger.error("Ya existe una reserva para la misma fecha.");
            throw new ReservarRecursoException("Ya existe una reserva para la misma fecha.");
        }
    }

    public void validarDuracionReserva(LocalDateTime inicio, LocalDateTime fin) throws ReservarRecursoException {
        logger.info("Validando la duración de la reserva.");

        if (inicio == null || fin == null) {
            logger.error("Las fechas de inicio y fin de la reserva no pueden ser nulas.");
            throw new ReservarRecursoException("Las fechas de inicio y fin de la reserva no pueden ser nulas.");
        }

        long minutos = Duration.between(inicio, fin).toMinutes();

        if (minutos < 180) {
            logger.error("La duración mínima para reservar es de 3 horas.");
            throw new ReservarRecursoException("La duración mínima para reservar es de 3 horas.");
        }
    }

    public void validarDiasDeAnticipacion(LocalDateTime fechaInicioReserva) throws ReservarRecursoException {
        logger.info("Validando los días de anticipación de la reserva.");

        LocalDateTime fechaActual = LocalDateTime.now();
        long diasDeDiferencia = ChronoUnit.DAYS.between(fechaActual, fechaInicioReserva);

        if (diasDeDiferencia < 3) {
            logger.error("La reserva debe realizarse con al menos 3 días de anticipación.");
            throw new ReservarRecursoException("La reserva debe realizarse con al menos 3 días de anticipación.");
        }
    }

    public double calcularCostoReserva(LocalDateTime inicioReserva, LocalDateTime finReserva) {
        logger.info("Calculando el costo de la reserva.");

        long minutosReserva = Duration.between(inicioReserva, finReserva).toMinutes();
        double costoTotalTresHoras = 150000;
        double horasReserva = minutosReserva / 60.0;
        double costoTotalPorHora = costoTotalTresHoras * (horasReserva / 3.0);

        return costoTotalPorHora;
    }

    public List<EstadisticaRecurso> estadisticaRecursos() {
        logger.info("Generando estadísticas de los recursos.");

        List<ReservaRecurso> listaReservas = daoReserva.obtenerTodos();
        Map<String, EstadisticaRecurso> estadisticaRecursosMap = new HashMap<>();

        for (ReservaRecurso reserva : listaReservas) {
            String tipoRecurso = reserva.getRecursoPropiedad().getRecurso().getTipoRecurso();
            EstadisticaRecurso estadistica = estadisticaRecursosMap.getOrDefault(tipoRecurso, new EstadisticaRecurso(tipoRecurso, 0, 0));

            estadistica.setCantidadReservas(estadistica.getCantidadReservas() + 1);
            estadistica.setTiempoReservado(estadistica.getTiempoReservado() + Duration.between(reserva.getFechaHoraInicioReserva(), reserva.getFechaHoraFinReserva()).toMinutes());

            estadisticaRecursosMap.put(tipoRecurso, estadistica);
        }

        logger.info("Estadísticas de los recursos generadas.");
        return new ArrayList<>(estadisticaRecursosMap.values());
    }
}
