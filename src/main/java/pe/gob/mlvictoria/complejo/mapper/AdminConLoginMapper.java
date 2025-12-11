package pe.gob.mlvictoria.complejo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.complejo.dto.adminlogin.AdminComLoginRequest;
import pe.gob.mlvictoria.complejo.dto.adminlogin.AdminComLoginResponse;

@Mapper
public interface AdminConLoginMapper {
    @Select("""
        EXEC complejoDeportivo.sp_admin_login
              @numDocumento = #{dto.numDocumento, mode=IN, jdbcType=VARCHAR};
    """)
    @Options(statementType = StatementType.CALLABLE)
    AdminComLoginResponse loginComplejoDeportivo(@Param("dto") AdminComLoginRequest dto);
}
