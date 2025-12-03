package pe.gob.mlvictoria.complejo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.complejo.dto.cancha.CanchaRequest;
import pe.gob.mlvictoria.complejo.dto.cancha.CanchaResponse;

import java.util.List;

@Mapper
public interface CanchaMapper {
    @Select("""
       EXEC complejoDeportivo.sp_cancha
         @opcion = #{dto.opcion, mode=IN, jdbcType=VARCHAR},
         @idComplejo = #{dto.idComplejo, mode=IN, jdbcType=VARCHAR}
     """)
    @Options(statementType = StatementType.CALLABLE)
   List<CanchaResponse> listarCancha(@Param("dto") CanchaRequest dto);
}
