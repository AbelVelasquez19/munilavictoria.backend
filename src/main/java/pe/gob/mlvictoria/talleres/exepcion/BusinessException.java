package pe.gob.mlvictoria.talleres.exepcion;

public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
