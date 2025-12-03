package pe.gob.mlvictoria.pagolinea.dto.niuviz;

import com.fasterxml.jackson.annotation.JsonRawValue;

import java.math.BigDecimal;

public record ReciboResultDTO(
        Long id,
        String purchaseNumber,
        String codigo,// ← String
        BigDecimal amount,
        String currency,
        String status,
        String ordenanza,
        @JsonRawValue String detaData,
        @JsonRawValue String authRaw,
        String createdAt,
        String updatedAt
) {}