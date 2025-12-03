package pe.gob.mlvictoria.fiscalizacioncontrol.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.infraccion.InfraccionRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.infraccion.InfraccionResponseDTO;

import java.util.List;

@Mapper
public interface InfraccionMapper {
    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC califica.sp_infraccion
        @busc = #{dto.busc},
        @inicio = #{dto.inicio},
        @final = #{dto.fin},
        @origen = #{dto.origen},
        @lineaaccion = #{dto.lineaaccion},
        @desc = #{dto.desc},
        @ordenanza = #{dto.ordenanza};
    """)
    List<InfraccionResponseDTO> listarInfraccion(@Param("dto") InfraccionRequestDTO dto);
}
