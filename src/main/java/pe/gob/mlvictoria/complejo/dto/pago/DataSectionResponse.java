package pe.gob.mlvictoria.complejo.dto.pago;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataSectionResponse {
    @JsonProperty("YAPE_ID")
    private String yapeId;

    @JsonProperty("CURRENCY")
    private String currency;

    @JsonProperty("TERMINAL")
    private String terminal;

    @JsonProperty("TRANSACTION_DATE")
    private String transactionDate;

    @JsonProperty("ACTION_CODE")
    private String actionCode;

    @JsonProperty("TRACE_NUMBER")
    private String traceNumber;

    @JsonProperty("CARD_TYPE")
    private String cardType;

    @JsonProperty("ECI_DESCRIPTION")
    private String eciDescription;

    @JsonProperty("ECI")
    private String eci;

    @JsonProperty("SIGNATURE")
    private String signature;

    @JsonProperty("CARD")
    private String card;

    @JsonProperty("MERCHANT")
    private String merchant;

    @JsonProperty("BRAND")
    private String brand;

    @JsonProperty("STATUS")
    private String status;

    @JsonProperty("ACTION_DESCRIPTION")
    private String actionDescription;

    @JsonProperty("ADQUIRENTE")
    private String adquicente;

    @JsonProperty("ID_UNICO")
    private String idUnico;

    @JsonProperty("AMOUNT")
    private String amount;

    @JsonProperty("PROCESS_CODE")
    private String processCode;

    @JsonProperty("TRANSACTION_ID")
    private String transactionId;
}
