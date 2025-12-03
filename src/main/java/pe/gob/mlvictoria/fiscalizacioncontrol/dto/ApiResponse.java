package pe.gob.mlvictoria.fiscalizacioncontrol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private LocalDateTime timestamp;
    private String status;
    private String message;
    private T data;
}