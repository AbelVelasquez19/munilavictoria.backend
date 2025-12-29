package pe.gob.mlvictoria.talleres.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.talleres.dto.login.LoginRequest;
import pe.gob.mlvictoria.talleres.dto.login.LoginResponse;

@Mapper
public interface LoginTalleresMapper {

    @Select("""
        EXEC talleres.sp_web_login
              @username = #{dto.numDocumento, mode=IN, jdbcType=VARCHAR};
    """)
    @Options(statementType = StatementType.CALLABLE)
    LoginResponse loginTalleres(@Param("dto") LoginRequest dto);
}
