package co.edu.unbosque.serviciosapi.exceptions;

import co.edu.unbosque.serviciosapi.model.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiciosExceptionHandler {

    @ExceptionHandler(DatosErroneosException.class)
    public ResponseEntity<BaseResponse> handlerDatosErroneosException(DatosErroneosException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new BaseResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(ProveedorNotFoundException.class)
    public ResponseEntity<BaseResponse> handlerNotFoundException(ProveedorNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(new BaseResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

}
