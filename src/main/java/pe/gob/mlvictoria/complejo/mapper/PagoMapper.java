package pe.gob.mlvictoria.complejo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.complejo.dto.pago.BuscarTokenRequest;
import pe.gob.mlvictoria.complejo.dto.pago.BuscarTokenResponse;
import pe.gob.mlvictoria.complejo.dto.pago.TicketAprobadoRequest;
import pe.gob.mlvictoria.complejo.dto.pago.TicketAprobadoResponse;


@Mapper
public interface PagoMapper {
    @Select("""  
        EXEC complejoDeportivo.sp_consulta_pago
        @idToken = #{dto.idToken, mode=IN, jdbcType=VARCHAR};
    """)
    @Options(statementType = StatementType.CALLABLE)
    BuscarTokenResponse buscarTokenPago(@Param("dto") BuscarTokenRequest dto);

    @Select("""  
        EXEC complejoDeportivo.sp_generar_ticket_aprobado
        @token_id = #{dto.idToken, mode=IN, jdbcType=VARCHAR};
    """)
    @Options(statementType = StatementType.CALLABLE)
    TicketAprobadoResponse ticketAprobado(@Param("dto") TicketAprobadoRequest dto);
}
