package pe.gob.mlvictoria.talleres.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.talleres.dto.restablecer.RestablecerRequest;
import pe.gob.mlvictoria.talleres.dto.restablecer.RestablecerResponse;

@Mapper
public interface RestablecerMapper {
    @Select("""
        EXEC cursos.sp_vtl_recuperar_password
            @opcion = #{dto.opcion, mode=IN, jdbcType=INTEGER},
            @correo = #{dto.correo, mode=IN, jdbcType=VARCHAR},
            @codigo = #{dto.codigo, mode=IN, jdbcType=VARCHAR},
            @numDoc = #{dto.numDoc, mode=IN, jdbcType=VARCHAR},
            @tokenn = #{dto.tokenn, mode=IN, jdbcType=VARCHAR},
            @password = #{dto.password, mode=IN, jdbcType=VARCHAR};
    """)
    @Options(statementType = StatementType.CALLABLE)
    RestablecerResponse restablecerPassword(@Param("dto") RestablecerRequest dto);
}
