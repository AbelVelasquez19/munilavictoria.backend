package pe.gob.mlvictoria.complejo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.complejo.dto.administrado.ComAdminRequest;
import pe.gob.mlvictoria.complejo.dto.administrado.ComAdminResponse;

@Mapper
public interface AdministradoMapper {
    @Select("""
        EXEC complejoDeportivo.sp_administrado
            @opcion = #{dto.opcion, mode=IN, jdbcType=INTEGER},
            @tipoPersona = #{dto.tipoPersona, mode=IN, jdbcType=INTEGER},
            @tipoDocumento = #{dto.tipoDocumento, mode=IN, jdbcType=INTEGER},
            @codigo = #{dto.codigo, mode=IN, jdbcType=VARCHAR},
            @numDocumento = #{dto.numDocumento, mode=IN, jdbcType=VARCHAR},
            @conAcepto = #{dto.conAcepto, mode=IN, jdbcType=INTEGER},
            @conNoResonsabilizar = #{dto.conNoResonsabilizar, mode=IN, jdbcType=INTEGER},
            @operador = #{dto.operador, mode=IN, jdbcType=VARCHAR},
            @estacion = #{dto.estacion, mode=IN, jdbcType=VARCHAR};
    """)
    @Options(statementType = StatementType.CALLABLE)
    ComAdminResponse registrarComAdministrado(@Param("dto") ComAdminRequest dto);
}
