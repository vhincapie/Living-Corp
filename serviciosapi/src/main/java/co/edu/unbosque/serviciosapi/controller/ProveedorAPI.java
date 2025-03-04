package co.edu.unbosque.serviciosapi.controller;

import co.edu.unbosque.serviciosapi.model.dto.ProveedorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/proveedor/api")
public interface ProveedorAPI {

    @PostMapping("/proveedor")
    ResponseEntity<ProveedorDTO> crearProveedor(@RequestBody ProveedorDTO proveedor);

    @GetMapping("/proveedor")
    ResponseEntity<List<ProveedorDTO>> listarProveedores();

    @GetMapping("/proveedor/{id}")
    ResponseEntity<ProveedorDTO> obtenerProveedorPorId(@PathVariable("id") int id);
}
