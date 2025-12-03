package pe.gob.mlvictoria.consultapide.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.consultapide.dto.DatosPersonaLocalDTO;
import pe.gob.mlvictoria.consultapide.dto.ReniecRegistroResponseDTO;
import pe.gob.mlvictoria.consultapide.model.PideReniec;

import java.util.Map;

@Mapper
public interface ReniecMapper {
    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC personal.sp_registrar_consulta_reniec
            @numDocumento    = #{dto.numDocumento},
            @apPrimer        = #{dto.apPrimer},
            @apSegundo       = #{dto.apSegundo},
            @perNombres      = #{dto.perNombres},
            @direccion       = #{dto.direccion},
            @estadoCivil     = #{dto.estadoCivil},
            @foto            = #{dto.foto},
            @restriccion     = #{dto.restriccion},
            @ubigeo          = #{dto.ubigeo},
            @ipMaquina       = #{dto.ipMaquina},
            @navegador       = #{dto.navegador},
            @userLogin       = #{dto.userLogin},
            @dniAccesoReniec = #{dto.dniAccesoReniec},
            @modulo          = #{dto.modulo},
            @plataforma      = #{dto.plataforma},
            @consulta        = #{dto.consulta}
    """)
    ReniecRegistroResponseDTO registrarConsulta(@Param("dto") PideReniec requestDTO);

    @Select("""
        SELECT TOP 1 numDocumento,apPrimer,apSegundo,perNombres,direccion,estadoCivil,foto,restriccion,ubigeo
        FROM personal.pide_reniec
        WHERE numDocumento = #{numDocumento}
        """)
    DatosPersonaLocalDTO buscarPorDni(String numDocumento);
}
