package pe.gob.mlvictoria.pagolinea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.pagolinea.dto.historialpago.HistorialPagoResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.DatosContriRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.recibo.ImprimirReciboRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.recibo.ImprimirReciboResponseDTO;

import java.util.List;

@Mapper
public interface HistorialPagoMapper {
    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC dbo.pxConsultasWeb2
        @msquery = #{dto.opcion},
        @paramt1 = #{dto.codigo},
        @paramt5 = #{dto.paramt5}
    """)
    List<HistorialPagoResponseDTO> annosRecibosPagados (@Param("dto") DatosContriRequestDTO dto);

    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC Caja.sp_Recibos_emitidos
        @buscar = #{dto.buscar},
        @codigo = #{dto.codigo},
        @num_ingr = #{dto.numIngr}
    """)
    List<ImprimirReciboResponseDTO> imprimirRecibo (@Param("dto")ImprimirReciboRequestDTO dto);
}
