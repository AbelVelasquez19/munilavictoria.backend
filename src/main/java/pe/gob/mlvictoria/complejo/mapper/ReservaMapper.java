package pe.gob.mlvictoria.complejo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.complejo.dto.reserva.*;

@Mapper
public interface ReservaMapper {
    @Select("""
         EXEC complejoDeportivo.sp_registrar_reserva
            @idAdministrado = #{dto.idAdministrado, mode=IN, jdbcType=INTEGER},
            @idCancha = #{dto.idCancha, mode=IN, jdbcType=INTEGER},
            @fecha = #{dto.fecha, mode=IN, jdbcType=VARCHAR},
            @horarios = #{dto.horarios, mode=IN, jdbcType=VARCHAR},
            @montoTotal = #{dto.montoTotal, mode=IN, jdbcType=DECIMAL},
            @cantidadHoras = #{dto.cantidadHoras, mode=IN, jdbcType=INTEGER},
            @operador = #{dto.operador, mode=IN, jdbcType=VARCHAR},
            @estacion = #{dto.estacion, mode=IN, jdbcType=VARCHAR};
        """)
    @Options(statementType = StatementType.CALLABLE)
    ReservaRegistrarResponse registrarReserva(@Param("dto") ReservaRegistrarRequest dto);

    @Select("""
        EXEC complejoDeportivo.sp_cancelar_reserva
            @opcion = #{dto.opcion, mode=IN, jdbcType=INTEGER},
            @idReserva = #{dto.idReserva, mode=IN, jdbcType=INTEGER};
    """)
    @Options(statementType = StatementType.CALLABLE)
    ReservaCancelarResponse cancelarReserva(@Param("dto") ReservaCancelarRequest dto);

    @Select("""
        EXEC complejoDeportivo.sp_detalle_reserva
            @idReserva = #{dto.idReserva, mode=IN, jdbcType=INTEGER};
    """)
    @Options(statementType = StatementType.CALLABLE)
    ReservaDetalleResponse detalleReserva(@Param("dto") ReservaDetalleRequest dto);

    @Select("""
        EXEC complejoDeportivo.sp_liberar_reservas_expiradas
    """)
    @Options(statementType = StatementType.CALLABLE)
    ReservaHrsLibResponse liberarReservaExpirada();

    @Select("""
        EXEC complejoDeportivo.sp_actualizar_reserva
        @idReserva = #{idReserva},
        @idRecibo = #{idRecibo};
    """)
    @Options(statementType = StatementType.CALLABLE)
    int pagarReserva(@Param("idReserva") int idReserva,@Param("idRecibo") int idRecibo);

    @Options(statementType = StatementType.CALLABLE)
    @Select("""
    DECLARE @rows INT;
    EXEC complejoDeportivo.sp_actualizar_recibo_niubiz
      @codigo          = #{dto.codigo},
      @intento           = #{dto.intento},
      @purchase_number   = #{dto.purchaseNumber},
      @tokenId           = #{dto.tokenId},
      @status            = #{dto.status},
      @ordenanza         = #{dto.ordenanza},
      @authRawJson       = #{dto.authRawJson},
      @idRecibo       = #{dto.idRecibo},
      @rowsAffected      = @rows OUTPUT;
      SELECT @rows AS rowsAffected;
    """)
    Integer actualizarDespuesDeAuth(@Param("dto") ActuReciNiubizRequest dto);
}
