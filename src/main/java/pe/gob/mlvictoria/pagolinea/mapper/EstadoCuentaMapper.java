package pe.gob.mlvictoria.pagolinea.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.EstadoCuentaRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.EstadoCuentaResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.ImprimirEstadoCuentaRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.ImprimirEstadoCuentaResponseDTO;

import java.util.List;

@Mapper
public interface EstadoCuentaMapper {

    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC Caja.sp_Imprime_EstCta_Detallado_02_GGGD
            @buscar = #{dto.buscar},
            @codigo = #{dto.codigo},
            @resumen = #{dto.resumen},
            @detalle = #{dto.detalle},
            @agrupar = #{dto.agrupar},
            @annos = #{dto.annos},
            @tipos = #{dto.tipos},
            @tiporec = #{dto.tiporec},
            @perio = #{dto.perio},
            @predio = #{dto.predio},
            @estado = #{dto.estado},
            @criterio = #{dto.criterio},
            @idrecibo = #{dto.idrecibo};
    """)
    List<EstadoCuentaResponseDTO> listarEstadoCuenta (@Param("dto") EstadoCuentaRequestDTO dto);

    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC Caja.sp_Imprime_EstCta_Detallado_02_manuel
            @buscar = #{dto.buscar},
            @codigo = #{dto.codigo},
            @resumen = #{dto.resumen},
            @detalle = #{dto.detalle},
            @agrupar = #{dto.agrupar},
            @annos = #{dto.annos},
            @tipos = #{dto.tipos},
            @tiporec = #{dto.tiporec},
            @perio = #{dto.perio},
            @predio = #{dto.predio},
            @estado = #{dto.estado},
            @criterio = #{dto.criterio},
            @idrecibo = #{dto.idrecibo}
    """)
    List<ImprimirEstadoCuentaResponseDTO> imprimirEstadoCuenta(@Param("dto") ImprimirEstadoCuentaRequestDTO dto);
}
