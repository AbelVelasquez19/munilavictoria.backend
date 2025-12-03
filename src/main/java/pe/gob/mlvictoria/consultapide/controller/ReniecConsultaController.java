package pe.gob.mlvictoria.consultapide.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mlvictoria.consultapide.dto.ReniecRequestDTO;
import pe.gob.mlvictoria.consultapide.dto.ReniecResponseDTO;
import pe.gob.mlvictoria.consultapide.service.ReniecConsultaService;

@RestController
@RequestMapping("/api/reniec")
@AllArgsConstructor
public class ReniecConsultaController {

    @Autowired
    private final ReniecConsultaService consultaService;

    @PostMapping("/consultar")
    public ResponseEntity<ReniecResponseDTO> consultar(@RequestBody ReniecRequestDTO request, HttpServletRequest req) {
        ReniecResponseDTO response = consultaService.consultarReniec(request,req);
        return ResponseEntity.ok(response);
    }
}
