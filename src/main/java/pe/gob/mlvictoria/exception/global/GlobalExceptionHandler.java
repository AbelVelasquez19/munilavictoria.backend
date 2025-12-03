package pe.gob.mlvictoria.exception.global;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pe.gob.mlvictoria.talleres.dto.ApiResponse;
import pe.gob.mlvictoria.talleres.exepcion.BusinessException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 400,
                "error", "Bad Request",
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler({PersistenceException.class, DataAccessException.class, SQLException.class})
    public ResponseEntity<?> handleSqlErrors(Exception ex) {
        String mensaje = obtenerMensajeSQL(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 400,
                "error", "Bad Request",
                "message", mensaje
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 500,
                "error", "Internal Server Error",
                "message", "Ocurrió un error inesperado. Contacte al administrador."
        ));
    }

    private String obtenerMensajeSQL(Exception ex) {
        String mensaje = ex.getMessage();
        if (mensaje != null && mensaje.contains("CODIGO DE CONTRIBUYENTE")) {
            return "Código de contribuyente o documento de identificación inválido.";
        }
        return "Error en la operación de base de datos: " + mensaje;
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException ex) {
        return ResponseEntity.status(400).body(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "fail",
                        ex.getMessage(),
                        null
                )
        );
    }

}
