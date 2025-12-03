package pe.gob.mlvictoria.fiscalizacioncontrol.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.tipourbanizacion.TipoUrbaniazacionResponse;

import java.util.List;

@Mapper
public interface TipoUrbanizacionMapper {
    @Select("{CALL Rentas.SP_MCUrba(#{msquery, mode=IN, jdbcType=VARCHAR})}")
    @Options(statementType = StatementType.CALLABLE)
    List<TipoUrbaniazacionResponse> listarTipoUrbanizacion(@Param("msquery") String msquery);
}
