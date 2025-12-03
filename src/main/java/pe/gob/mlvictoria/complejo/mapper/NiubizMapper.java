package pe.gob.mlvictoria.complejo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.complejo.dto.niubiz.*;

import java.util.List;

@Mapper
public interface NiubizMapper {
    @Select("""
        EXEC complejoDeportivo.sp_token_storage_crear
        @opcion = #{dto.opcion, mode=IN, jdbcType=INTEGER},
        @accessToken = #{dto.accessToken, mode=IN, jdbcType=VARCHAR},
        @transactionToken = #{dto.transactionToken, mode=IN, jdbcType=VARCHAR},
        @purchasNumber = #{dto.purchasNumber, mode=IN, jdbcType=VARCHAR},
        @amount = #{dto.amount, mode=IN, jdbcType=DECIMAL},
        @estado	 = #{dto.estado, mode=IN, jdbcType=VARCHAR},
        @idToken = #{dto.idToken, mode=IN, jdbcType=VARCHAR};
    """)
    @Options(statementType = StatementType.CALLABLE)
    NiubizResponse tokenStorage(@Param("dto") NiubizRequest dto);

    @Select("""  
        EXEC caja.sp_CajaRecibosWeb
        @msquery = #{dto.msquery, mode=IN, jdbcType=INTEGER},
        @codigo = #{dto.codigo, mode=IN, jdbcType=VARCHAR},
        @anno = #{dto.anno, mode=IN, jdbcType=VARCHAR},
        @num_docu = #{dto.numDocu, mode=IN, jdbcType=VARCHAR},
        @tipo = #{dto.tipo, mode=IN, jdbcType=VARCHAR},
        @tipo_rec = #{dto.tipoRec, mode=IN, jdbcType=VARCHAR},
        @periodo = #{dto.periodo, mode=IN, jdbcType=VARCHAR},
        @imp_insol = #{dto.impInsol, mode=IN, jdbcType=DECIMAL},
        @fact_reaj = #{dto.impInsol, mode=IN, jdbcType=DECIMAL},
        @observacion = #{dto.observacion, mode=IN, jdbcType=VARCHAR},
        @fec_venc = #{dto.fecVenc, mode=IN, jdbcType=VARCHAR},
        @operador = #{dto.operador, mode=IN, jdbcType=VARCHAR},
        @estacion = #{dto.estacion, mode=IN, jdbcType=VARCHAR};
    """)
    @Options(statementType = StatementType.CALLABLE)
    int cajaRecibosWeb(@Param("dto") ReciboGenerarRequest dto);

    @Select("""  
        EXEC Caja.sp_EstCta
        @codigo = #{dto.codigo, mode=IN, jdbcType=VARCHAR},
        @idrecibo_group = #{dto.idreciboGroup, mode=IN, jdbcType=VARCHAR};
    """)
    @Options(statementType = StatementType.CALLABLE)
    List<EstadoCuentaResponse> estadoCuenta(@Param("dto") EstadoCuentaRequest dto);
}
