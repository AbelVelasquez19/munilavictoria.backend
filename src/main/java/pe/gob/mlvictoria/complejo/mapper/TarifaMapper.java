package pe.gob.mlvictoria.complejo.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.complejo.dto.tarifa.*;

import java.util.List;

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

    @Select("""
         EXEC complejoDeportivo.sp_admin_tarifa_listar
            @idComplejo = #{dto.idComplejo, mode=IN, jdbcType=INTEGER},
            @idCancha = #{dto.idCancha, mode=IN, jdbcType=INTEGER},
            @diaSemana = #{dto.diaSemana, mode=IN, jdbcType=INTEGER},
            @tipoHorario = #{dto.tipoHorario, mode=IN, jdbcType=VARCHAR},
            @estado = #{dto.estado, mode=IN, jdbcType=INTEGER},
            @numeroPagina = #{dto.numeroPagina, mode=IN, jdbcType=INTEGER},
            @totalPagina = #{dto.totalPagina, mode=IN, jdbcType=INTEGER};
        """)
    @Options(statementType = StatementType.CALLABLE)
    List<ListarTarrifaResponse> listarTarifa(@Param("dto") ListarTarifaRequest dto);

    @Update("""
        UPDATE complejoDeportivo.tarifa
        SET estado = #{estado}
        WHERE idTarifa = #{idTarifa}
    """)
    int actualizarEstadoTarifa(
       @Param("idTarifa") Integer idTarifa,
       @Param("estado") Integer estado
    );

    @Update("""
    <script>
    UPDATE complejoDeportivo.tarifa
    <set>
      <if test="dto.idCancha != null">idCancha = #{dto.idCancha},</if>
      <if test="dto.diaSemana != null">diaSemana = #{dto.diaSemana},</if>
      <if test="dto.tipoHorario != null">tipoHorario = #{dto.tipoHorario},</if>
      <if test="dto.horaInicio != null">horaInicio = #{dto.horaInicio},</if>
      <if test="dto.horaFin != null">horaFin = #{dto.horaFin},</if>
      <if test="dto.precio != null">precio = #{dto.precio},</if>
      <if test="dto.estado != null">estado = #{dto.estado},</if>
      <if test="dto.codTasa != null">codTasa = #{dto.codTasa},</if>
      <if test="dto.tipoTasa != null">tipoTasa = #{dto.tipoTasa},</if>
    </set>
    WHERE idTarifa = #{dto.idTarifa}
    </script>
    """)
    int actualizarTarifa(@Param("dto") ActualizarTarifaRequest dto);
}
