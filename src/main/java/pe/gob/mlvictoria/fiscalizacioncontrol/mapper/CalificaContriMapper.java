package pe.gob.mlvictoria.fiscalizacioncontrol.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listado.CalificaContriRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listado.CalificaContriResponseDTO;

import java.util.List;

@Mapper
public interface CalificaContriMapper {

    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC califica.sp_califica_contribuyente
        @busc = #{dto.busc},
        @inicio = #{dto.inicio},
        @final = #{dto.finalP},
        @codigo = #{dto.codigo},
        @docu = #{dto.docu},
        @nombre = #{dto.nombre},
        @notif = #{dto.notif},
        @multa = #{dto.multa};
    """)
    List<CalificaContriResponseDTO> listaCalificaContribuyente(@Param("dto") CalificaContriRequestDTO dto);
}
