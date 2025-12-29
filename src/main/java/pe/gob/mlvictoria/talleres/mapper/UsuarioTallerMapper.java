package pe.gob.mlvictoria.talleres.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.talleres.dto.usuario.*;

import java.util.List;

@Mapper
public interface UsuarioTallerMapper {
    @Select("""
        EXEC talleres.sp_web_usuario_listar
            @buscar = #{dto.buscar, mode=IN, jdbcType=VARCHAR},
            @numeroPagina = #{dto.numeroPagina, mode=IN, jdbcType=INTEGER},
            @totalPagina = #{dto.totalPagina, mode=IN, jdbcType=INTEGER};
    """)
    @Options(statementType = StatementType.CALLABLE)
    List<UsuarioTallerResponse> listarUsuarioTaller(@Param("dto") UsuarioTallerRequest dto);

    @Select("""
        EXEC talleres.sp_web_usuario_registrar
            @codigo = #{dto.codigo, mode=IN, jdbcType=VARCHAR},
            @username = #{dto.username, mode=IN, jdbcType=VARCHAR},
            @passwordHash = #{dto.passwordHash, mode=IN, jdbcType=VARCHAR},
            @idRol = #{dto.idRol, mode=IN, jdbcType=INTEGER},
            @operador = #{dto.operador, mode=IN, jdbcType=VARCHAR},
            @estacion = #{dto.estacion, mode=IN, jdbcType=VARCHAR};
    """)
    @Options(statementType = StatementType.CALLABLE)
    RegisUsuarioTallerResponse registrarUsuarioTaller(@Param("dto") RegisUsuarioTallerRequest dto);

    @Select("""
        EXEC talleres.sp_web_usuario_obtener
            @idUsuario = #{dto.idUsuario, mode=IN, jdbcType=VARCHAR};
    """)
    @Options(statementType = StatementType.CALLABLE)
    ObtUsuarioTallerResponse obtenerUsuarioTaller(@Param("dto") ObtUsuarioTallerRequest dto);

    @Select("""
        EXEC talleres.sp_web_usuario_actualizar
            @idUsuario = #{dto.idUsuario, mode=IN, jdbcType=VARCHAR},
            @idRol = #{dto.idRol, mode=IN, jdbcType=INTEGER},
            @passwordHash = #{dto.passwordHash, mode=IN, jdbcType=VARCHAR},
            @estado = #{dto.estado, mode=IN, jdbcType=VARCHAR},
            @operador = #{dto.operador, mode=IN, jdbcType=VARCHAR},
            @estacion = #{dto.estacion, mode=IN, jdbcType=VARCHAR},
            @codigo = #{dto.codigo, mode=IN, jdbcType=VARCHAR};
    """)
    @Options(statementType = StatementType.CALLABLE)
    ActUsuarioTallerResponse actualizarUsuarioTaller(@Param("dto") ActUsuarioTallerRequest dto);
}
