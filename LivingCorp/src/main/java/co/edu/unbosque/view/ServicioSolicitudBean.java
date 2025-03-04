package co.edu.unbosque.view;

import co.edu.unbosque.exceptions.SolicitudServicioException;
import co.edu.unbosque.model.dto.PropiedadDTO;
import co.edu.unbosque.model.dto.ProveedorServicioDTO;
import co.edu.unbosque.model.dto.SolicitudServicioDTO;
import co.edu.unbosque.model.dto.UsuarioDTO;
import co.edu.unbosque.service.ProveedorAPIService;
import co.edu.unbosque.service.ServicioPropiedad;
import co.edu.unbosque.service.ServicioSolicitudServicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named
@ViewScoped
public class ServicioSolicitudBean implements Serializable {

    @Inject
    private ServicioSolicitudServicio solicitudServicio;
    @Inject
    private ServicioPropiedad servicioPropiedad;
    @Inject
    private ProveedorAPIService proveedorAPIService;
    private SolicitudServicioDTO solicitudServicioDTO;
    private List<ProveedorServicioDTO> listaProveedores;
    private List<PropiedadDTO> listaPropiedades;
    private String nombreUsuario;
    private int idPropiedad;
    private int idProveedor;
    private LocalDateTime fechaMinima;

    @PostConstruct
    public void init () {
        solicitudServicioDTO = new SolicitudServicioDTO();
        solicitudServicioDTO.setFechaHoraSolicitud(LocalDateTime.now());
        fechaMinima = LocalDateTime.now();
        setNombreUsuario();
        cargarPropiedades();
        cargarProveedores();
    }

    public void crearSolicitud() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            solicitudServicio.solicitarServicio(solicitudServicioDTO, nombreUsuario, idPropiedad, idProveedor);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Solicitud creada correctamente."));
        } catch (SolicitudServicioException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void cargarPropiedades(){
        listaPropiedades = servicioPropiedad.listarPropiedades();
    }

    public void cargarProveedores(){
        listaProveedores = proveedorAPIService.listarProveedores();
    }

    //Getters y setters
    public SolicitudServicioDTO getSolicitudServicioDTO() {
        return solicitudServicioDTO;
    }

    public void setSolicitudServicioDTO(SolicitudServicioDTO solicitudServicioDTO) {
        this.solicitudServicioDTO = solicitudServicioDTO;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("user");
        nombreUsuario = usuarioDTO.getNombreUsuario();
    }

    public LocalDateTime getFechaMinima() {
        return fechaMinima;
    }

    public void setFechaMinima(LocalDateTime fechaMinima) {
        this.fechaMinima = fechaMinima;
    }

    public List<PropiedadDTO> getListaPropiedades() {
        return listaPropiedades;
    }

    public void setListaPropiedades(List<PropiedadDTO> listaPropiedades) {
        this.listaPropiedades = listaPropiedades;
    }

    public int getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public List<ProveedorServicioDTO> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(List<ProveedorServicioDTO> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }
}
