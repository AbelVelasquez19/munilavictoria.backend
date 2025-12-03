package pe.gob.mlvictoria.niubiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.mlvictoria.complejo.dto.niubiz.*;
import pe.gob.mlvictoria.complejo.dto.reserva.ActuReciNiubizRequest;
import pe.gob.mlvictoria.complejo.service.NiubizStotageService;
import pe.gob.mlvictoria.complejo.service.ReservaService;
import pe.gob.mlvictoria.niubiz.service.NiubizWService;
import pe.gob.mlvictoria.niubiz.service.TokenStorageService;
import pe.gob.mlvictoria.pagolinea.dto.ApiResponse;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.CreateIntentReq;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.CreateIntentResponse;
import pe.gob.mlvictoria.pagolinea.dto.pago.LogVisaDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.NumCompraRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.NumCompraResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.recibo.GenerarReciboRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.recibo.GenerarReciboResponseDTO;
import pe.gob.mlvictoria.pagolinea.service.PagoLiniaService;
import pe.gob.mlvictoria.talleres.exepcion.BusinessException;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/niubiz")
public class NiubizController {
    private final NiubizWService niubizService;
    private final TokenStorageService tokenStorageService;
    private final PagoLiniaService pagoLiniaService;
    private final ObjectMapper objectMapper;
    private final NiubizStotageService niubizStotageService;
    private final ReservaService reservaService;

    @Value("${niuviz.upload-dir}")
    private String logDirPrp;

    public NiubizController(NiubizWService niubizService, TokenStorageService tokenStorageService,
                            PagoLiniaService pagoLiniaService, ObjectMapper objectMapper,
                            NiubizStotageService niubizStotageService, ReservaService reservaService) {
        this.reservaService = reservaService;
        this.pagoLiniaService = pagoLiniaService;
        this.niubizService = niubizService;
        this.tokenStorageService = tokenStorageService;
        this.objectMapper = objectMapper;
        this.niubizStotageService = niubizStotageService;
    }

    @PostMapping("/visa-numero-compra")
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

    // Endpoint para generar token de sesion
    @PostMapping("/generate-session-token")
    public ResponseEntity<Map<String, Object>> generateSessionToken(@RequestBody Map<String, Object> requestBody,HttpServletRequest req) {
        Map<String, Object> response = new HashMap<>();
        try {
            double amount = ((Number) requestBody.get("amount")).doubleValue();
            String purchaseNumber = (String) requestBody.get("purchaseNumber");
            String clientIp = extractClientIp(req);
            String email = (String) requestBody.get("email");
            String dni = (String) requestBody.get("numeroDocumento");

            String accessToken = niubizService.generateAccessToken();

            //  Guardamos el monto total(amount) en el TSS(no ideal)
            NiubizRequest reque1 = new NiubizRequest();
            reque1.setOpcion(1);
            reque1.setPurchasNumber(purchaseNumber);
            reque1.setAmount(amount);
            NiubizResponse res1 = niubizStotageService.tokenStorage(reque1);

            NiubizRequest reque2 = new NiubizRequest();
            reque2.setOpcion(2);
            reque2.setAccessToken(accessToken);
            reque2.setIdToken(res1.getIdToken());
            NiubizResponse res2 = niubizStotageService.tokenStorage(reque2);
            response.put("accessToken", accessToken);
            // Generar token de sesión
            String sessionToken = niubizService.generateSessionToken(accessToken, amount, purchaseNumber, clientIp, email, dni);
            response.put("sessionToken", sessionToken);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // En caso de error
            response.put("error", "Error al generar los tokens de acceso y sesion");
            return ResponseEntity.status(500).body(response);
        }
    }

    // Endpoint para recibir repuesta del fomulario de pago de niubiz
    @PostMapping("/response-form")
    public void handleFormUrlEncodedResponse(@RequestParam Map<String, String> formData, HttpServletResponse response,HttpServletRequest req)throws IOException {
        try {
            // 1. Leer datos enviados por Niubiz
            String channel = formData.get("channel");
            String transactionToken = formData.get("transactionToken");
            String customerEmail = formData.get("customerEmail");
            String purchasenumber = formData.get("purchasenumber");
            String codigo = formData.get("codigo");
            String idReserva = formData.get("idReserva");
            String descripcion = formData.get("descripcion");
            String tasa = (String) formData.get("tasa");

            NiubizRequest reque = new NiubizRequest();
            reque.setOpcion(5);
            reque.setPurchasNumber(purchasenumber);
            NiubizResponse res = niubizStotageService.tokenStorage(reque);

            String purchaseNumber = res.getPurchasNumber();

            System.out.println("Channel: " + channel);
            System.out.println("Transaction Token: " + transactionToken);
            System.out.println("PurchaseNumber: " + purchaseNumber);

            // Validar
            if (transactionToken == null || purchaseNumber == null) {
                System.out.println("Error: Datos incompletos en callback");
                response.sendRedirect("http://localhost:4500/error");
                return;
            }

            // 2. Guardar el token de transacción (temporal o BD)
            NiubizRequest reque1 = new NiubizRequest();
            reque1.setOpcion(3);
            reque1.setTransactionToken(transactionToken);
            reque1.setIdToken(res.getIdToken());
            NiubizResponse res1 = niubizStotageService.tokenStorage(reque1);

            // 3. Procesar autorización Niubiz (PASO 3)
            Map<String, Object> authorizationResult = processAuthorization(purchaseNumber);
            String estado = validarTrama(authorizationResult);

            System.out.println("Authorization Result: " + authorizationResult);

            if(estado == null){
                System.out.println("Error: Estado de autorización nulo");
                response.sendRedirect("http://localhost:4500/error");
                return;
            }
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

            if(estado.equals("APROBADO")){
                //  Guardamos el monto total(amount) en el TSS(no ideal)
                NiubizRequest reqFinal = new NiubizRequest();
                reqFinal.setOpcion(4);
                reqFinal.setEstado("APROBADO");
                reqFinal.setIdToken(res.getIdToken());
                NiubizResponse resFinal = niubizStotageService.tokenStorage(reqFinal);

                //generar recibo
                ReciboGenerarRequest rgt = new ReciboGenerarRequest();
                rgt.setMsquery(1);
                rgt.setCodigo(codigo);
                rgt.setAnno(Year.now().getValue()+"");
                rgt.setNumDocu(idReserva);
                rgt.setTipo(tasa);
                rgt.setTipoRec(tasa);
                rgt.setPeriodo(LocalDate.now().getMonthValue()+"");
                rgt.setImpInsol(res.getAmount());
                rgt.setImpReaj(res.getAmount());
                rgt.setObservacion(descripcion);
                rgt.setFecVenc(LocalDateTime.now().format(formato));
                rgt.setOperador(codigo);
                rgt.setEstacion(extractClientIp(req));
                int rid = niubizStotageService.cajaRecibosWeb(rgt);

                int iidReserva = reservaService.pagarReserva(Integer.parseInt(idReserva),rid);

                ActuReciNiubizRequest resRecNiubiz = new ActuReciNiubizRequest();
                resRecNiubiz.setCodigo(codigo);
                resRecNiubiz.setIntento(1);
                resRecNiubiz.setPurchaseNumber(purchaseNumber);
                resRecNiubiz.setTokenId(transactionToken);
                resRecNiubiz.setStatus("APPROVED");
                resRecNiubiz.setOrdenanza("0");
                resRecNiubiz.setIdRecibo(rid);
                resRecNiubiz.setAuthRawJson(objectMapper.writeValueAsString(authorizationResult));
                int iidReciboNiubiz = reservaService.actualizarReciboNiubiz(resRecNiubiz);

                EstadoCuentaRequest estadoCuenta = new EstadoCuentaRequest();
                estadoCuenta.setCodigo(codigo);
                estadoCuenta.setIdreciboGroup( String.valueOf(rid));
                List<EstadoCuentaResponse> listEstadoCuenta = niubizStotageService.EstadoCuenta(estadoCuenta);

                //detalle caja
                List<DetalleCajaResponse> detalle = new ArrayList<>();
                for(EstadoCuentaResponse rw : listEstadoCuenta){
                    if (rw.getEstado() == 1) {
                        throw new BusinessException("El recibo ya se encuentra pagado.");
                    }

                    DetalleCajaResponse dtoRes = DetalleCajaResponse.builder()
                            .numIngr("0")
                            .idrecibo(Integer.parseInt(rw.getIdrecibo()))
                            .montoTotal(BigDecimal.valueOf(res.getAmount()))
                            .codigo(rw.getCodigo())
                            .anno(rw.getAnno())
                            .codPred(rw.getCodPred())
                            .anexo(rw.getAnexo())
                            .subAnexo(rw.getSubAnexo())
                            .tipo(rw.getTipo())
                            .tipoRec(rw.getTipoRec())
                            .periodo(rw.getPeriodo())

                            .impInsol(rw.getImpInsol())
                            .factReaj(rw.getFactReaj())
                            .impReaj(rw.getImpReaj())
                            .factMora(rw.getFactMora())
                            .impMora(rw.getMora())
                            .costoEmis(rw.getCostoEmis())

                            .observacion(limpiarObservacion(rw.getObservacion()))
                            .operador(rw.getCodigo())
                            .estacion(extractClientIp(req))
                            .fechaIngreso("01/01/2010")

                            .descuento(nvl(rw.getDescuento()))
                            .ubica(rw.getUbica())
                            .descInsol(nvl(rw.getDescInsol()))
                            .descReaj(nvl(rw.getDescReaj()))
                            .descMora(nvl(rw.getDescMora()))
                            .descEmis(nvl(rw.getDescEmis()))
                            .build();

                    detalle.add(dtoRes);
                }

                String dxml = generarDxml(detalle);
                GenerarReciboRequestDTO requestDto = new GenerarReciboRequestDTO();
                requestDto.setCodigo(codigo);
                requestDto.setCajero("008");
                requestDto.setDxml(dxml);
                requestDto.setFormaPago("T");
                requestDto.setCriterio("0");
                requestDto.setCmbBanco("");
                requestDto.setCmbTarjeta("");
                requestDto.setTxtObservacion(descripcion);
                requestDto.setNomCaja("PAGOVIAWEB");
                requestDto.setEstacion(extractClientIp(req));
                requestDto.setTxtCobrar(BigDecimal.valueOf(res.getAmount()));
                requestDto.setTxtEfectivo(BigDecimal.valueOf(res.getAmount()));
                List<GenerarReciboResponseDTO> responseDto = pagoLiniaService.generarRecibo(requestDto);

                LogVisaDTO logVisa = new LogVisaDTO(
                        "0002",
                        purchaseNumber,
                        "web",
                        "",
                        "",
                        "",
                        "",
                        res.getAccessToken(),
                        authorizationResult,
                        Map.of(
                                "amount", res.getAmount(),
                                "codigo", codigo
                        ),
                        detalle.toArray()
                );
                guardarComplejoLogVisa(logVisa, req);
                response.sendRedirect("http://localhost:4500/inicio?tid="+transactionToken);
            } else {
                System.out.println("Pago RECHAZADO para la compra: " + purchaseNumber + " - Estado: " + estado);
                LogVisaDTO logVisa = new LogVisaDTO(
                        "0004",
                        purchaseNumber,
                        "web",
                        "",
                        "",
                        "",
                        "",
                        res.getAccessToken(),
                        authorizationResult,
                        Map.of(
                                "amount", res.getAmount(),
                                "codigo", codigo
                        ),
                        new Object[]{}
                );
                guardarComplejoLogVisa(logVisa, req);

                ActuReciNiubizRequest resRecNiubiz = new ActuReciNiubizRequest();
                resRecNiubiz.setCodigo(codigo);
                resRecNiubiz.setIntento(2);
                resRecNiubiz.setPurchaseNumber(purchaseNumber);
                resRecNiubiz.setTokenId(transactionToken);
                resRecNiubiz.setStatus("REJECTED");
                resRecNiubiz.setOrdenanza("0");
                resRecNiubiz.setIdRecibo(0);
                resRecNiubiz.setAuthRawJson(objectMapper.writeValueAsString(authorizationResult));
                int iidReciboNiubiz = reservaService.actualizarReciboNiubiz(resRecNiubiz);

                //  Guardamos el monto total(amount) en el TSS(no ideal)
                NiubizRequest reqFinal = new NiubizRequest();
                reqFinal.setOpcion(4);
                reqFinal.setEstado("FINALIZADO");
                reqFinal.setIdToken(res.getIdToken());
                NiubizResponse resFinal = niubizStotageService.tokenStorage(reqFinal);

                response.sendRedirect("http://localhost:4500/inicio?tid="+transactionToken);
            }

        } catch (Exception e) {
            e.printStackTrace();
            //response.sendRedirect("http://localhost:4500/inicio?error=true");
        }
    }

    public String generarDxml(List<DetalleCajaResponse> detalle) {

        StringBuilder dxml = new StringBuilder();

        for (DetalleCajaResponse fila : detalle) {

            List<String> valores = List.of(
                    String.valueOf(fila.getNumIngr()),
                    String.valueOf(fila.getIdrecibo()),
                    fila.getMontoTotal().toPlainString(),
                    fila.getCodigo(),
                    String.valueOf(fila.getAnno()),
                    fila.getCodPred(),
                    fila.getAnexo(),
                    fila.getSubAnexo(),
                    fila.getTipo(),
                    fila.getTipoRec(),
                    fila.getPeriodo(),
                    fila.getImpInsol().toPlainString(),
                    fila.getFactReaj().toPlainString(),
                    fila.getImpReaj().toPlainString(),
                    fila.getFactMora().toPlainString(),
                    fila.getImpMora().toPlainString(),
                    fila.getCostoEmis().toPlainString(),
                    fila.getObservacion(),
                    fila.getOperador(),
                    fila.getEstacion(),
                    fila.getFechaIngreso(),
                    fila.getDescuento().toPlainString(),
                    fila.getUbica(),
                    fila.getDescInsol().toPlainString(),
                    fila.getDescReaj().toPlainString(),
                    fila.getDescMora().toPlainString(),
                    fila.getDescEmis().toPlainString()
            );

            for (String v : valores) {
                dxml.append("\"").append(v).append("\",");
            }

            dxml.append(";");
        }

        return dxml.toString();
    }

    private String limpiarObservacion(String obs) {
        if (obs == null) return "";
        return obs.replace(";", "")
                .replace(",", "")
                .replace("'", "");
    }

    private BigDecimal nvl(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }

    public Map<String, Object> processAuthorization(String purchaseNumber) {

        NiubizRequest reque = new NiubizRequest();
        reque.setOpcion(5);
        reque.setPurchasNumber(purchaseNumber);
        NiubizResponse res = niubizStotageService.tokenStorage(reque);

        String accessToken = res.getAccessToken();
        String transactionId = res.getTransactionToken();

        System.out.println("Token de acceso auth: " + accessToken);
        System.out.println("Transaction Id auth: " + transactionId);

        Map<String, Object> order = Map.of(
                "tokenId", transactionId,
                "purchaseNumber", purchaseNumber,
                "amount", res.getAmount(),
                "currency", "PEN"
        );

        return niubizService.generateAuthorizationToken(accessToken, order);
    }

    public String validarTrama(Map<String, Object> trama) {

        // ======== CASO 200 (tiene "order") ========
        if (trama.containsKey("order")) {
            Map<String, Object> order = (Map<String, Object>) trama.get("order");
            String actionCode = String.valueOf(order.get("actionCode"));

            if ("000".equals(actionCode)) return "APROBADO";
            else return "RECHAZADO";
        }

        // ======== CASO 400 / 401 / 406 / 500 (NO tiene "order") ========
        if (trama.containsKey("errorCode")) {
            Integer errorCode = (Integer) trama.get("errorCode");

            switch (errorCode) {
                case 400: return "ERROR_400";
                case 401: return "ERROR_401";
                case 406: return "SESION_EXPIRADA";
                case 500: return "ERROR_SERVIDOR";
                default:   return "ERROR_DESCONOCIDO";
            }
        }

        // ======== CASO ESPECIAL (si llega ACTION_CODE en data) =========
        if (trama.containsKey("data")) {
            Map<String, Object> data = (Map<String, Object>) trama.get("data");

            if (data.containsKey("ACTION_CODE")) {
                return "RECHAZADO";
            }
        }

        return "ERROR_DESCONOCIDO";
    }



    private String extractClientIp(HttpServletRequest request) {
        String[] headers = {
                "X-Forwarded-For",
                "X-Real-IP",
                "CF-Connecting-IP",
                "True-Client-IP",
                "Forwarded"
        };

        for (String h : headers) {
            String v = request.getHeader(h);
            if (v != null && !v.isBlank()) return v.split(",")[0].trim();
        }

        return request.getRemoteAddr();
    }

    @PostMapping("/logs-complejos")
    public  String prueba (){
        return "hola mundo";
    }

    @PostMapping("/logs-complejo")
    public ResponseEntity<Map<String,Object>> guardarComplejoLogVisa(@RequestBody LogVisaDTO log, HttpServletRequest req) {
        try {
            Path logDir = Paths.get(logDirPrp).toAbsolutePath().normalize();

            Files.createDirectories(logDir);
            String date = LocalDate.now().toString();
            Path file = logDir.resolve("complejo_detalle_pago_req_log_" + date + ".txt");
            String ip = extractClientIp(req);
            Map<String, Object> entry = new LinkedHashMap<>();
            entry.put("ts", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            entry.put("ip_servidor", ip);
            entry.put("log", log);
            String json = objectMapper.writeValueAsString(entry);
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
                reqInt.setModuloAgente("COMPLEJO_DEPORTIVO");
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
}
