package edu.escuelaing.arsw.app.OperationService;

public class OperationServiceException extends Exception{
    public static final String MAL_FORMED_FUNCTION = "La funcion ingresada no es valida.";

    public OperationServiceException(String msg){
        super(msg);
    }

}
