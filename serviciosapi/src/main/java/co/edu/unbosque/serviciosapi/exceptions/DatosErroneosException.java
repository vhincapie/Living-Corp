package co.edu.unbosque.serviciosapi.exceptions;

public class DatosErroneosException extends RuntimeException{

    public DatosErroneosException(String mensaje) {
        super(mensaje);
    }
}
