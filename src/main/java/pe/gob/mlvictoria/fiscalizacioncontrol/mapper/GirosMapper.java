package pe.gob.mlvictoria.fiscalizacioncontrol.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.giros.GirosRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.giros.GirosResponseDTO;

import java.util.List;

@Mapper
public interface GirosMapper {
    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC califica.sp_giros
        @busc = #{dto.busc},
        @inicio = #{dto.inicio},
        @final = #{dto.fin},
        @desc = #{dto.desc};
    """)
    List<GirosResponseDTO> listarGiros(@Param("dto") GirosRequestDTO dto);
}
