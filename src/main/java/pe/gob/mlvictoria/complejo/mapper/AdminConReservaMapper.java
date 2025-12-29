package pe.gob.mlvictoria.complejo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.complejo.dto.adminreserva.*;

import java.util.List;

@Mapper
public interface AdminConReservaMapper {
    @Select("""
        EXEC complejoDeportivo.sp_admin_reservas_panel
        @fecha = #{dto.fecha, mode=IN, jdbcType=VARCHAR},
        @idComplejo = #{dto.idComplejo, mode=IN, jdbcType=INTEGER},
        @idCancha = #{dto.idCancha, mode=IN, jdbcType=INTEGER};
    """)
    @Options(statementType = StatementType.CALLABLE)
    List<AdminConReservaResponse> listarReservasDeportivo(@Param("dto")AdminConReservaRequest dto);

    @Select("""
        EXEC complejoDeportivo.sp_admin_generar_horarios_masivo
        @idComplejo = #{dto.idComplejo, mode=IN, jdbcType=INTEGER},
        @listaCanchas = #{dto.listaCanchas, mode=IN, jdbcType=VARCHAR},
        @listaFechas = #{dto.listaFechas, mode=IN, jdbcType=VARCHAR},
        @operador = #{dto.operador, mode=IN, jdbcType=VARCHAR},
        @estacion = #{dto.estacion, mode=IN, jdbcType=VARCHAR};
    """)
    @Options(statementType = StatementType.CALLABLE)
    HorarioMasivaResponsive generarHorarioMasiva(@Param("dto") HorarioMasivaRequest dto);

    @Select("""
        EXEC complejoDeportivo.sp_admin_cambiar_estado_horario
        @idEstadoCancha = #{dto.idEstadoCancha, mode=IN, jdbcType=INTEGER},
        @nuevoEstado = #{dto.nuevoEstado, mode=IN, jdbcType=VARCHAR},
        @operador = #{dto.operador, mode=IN, jdbcType=VARCHAR},
        @estacion = #{dto.estacion, mode=IN, jdbcType=VARCHAR};
    """)
    @Options(statementType = StatementType.CALLABLE)
    CambiarEstadoHorarioResponse cambiarEstadoHorario(@Param("dto") CambiarEstadoHorarioRequest dto);
}
