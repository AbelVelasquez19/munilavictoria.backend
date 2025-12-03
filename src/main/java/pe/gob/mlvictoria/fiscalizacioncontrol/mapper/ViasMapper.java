package pe.gob.mlvictoria.fiscalizacioncontrol.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.tipovia.TipoViaResponse;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias.*;

import java.util.List;

@Mapper
public interface ViasMapper {
    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC califica.sp_vias
        @busc = #{dto.busc},
        @inicio = #{dto.inicio},
        @final = #{dto.fin},
        @desc = #{dto.desc};
    """)
    List<ViasResponseDTO> listarVias(@Param("dto") ViasRequestDTO dto);

    @Select("""
            EXEC Rentas.SP_vw_Mvias
            @msquery = #{dto.msquery, mode=IN, jdbcType=INTEGER},
            @nombre_via = #{dto.nombreVia, mode=IN, jdbcType=VARCHAR},
            @inicio = #{dto.inicio, mode=IN, jdbcType=INTEGER},
            @final = #{dto.finall, mode=IN, jdbcType=INTEGER};
        """)
    @Options(statementType = StatementType.CALLABLE)
    List<ViasContriResponse> listarViasContri(@Param("dto") ViasContriRequest request);

    @Select("""
            EXEC Rentas.SP_vw_Mvias
            @msquery = #{dto.msquery, mode=IN, jdbcType=INTEGER},
            @nombre_via = #{dto.nombreVia, mode=IN, jdbcType=VARCHAR},
            @inicio = #{dto.inicio, mode=IN, jdbcType=INTEGER},
            @final = #{dto.finall, mode=IN, jdbcType=INTEGER};
        """)
    @Options(statementType = StatementType.CALLABLE)
    List<ViasContriTotalResponse> listarViasContriTotal(@Param("dto") ViasContriRequest request);
}
