package pe.gob.mlvictoria.fiscalizacioncontrol.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.distrito.DistritoResponse;

import java.util.List;

@Mapper
public interface DistritoMapper {
    @Select("{CALL Contenedor.SP_TblDistrito(#{msquery, mode=IN, jdbcType=VARCHAR})}")
    @Options(statementType = StatementType.CALLABLE)
    List<DistritoResponse> listarDistrito(@Param("msquery") String msquery);
}
