package pe.gob.mlvictoria.fiscalizacioncontrol.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.registrarnotificacion.RegistrarNotificacionRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.registrarnotificacion.RegistrarNotificacionResponseDTO;

import java.util.List;

@Mapper
public interface RegistrarNotificacionMapper {
    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        { CALL califica.sp_mantnotif_2025(
            #{dto.busc, mode=IN, jdbcType=INTEGER},
            #{dto.FATIPNOTIF, mode=IN, jdbcType=CHAR},
            #{dto.FAANONOTIF, mode=IN, jdbcType=CHAR},
            #{dto.FANRONOTIF, mode=IN, jdbcType=CHAR},
            #{dto.FACODAREA, mode=IN, jdbcType=CHAR},
            #{dto.FACODCONTR, mode=IN, jdbcType=CHAR},
            #{dto.FATIPVIA, mode=IN, jdbcType=CHAR},
            #{dto.FACODVIA, mode=IN, jdbcType=CHAR},
            #{dto.FANUMERO, mode=IN, jdbcType=CHAR},
            #{dto.FADEPARTAM, mode=IN, jdbcType=CHAR},
            #{dto.FAMANZANA, mode=IN, jdbcType=CHAR},
            #{dto.FALOTE, mode=IN, jdbcType=CHAR},
            #{dto.FAREFERENC, mode=IN, jdbcType=VARCHAR},
            #{dto.FATELEFONO, mode=IN, jdbcType=VARCHAR},
            #{dto.FAHORNOTIF, mode=IN, jdbcType=CHAR},
            #{dto.FDFECNOTIF, mode=IN, jdbcType=TIMESTAMP},
            #{dto.FACODGIRO, mode=IN, jdbcType=CHAR},
            #{dto.FANROVIGEN, mode=IN, jdbcType=CHAR},
            #{dto.FACODMULTA, mode=IN, jdbcType=CHAR},
            #{dto.FNTOTAL, mode=IN, jdbcType=DECIMAL},
            #{dto.FACODINSPE, mode=IN, jdbcType=CHAR},
            #{dto.FACALNOTIF, mode=IN, jdbcType=CHAR},
            #{dto.FMOBSERVAC, mode=IN, jdbcType=LONGVARCHAR},
            #{dto.FAOPERADOR, mode=IN, jdbcType=CHAR},
            #{dto.FAESTACION, mode=IN, jdbcType=CHAR},
            #{dto.FAPRESENTO, mode=IN, jdbcType=CHAR},
            #{dto.FADOCUMENTO, mode=IN, jdbcType=CHAR},
            #{dto.FAANODOCUM, mode=IN, jdbcType=CHAR},
            #{dto.FACODAREAINF, mode=IN, jdbcType=CHAR},
            #{dto.ZONIFICACION, mode=IN, jdbcType=VARCHAR},
            #{dto.FASUBMULTA, mode=IN, jdbcType=CHAR},
            #{dto.FNCANTIDAD, mode=IN, jdbcType=INTEGER},
            #{dto.FNIMPMULTA, mode=IN, jdbcType=DECIMAL},
            #{dto.FACODBASE, mode=IN, jdbcType=CHAR},
            #{dto.FACODCONTI, mode=IN, jdbcType=CHAR},
            #{dto.FAANONOTIFC, mode=IN, jdbcType=CHAR},
            #{dto.FANRONOTIFC, mode=IN, jdbcType=CHAR},
            #{dto.FACODLINEA, mode=IN, jdbcType=CHAR},
            #{dto.FATIPVIA2, mode=IN, jdbcType=CHAR},
            #{dto.FACODVIA2, mode=IN, jdbcType=CHAR},
            #{dto.FANUMERO2, mode=IN, jdbcType=CHAR},
            #{dto.FADEPARTAM2, mode=IN, jdbcType=CHAR},
            #{dto.FAMANZANA2, mode=IN, jdbcType=CHAR},
            #{dto.FALOTE2, mode=IN, jdbcType=CHAR},
            #{dto.FAREFERENC2, mode=IN, jdbcType=VARCHAR},
            #{dto.FACODDISTR, mode=IN, jdbcType=CHAR},
            #{dto.FANOMVIA, mode=IN, jdbcType=VARCHAR},
            #{dto.tipo, mode=IN, jdbcType=CHAR},
            #{dto.NROIFI, mode=IN, jdbcType=VARCHAR},
            #{dto.FECHIFI, mode=IN, jdbcType=DATE},
            #{dto.NRONOTIF, mode=IN, jdbcType=INTEGER},
            #{dto.idordenanza, mode=IN, jdbcType=INTEGER},
            #{dto.area, mode=IN, jdbcType=VARCHAR},
            #{dto.latitud, mode=IN, jdbcType=VARCHAR},
            #{dto.longitud, mode=IN, jdbcType=VARCHAR},
            #{dto.fotoFrontal, mode=IN, jdbcType=VARCHAR},
            #{dto.fotoPlaca, mode=IN, jdbcType=VARCHAR},
            #{dto.video, mode=IN, jdbcType=VARCHAR},
            #{dto.placa, mode=IN, jdbcType=VARCHAR},
            #{dto.marca, mode=IN, jdbcType=VARCHAR},
            #{dto.color, mode=IN, jdbcType=VARCHAR}
        ) }
    """)
    RegistrarNotificacionResponseDTO  registrarNotificacion(@Param("dto") RegistrarNotificacionRequestDTO dto);
}
