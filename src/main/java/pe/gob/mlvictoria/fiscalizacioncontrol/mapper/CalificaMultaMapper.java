package pe.gob.mlvictoria.fiscalizacioncontrol.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.multas.CalificaMultaResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.multas.CalificaMultaRequestDTO;

import java.util.List;

@Mapper
public interface CalificaMultaMapper {
    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC califica.sp_califica_multas
        @vfacodcontr = #{dto.codigo},
        @vfcomen = #{dto.comen};
    """)
    List<CalificaMultaResponseDTO> listaCalificaMulta(@Param("dto") CalificaMultaRequestDTO dto);
}
