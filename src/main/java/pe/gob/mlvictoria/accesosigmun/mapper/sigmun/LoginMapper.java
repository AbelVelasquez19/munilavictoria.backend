package pe.gob.mlvictoria.accesosigmun.mapper.sigmun;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pe.gob.mlvictoria.accesosigmun.dto.LogoutRequestDTO;
import pe.gob.mlvictoria.accesosigmun.model.Logout;

@Mapper
public interface LoginMapper {
    @Select(""" 
           exec Acceso.sp_LogOut
            @buscar = #{dto.buscar},
            @parametro= #{dto.parametro},
            @password= #{dto.password};
    """)
    Logout logout (@Param("dto")LogoutRequestDTO dto);
}
