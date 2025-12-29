package pe.gob.mlvictoria.talleres.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.talleres.dto.apoderado.ApoderadoRequest;
import pe.gob.mlvictoria.talleres.dto.apoderado.ApoderadoResponse;
import pe.gob.mlvictoria.talleres.dto.apoderado.ContribuyenteRequest;
import pe.gob.mlvictoria.talleres.dto.apoderado.ContribuyenteResponse;

@Mapper
public interface ApoderadoMapper {
    @Select("""
        EXEC talleres.sp_web_contribuyente
            @id_tipo_contri = #{dto.idTipoContri, mode=IN, jdbcType=VARCHAR},
            @nombres = #{dto.nombres, mode=IN, jdbcType=VARCHAR},
            @paterno = #{dto.paterno, mode=IN, jdbcType=VARCHAR},
            @materno = #{dto.materno, mode=IN, jdbcType=VARCHAR},
            @sexo = #{dto.sexo, mode=IN, jdbcType=VARCHAR},
            @correo_e = #{dto.correoE, mode=IN, jdbcType=VARCHAR},
            @id_doc = #{dto.idDoc, mode=IN, jdbcType=VARCHAR},
            @num_doc = #{dto.numDoc, mode=IN, jdbcType=VARCHAR},
            @direccion = #{dto.direccion, mode=IN, jdbcType=VARCHAR},
            @telefono = #{dto.telefono, mode=IN, jdbcType=VARCHAR},
            @valida = #{dto.valida, mode=IN, jdbcType=INTEGER},
            @operador = #{dto.operador, mode=IN, jdbcType=VARCHAR},
            @estacion = #{dto.estacion, mode=IN, jdbcType=VARCHAR};
    """)
    @Options(statementType = StatementType.CALLABLE)
    ContribuyenteResponse registrarContribuyente(@Param("dto") ContribuyenteRequest dto);

    @Select("""
         EXEC talleres.sp_web_usuario_apoderado
              @codigo = #{dto.codigo, mode=IN, jdbcType=VARCHAR},
              @tipoDocumento = #{dto.tipoDocumento, mode=IN, jdbcType=VARCHAR},
              @numDocumento = #{dto.numDocumento, mode=IN, jdbcType=VARCHAR},
              @passwordHash = #{dto.passwordHash, mode=IN, jdbcType=VARCHAR},
              @operador = #{dto.operador, mode=IN, jdbcType=VARCHAR},
              @estacion = #{dto.estacion, mode=IN, jdbcType=VARCHAR};
     """)
    @Options(statementType = StatementType.CALLABLE)
    ApoderadoResponse registrarApoderado(@Param("dto") ApoderadoRequest dto);
}
