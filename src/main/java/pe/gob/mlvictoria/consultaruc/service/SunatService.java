package pe.gob.mlvictoria.consultaruc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import pe.gob.mlvictoria.consultaruc.dto.SunatResponseDTO;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Slf4j
@Service
@AllArgsConstructor
public class SunatService {
    public SunatResponseDTO consultarRuc(String numRuc) {
        SunatResponseDTO dto = new SunatResponseDTO();
        try {
            String urlStr = "https://ws3.pide.gob.pe/Rest/Sunat/DatosPrincipales?numruc=" + numRuc;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/xml");

            // Leer respuesta XML
            Scanner sc = new Scanner(conn.getInputStream());
            StringBuilder xmlResponse = new StringBuilder();
            while (sc.hasNext()) xmlResponse.append(sc.nextLine());
            sc.close();
            conn.disconnect();

            if (xmlResponse.length() == 0) {
                log.warn("Respuesta vacía del servicio SUNAT para RUC {}", numRuc);
                return null;
            }

            // Parsear XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlResponse.toString())));

            if (doc.getElementsByTagName("multiRef").getLength() == 0) {
                log.warn("No se encontraron datos válidos para el RUC {}", numRuc);
                return null;
            }

            String nombre = getTagValue(doc, "ddp_nombre");
            if (nombre == null || nombre.isBlank()) {
                log.warn("RUC {} no existe o no tiene datos en SUNAT", numRuc);
                return null;
            }

            dto.setNumRuc(getTagValue(doc, "ddp_numruc"));
            dto.setNombre(getTagValue(doc, "ddp_nombre").trim());
            dto.setEstado(getTagValue(doc, "desc_estado").trim());
            dto.setCondicion(getTagValue(doc, "desc_flag22").trim());
            dto.setTipoContribuyente(getTagValue(doc, "desc_identi").trim());
            dto.setDireccion(getTagValue(doc, "ddp_nomvia").trim());
            dto.setUbigeo(getTagValue(doc, "ddp_ubigeo"));
            dto.setDepartamento(getTagValue(doc, "desc_dep").trim());
            dto.setProvincia(getTagValue(doc, "desc_prov").trim());
            dto.setDistrito(getTagValue(doc, "desc_dist").trim());

        } catch (Exception e) {
            log.error("Error consultando SUNAT: {}", e.getMessage());
            return null;
        }
        return dto;
    }

    private String getTagValue(Document doc, String tag) {
        try {
            return doc.getElementsByTagName(tag).item(0).getTextContent();
        } catch (Exception e) {
            return "";
        }
    }
}
