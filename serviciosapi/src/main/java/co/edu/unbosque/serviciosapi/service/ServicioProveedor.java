package co.edu.unbosque.serviciosapi.service;

import co.edu.unbosque.serviciosapi.exceptions.DatosErroneosException;
import co.edu.unbosque.serviciosapi.exceptions.ProveedorNotFoundException;
import co.edu.unbosque.serviciosapi.model.dto.ProveedorDTO;
import co.edu.unbosque.serviciosapi.model.entity.Proveedor;
import co.edu.unbosque.serviciosapi.repository.ProveedorRepository;
import jakarta.persistence.PersistenceException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioProveedor {

    private final ModelMapper modelMapper;
    private final ProveedorRepository proveedorRepository;

    public ServicioProveedor(ModelMapper modelMapper, ProveedorRepository proveedorRepository) {
        this.modelMapper = modelMapper;
        this.proveedorRepository = proveedorRepository;
    }

    public ProveedorDTO crearProveedor(ProveedorDTO proveedorDTO) {
        try {
            Proveedor proveedor = proveedorRepository.save(modelMapper.map(proveedorDTO, Proveedor.class));
            return modelMapper.map(proveedor, ProveedorDTO.class);
        }catch (PersistenceException e){
            throw new DatosErroneosException("No se pudo guardar los datos del proveedor, por favor verifique los datos.");
        }
    }

    public ProveedorDTO buscarProveedorPorId(int id) {
        return proveedorRepository.findById(id)
                .map(proveedor -> modelMapper.map(proveedor, ProveedorDTO.class))
                .orElseThrow(() -> new ProveedorNotFoundException("Proveedor no encontrado."));
    }

    public List<ProveedorDTO> listarProveedor() {
        List<ProveedorDTO> auxList = proveedorRepository.findAll()
                .stream()
                .map(proveedor -> modelMapper.map(proveedor, ProveedorDTO.class))
                .toList();
        if (auxList.isEmpty()) {
            throw new ProveedorNotFoundException("No se encontraron proveedores registrados.");
        }else{
            return auxList;
        }
    }
}
