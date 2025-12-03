package pe.gob.mlvictoria.complejo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.complejo.dto.horario.HorarioRequest;
import pe.gob.mlvictoria.complejo.dto.horario.HorarioResponse;

import java.util.List;

@Mapper
public interface HorarioMapper {
    @Select("""
         EXEC complejoDeportivo.sp_listar_horarios_cancha
              @idCancha = #{dto.idCancha, mode=IN, jdbcType=INTEGER},
              @fecha = #{dto.fecha, mode=IN, jdbcType=VARCHAR};
        """)
    @Options(statementType = StatementType.CALLABLE)
    List<HorarioResponse> listarHorariosCancha(@Param("dto") HorarioRequest dto);
}
