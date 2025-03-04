package co.edu.unbosque.view;

import co.edu.unbosque.model.dto.ProveedorServicioDTO;
import co.edu.unbosque.service.ProveedorAPIService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProveedorBean implements Serializable {

    @Inject
    private ProveedorAPIService proveedorAPIService;
    private List<ProveedorServicioDTO> proveedorServicios;

    @PostConstruct
    public void init() {
        cargarProveedores();
    }

    public void cargarProveedores(){
        proveedorServicios = proveedorAPIService.listarProveedores();
    }

    public List<ProveedorServicioDTO> getProveedorServicios() {
        return proveedorServicios;
    }

    public void setProveedorServicios(List<ProveedorServicioDTO> proveedorServicios) {
        this.proveedorServicios = proveedorServicios;
    }
}
