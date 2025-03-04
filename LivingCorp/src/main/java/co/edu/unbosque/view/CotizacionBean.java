package co.edu.unbosque.view;

import co.edu.unbosque.exceptions.CotizacionException;
import co.edu.unbosque.model.dto.CotizacionServicioDTO;
import co.edu.unbosque.model.dto.PropiedadDTO;
import co.edu.unbosque.model.dto.ProveedorServicioDTO;
import co.edu.unbosque.model.dto.UsuarioDTO;
import co.edu.unbosque.service.ProveedorAPIService;
import co.edu.unbosque.service.ServicioCotizacion;
import co.edu.unbosque.service.ServicioPropiedad;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
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
public class CotizacionBean implements Serializable {

    @Inject
    private ServicioCotizacion servicioCotizacion;
    @Inject
    private ProveedorAPIService proveedorAPIService;
    @Inject
    private ServicioPropiedad servicioPropiedad;
    private CotizacionServicioDTO cotizacionServicioDTO;
    private List<ProveedorServicioDTO> listaProveedores;
    private List<PropiedadDTO> listaPropiedades;
    private String nombreUsuario;
    private int idPropiedad;
    private int idProveedor;

    @PostConstruct
    public void init() {
        cotizacionServicioDTO = new CotizacionServicioDTO();
        cotizacionServicioDTO.setFechaHoraSolicitud(LocalDateTime.now());
        setNombreUsuario();
        cargarProveedores();
        cargarPropiedades();
    }

    public void crearCotizacion() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            servicioCotizacion.crearCotizacion(cotizacionServicioDTO, nombreUsuario, idPropiedad, idProveedor);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cotización creada correctamente."));
        } catch (CotizacionException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void cargarProveedores() {
        listaProveedores = proveedorAPIService.listarProveedores();
    }

    public void cargarPropiedades(){
        listaPropiedades = servicioPropiedad.listarPropiedades();
    }

    private void setNombreUsuario() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("user");
        this.nombreUsuario = usuarioDTO.getNombreUsuario();
    }

    // Getters y setters
    public List<ProveedorServicioDTO> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(List<ProveedorServicioDTO> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public List<PropiedadDTO> getListaPropiedades() {
        return listaPropiedades;
    }

    public void setListaPropiedades(List<PropiedadDTO> listaPropiedades) {
        this.listaPropiedades = listaPropiedades;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
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

    public CotizacionServicioDTO getCotizacionServicioDTO() {
        return cotizacionServicioDTO;
    }

    public void setCotizacionServicioDTO(CotizacionServicioDTO cotizacionServicioDTO) {
        this.cotizacionServicioDTO = cotizacionServicioDTO;
    }
}