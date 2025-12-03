package pe.gob.mlvictoria.complejo.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.complejo.dto.tarifa.CalcularTarifaRequest;
import pe.gob.mlvictoria.complejo.dto.tarifa.CalcularTarifaResponse;

@Mapper
public interface TarifaMapper {
    @Select("""
         EXEC complejoDeportivo.sp_calcular_tarifa
              @idCancha = #{dto.idCancha, mode=IN, jdbcType=INTEGER},
              @fecha = #{dto.fecha, mode=IN, jdbcType=VARCHAR},
              @horarios = #{dto.horarios, mode=IN, jdbcType=VARCHAR};
        """)
    @Options(statementType = StatementType.CALLABLE)
    CalcularTarifaResponse calcularTarifa(@Param("dto") CalcularTarifaRequest dto);
}
