package pe.gob.mlvictoria.pagolinea.dto.pago;

import java.util.Map;

public record LogVisaDTO (
    String tramo,
    String purchase_number,
    String channel,
    String navegador,
    String url,
    String visa_url,
    String visa_merchant_id,
    String sesion_visa,
    Map<String, Object> data,
    Map<String, Object> resumen,
    Object[] detalle
){}
