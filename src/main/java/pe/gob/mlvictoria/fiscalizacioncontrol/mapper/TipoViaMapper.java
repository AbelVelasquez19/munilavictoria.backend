package pe.gob.mlvictoria.fiscalizacioncontrol.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.tipovia.TipoViaResponse;

import java.util.List;

@Mapper
public interface TipoViaMapper {
    @Select("{CALL Rentas.sp_MVias(#{msquery, mode=IN, jdbcType=VARCHAR})}")
    @Options(statementType = StatementType.CALLABLE)
    List<TipoViaResponse> listarTipoVia(@Param("msquery") String msquery);
}
