package co.edu.unbosque.serviciosapi.exceptions;

public class ProveedorNotFoundException extends RuntimeException{

    public ProveedorNotFoundException(String mensaje){
        super(mensaje);
    }
}
