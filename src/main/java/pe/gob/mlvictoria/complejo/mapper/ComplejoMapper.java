package pe.gob.mlvictoria.complejo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.complejo.dto.complejo.ComplejoRequest;
import pe.gob.mlvictoria.complejo.dto.complejo.ComplejoResponse;

import java.util.List;

@Mapper
public interface ComplejoMapper {
    @Select("""
       EXEC complejoDeportivo.sp_complejo
       @opcion = #{dto.opcion, mode=IN, jdbcType=INTEGER};
     """)
    @Options(statementType = StatementType.CALLABLE)
    List<ComplejoResponse> listarComplejo(@Param("dto") ComplejoRequest dto);
}
