package pe.gob.mlvictoria.fiscalizacioncontrol.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.inspectores.InspectoresRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.inspectores.InspectoresResponseDTO;

import java.util.List;

@Mapper
public interface InspectorMapper {
    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC califica.sp_inspectores
        @busc = #{dto.busc},
        @FAANOTRIBU = #{dto.FAANOTRIBU},
        @FANRODOCUM = #{dto.FANRODOCUM},
        @FANOMINSPE = #{dto.FANOMINSPE},
        @inicio = #{dto.inicio},
        @final = #{dto.fin};
    """)
    List<InspectoresResponseDTO> listarInspectores(@Param("dto") InspectoresRequestDTO dto);
}
