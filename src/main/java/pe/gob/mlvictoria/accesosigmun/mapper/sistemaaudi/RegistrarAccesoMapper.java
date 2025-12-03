package pe.gob.mlvictoria.accesosigmun.mapper.sistemaaudi;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pe.gob.mlvictoria.accesosigmun.dto.RegistrarAccesoRequestDTO;

@Mapper
public interface RegistrarAccesoMapper {

    @Select(""" 
           exec Acceso.sp_registra_acceso
            @opcion    = #{dto.opcion},
            @idusuario = #{dto.idUsuario},
            @parametro = #{dto.parametro},
            @estacion = #{dto.estacion},
            @idsistema = #{dto.idsistema};
    """)
    int registrarAcceso (@Param("dto") RegistrarAccesoRequestDTO dto);
}
