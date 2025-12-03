package pe.gob.mlvictoria.pagolinea.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pe.gob.mlvictoria.config.JwtUtil;
import pe.gob.mlvictoria.pagolinea.dto.ApiResponse;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.CreateIntentReq;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.CreateIntentResponse;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.ReciboResultDTO;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.UpdateAfterAuthRe;
import pe.gob.mlvictoria.pagolinea.dto.pago.*;
import pe.gob.mlvictoria.pagolinea.dto.recibo.*;
import pe.gob.mlvictoria.pagolinea.dto.validarrecibo.ReciboRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.validarrecibo.ReciboResponseDTO;
import pe.gob.mlvictoria.pagolinea.service.NiubizService;
import pe.gob.mlvictoria.pagolinea.service.PagoLiniaService;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import pe.gob.mlvictoria.utility.VisaConfig;

@Slf4j
@RestController
@RequestMapping("/api/pago-linia")

public class PagoLiniaController {
    @Autowired
    private PagoLiniaService pagoLiniaService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${pagolinea.upload-dir}")
    private String logDirPrp;

    private final NiubizService niubizService;
    private final ObjectMapper objectMapper;
    private final VisaConfig visaConfig;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public PagoLiniaController(
            NiubizService niubizService,
            ObjectMapper objectMapper,
            VisaConfig visaConfig
    ) {
        this.niubizService = niubizService;
        this.objectMapper = objectMapper;
        this.visaConfig = visaConfig;
    }

    @PostMapping("/validar-recibo")
    public ResponseEntity<ApiResponse<List<ReciboResponseDTO>>> validarRecibo(@RequestHeader("Authorization") String authHeader,@RequestBody ReciboRequestDTO requestDTO){

        String token = authHeader.replace("Bearer ", "");
        String codigoContribuyente = jwtUtil.extractUsername(token);
        requestDTO.setCodigo(codigoContribuyente);

        List<ReciboResponseDTO> resultado = pagoLiniaService.validarRecibo(requestDTO);
        if(resultado == null || resultado.isEmpty()){
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "No se encontraron registros para la consulta.",
                            null
                    )
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Consulta de estado de cuenta exitosa",
                        resultado
                )
        );
    }

    @PostMapping("/token")
    public ResponseEntity<String> obtenerToken() {
        String token = niubizService.getAccessToken();
        return ResponseEntity.ok(token);
    }

    @GetMapping("/ip-maquina")
    public Map<String, String> getIp(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isBlank()) {
            ip = ip.split(",")[0].trim();
        } else {
            ip = req.getRemoteAddr();
        }
        return Map.of("ip", ip);
    }

    @PostMapping("/niubiz/session")
    public ResponseEntity<?> generarTokenSesion(@RequestBody SessionRequestDTO dto) {
        try {
            SessionResponseDTO sessionToken  = niubizService.getSessionToken(dto);
            return ResponseEntity.ok(sessionToken);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(e.getResponseBodyAsString());
        }
    }

    @PostMapping("/obtener-datos-contribuyente")
    public ResponseEntity<ApiResponse<DatosContiResponseDTO>> obtenerDatosContribuyete(@RequestBody DatosContriRequestDTO requestDTO){
        DatosContiResponseDTO resultado = pagoLiniaService.obtenerDatosContribuyente(requestDTO);
        if(resultado==null){
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "No se encontraron registros para la consulta.",
                            null
                    )
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Consulta de estado de cuenta exitosa",
                        resultado
                )
        );
    }

    @PostMapping("/obtener-numero-compra")
    public ResponseEntity<ApiResponse<NumCompraResponseDTO>> obtenerNumeroCompra(@RequestBody NumCompraRequestDTO requestDTO){
        NumCompraResponseDTO resultado = pagoLiniaService.obtenerNumeroCompra(requestDTO);
        if(resultado==null){
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "No se encontraron registros para la consulta.",
                            null
                    )
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Consulta de estado de cuenta exitosa",
                        resultado
                )
        );
    }

    private String extractClientIp(HttpServletRequest request) {
        String[] headers = {"X-Forwarded-For","X-Real-IP","CF-Connecting-IP","True-Client-IP","Forwarded"};
        for (String h : headers) {
            String v = request.getHeader(h);
            if (v != null && !v.isBlank()) return v.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }


    @PostMapping("/logs-pago")
    public ResponseEntity<Map<String,Object>> guardarLogVisa(@RequestBody LogVisaDTO log, HttpServletRequest req) {
        try {
            Path logDir = Paths.get(logDirPrp).toAbsolutePath().normalize();

            Files.createDirectories(logDir);
            String date = LocalDate.now().toString();
            Path file = logDir.resolve("detalle_pago_req_log_" + date + ".txt");
            String ip = extractClientIp(req);
            Map<String, Object> entry = new LinkedHashMap<>();
            entry.put("ts", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            entry.put("ip_servidor", ip);
            entry.put("log", log);
            String json = mapper.writeValueAsString(entry);
            String line = json + System.lineSeparator() + System.lineSeparator();
            Files.writeString(file, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND, StandardOpenOption.WRITE);

            CreateIntentResponse resp = null;
            if(log.tramo().equals("0001")) {
                CreateIntentReq reqInt = new CreateIntentReq();
                reqInt.setPurchaseNumber(log.purchase_number());
                reqInt.setAmount(new BigDecimal(log.resumen().get("amount").toString()));
                reqInt.setCurrency("PEN");
                reqInt.setCodigo(log.resumen().get("codigo").toString());
                reqInt.setOrdenanza("");
                reqInt.setUsuario(log.resumen().get("codigo").toString());
                reqInt.setSessionKey(log.sesion_visa());
                reqInt.setIp(ip);
                reqInt.setUserAgent(log.resumen().get("codigo").toString());
                reqInt.setDetaDataJson(objectMapper.writeValueAsString(log.detalle()));
                reqInt.setModuloAgente("PAGOLINIA");
                resp = pagoLiniaService.crearIntento(reqInt, req);
            }
            //return ResponseEntity.ok(Map.of("ok", true, "file", file.toString(), "resp",resp));
            Map<String, Object> response = new HashMap<>();
            response.put("ok", true);
            response.put("file", file.toString());
            response.put("resp", resp); // null permitido
            return ResponseEntity.ok(response);
        }catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "ok", false,
                    "error", "No se pudo escribir el log: " + e.getMessage()
            ));
        }
    }

    @PostMapping(value = "/autorizacion-transaccion", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Void> autorizacionTransaccion(
            @RequestParam("rid") Long rid,
            @RequestParam("pn") String purchaseNumber,
            @RequestParam("amt") BigDecimal amount,
            @RequestParam("cur") String currency,
            @RequestParam("ord") String ordenanza,
            @RequestParam Map<String, String> form,
            HttpServletRequest request
    ) {
        final String transactionToken = form.get("transactionToken");
        final String ordenaza = ordenanza.isBlank() ? "0" :ordenanza;
        final  String ridUrl = form.get("rid");
        if (transactionToken == null || transactionToken.isBlank()) {
            return redirect303(visaConfig.getUrl().getUrlFrontend()+"/dashboard/estado-cuenta");
        }

        Map<String, Object> auth = niubizService.authorizeTransaction(
                request, transactionToken, purchaseNumber, amount, currency
        );

        String frontStatus = auth.containsKey("dataMap") ? "APPROVED" : auth.containsKey("data") ? "REJECTED" :  "UNKNOWN";
        String authJson = objectMapper.valueToTree(auth).toString();

        UpdateAfterAuthRe dto = new UpdateAfterAuthRe();
        dto.setReciboId(rid);
        dto.setIntento(1);
        dto.setPurchase_number(purchaseNumber);
        dto.setTokenId(transactionToken);
        dto.setStatus(frontStatus);
        dto.setOrdenanza(ordenanza);
        dto.setAuthRawJson(authJson);
        int result = pagoLiniaService.actualizarDespuesDeAuth(dto);

        String payload = ridUrl + "|" + frontStatus +"|"+purchaseNumber;
        String encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes(StandardCharsets.UTF_8));
        return redirect303(visaConfig.getUrl().getUrlFrontend()+"/dashboard/finalizar-pago/"+encoded);
    }

    private ResponseEntity<Void> redirect303(String url) {
        return ResponseEntity.status(303).location(URI.create(url)).build();
    }

    record VisaQuery(Long reciboId, String purchaseNumber) {}

    @PostMapping(
            value = "/listar-registro-visa",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<ReciboResultDTO>> obtenerResultadoVisa( @RequestBody VisaQuery req){
        if (req == null || req.reciboId() == null || req.purchaseNumber() == null || req.purchaseNumber().isBlank()) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(LocalDateTime.now(), "fail", "reciboId y purchaseNumber son obligatorios", null)
            );
        }

        ReciboResultDTO resultado = pagoLiniaService.obtenerResultadoVisa(req.reciboId(), req.purchaseNumber());
        if(resultado==null){
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "No se encontraron registros para la consulta.",
                            null
                    )
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Consulta de estado de cuenta exitosa",
                        resultado
                )
        );
    }

    @PostMapping("/generar-recibo")
    public ResponseEntity<ApiResponse<List<GenerarReciboResponseDTO>>> generarRecibo(@RequestHeader("Authorization") String authHeader,@RequestBody GenerarReciboRequestDTO requestDto) {

        String token = authHeader.replace("Bearer ", "");
        String codigoContribuyente = jwtUtil.extractUsername(token);
        requestDto.setCodigo(codigoContribuyente);

        List<GenerarReciboResponseDTO> response = pagoLiniaService.generarRecibo(requestDto);

        if(response==null){
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "No se encontraron registros para la consulta.",
                            null
                    )
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Consulta de estado de cuenta exitosa",
                        response
                )
        );
    }

    @PostMapping("/guardar-pasarela")
    public ResponseEntity<ApiResponse<List<GenerarReciboResponseDTO>>> guardarPasarela(@RequestBody GuardarPasarelaRequestDTO dto){
        try {
            if(dto.getCodigo().isEmpty() || dto.getNumIngr().isEmpty()){
                throw new RuntimeException("Código o Numero de ingreso es requerido");
            }
            TransaccionRequestDTO transc = new TransaccionRequestDTO();
            transc.setBuscar(1);
            transc.setNumIngr(dto.getNumIngr());
            transc.setCodigo(dto.getCodigo());
            transc.setPurchaseNumber(dto.getPurchaseNumber());
            transc.setCurrency((String) dto.getDataMap().get("CURRENCY"));
            transc.setTransactionDate((String) dto.getDataMap().get("TRANSACTION_DATE"));
            transc.setTerminal((String) dto.getDataMap().get("TERMINAL"));
            transc.setActionCode((String) dto.getDataMap().get("ACTION_CODE"));
            transc.setTraceNumber((String) dto.getDataMap().get("TRACE_NUMBER"));
            transc.setEciDescription((String) dto.getDataMap().get("ECI_DESCRIPTION"));
            transc.setEci((String) dto.getDataMap().get("ECI"));
            transc.setSignature((String) dto.getDataMap().get("SIGNATURE"));
            transc.setBrand((String) dto.getDataMap().get("BRAND"));
            transc.setCard((String) dto.getDataMap().get("CARD"));
            transc.setMerchant((String) dto.getDataMap().get("MERCHANT"));
            transc.setStatus((String) dto.getDataMap().get("STATUS"));
            transc.setAdquirente((String) dto.getDataMap().get("ADQUIRENTE"));
            transc.setActionDescription((String) dto.getDataMap().get("ACTION_DESCRIPTION"));
            transc.setIdUnico((String) dto.getDataMap().get("ID_UNICO"));
            transc.setAmount(new BigDecimal(dto.getDataMap().get("AMOUNT").toString()));
            transc.setProcessCode((String) dto.getDataMap().get("PROCESS_CODE"));
            transc.setTransactionId((String) dto.getDataMap().get("TRANSACTION_ID"));
            transc.setAuthorizationCode((String) dto.getDataMap().get("AUTHORIZATION_CODE"));
            List<GenerarReciboResponseDTO> response = pagoLiniaService.generarPasarela(transc);

            if(response==null){
                return ResponseEntity.status(404).body(
                        new ApiResponse<>(
                                LocalDateTime.now(),
                                "fail",
                                "erro al registrar pasarela.",
                                null
                        )
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "success",
                            "se registro correctamente",
                            response
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "error en el proceso de registrar pasarela.",
                            null
                    )
            );
        }
    }

    @PostMapping("/buscar-numero-compra")
    public ResponseEntity<ApiResponse<NumeroCompraResponseDTO>> buscarNumeroCompra(@RequestBody NumeroCompraRequestDTO dto){
        try {
            if (dto.getPurchaseNumber() == null || dto.getPurchaseNumber().isBlank()) {
                throw new RuntimeException("El purchaseNumber es obligatorio");
            }

            NumeroCompraResponseDTO response = pagoLiniaService.consultarNumeroCompra(dto);

            if(response==null){
                return ResponseEntity.status(404).body(
                        new ApiResponse<>(
                                LocalDateTime.now(),
                                "fail",
                                "No se encontraron registros para la consulta.",
                                null
                        )
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "success",
                            "Consulta de estado de cuenta exitosa",
                            response
                    )
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/actualizar-intento")
    public ResponseEntity<ApiResponse<Integer>> actualizarIntento(
            @RequestBody NumeroCompraRequestDTO requestDTO) {
        try {
            Integer rows = pagoLiniaService.actualizarIntento(requestDTO);

            if (rows > 0) {
                return ResponseEntity.ok(
                        new ApiResponse<>(LocalDateTime.now(),"success", "Intento actualizado correctamente",rows)
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse<>(
                                LocalDateTime.now(),
                                "fail",
                                "No se encontraron registros para la consulta.",
                                null
                        )
                );
            }
        } catch (Exception e) {
            log.error("Error en controlador actualizarIntento", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                        new ApiResponse<>(
                                LocalDateTime.now(),
                                "fail",
                                "Error interno al actualizar.",
                                null
                        )
                    );
        }
    }

}
