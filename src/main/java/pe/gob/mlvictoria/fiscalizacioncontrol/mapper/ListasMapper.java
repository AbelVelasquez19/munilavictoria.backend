package pe.gob.mlvictoria.fiscalizacioncontrol.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listas.ListasRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listas.ListasRespondeDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listas.ReporteNotificacionRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listas.ReporteNotificacionResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.numeronotificacion.NumeroNotificacionRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.numeronotificacion.NumeroNotificacionResponseDTO;

import java.util.List;

@Mapper
public interface ListasMapper {

    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC califica.sp_numero_notificacion
        @busc = #{dto.busc},
        @anno = #{dto.anno},
        @idordenanza = #{dto.idordenanza},
        @area = #{dto.area};
    """)
    NumeroNotificacionResponseDTO NumeroNotificacion(@Param("dto") NumeroNotificacionRequestDTO dto);

    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC califica.sp_combos
        @busc = #{dto.busc},
        @valor01 = #{dto.valor01};
    """)
    List<ListasRespondeDTO> listas(@Param("dto") ListasRequestDTO dto);

    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC califica.notificiacion_cargo_mobilidad_urbano
        @fecIniNoti = #{dto.fecIniNoti},
        @fecFinNoti = #{dto.fecFinNoti},
        @inspector = #{dto.inspector};
    """)
    List<ReporteNotificacionResponseDTO> imprimirCargoActa(@Param("dto") ReporteNotificacionRequestDTO dto);
}
