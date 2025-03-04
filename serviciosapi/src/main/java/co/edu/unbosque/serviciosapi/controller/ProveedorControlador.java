package co.edu.unbosque.serviciosapi.controller;

import co.edu.unbosque.serviciosapi.model.dto.ProveedorDTO;
import co.edu.unbosque.serviciosapi.service.ServicioProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProveedorControlador implements ProveedorAPI{

    @Autowired
    private ServicioProveedor servicioProveedor;

    @Override
    public ResponseEntity<ProveedorDTO> crearProveedor(ProveedorDTO proveedor) {
        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(servicioProveedor.crearProveedor(proveedor));
    }

    @Override
    public ResponseEntity<List<ProveedorDTO>> listarProveedores() {
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(servicioProveedor.listarProveedor());
    }

    @Override
    public ResponseEntity<ProveedorDTO> obtenerProveedorPorId(int id) {
        ProveedorDTO proveedorDTO = servicioProveedor.buscarProveedorPorId(id);
        if (proveedorDTO != null) {
            return ResponseEntity
                    .status(HttpStatus.OK.value())
                    .body(proveedorDTO);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND.value())
                    .build();
        }
    }
}
