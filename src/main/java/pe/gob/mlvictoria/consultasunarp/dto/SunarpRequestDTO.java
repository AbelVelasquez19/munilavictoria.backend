package pe.gob.mlvictoria.consultasunarp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SunarpRequestDTO {
    private SunarpTsirsarpPayloadDTO PIDE;
}
