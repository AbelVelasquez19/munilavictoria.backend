package pe.gob.mlvictoria.pagolinea.mapper;

import org.apache.ibatis.annotations.*;
import pe.gob.mlvictoria.pagolinea.dto.AnioPenContriRespondeDTO;
import pe.gob.mlvictoria.pagolinea.dto.ContribuyenteDTO;
import pe.gob.mlvictoria.pagolinea.dto.EnviarCodigoRecuperacionResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.RecuperarContrasenaResponseDTO;
import pe.gob.mlvictoria.pagolinea.model.ContribuyenteLogin;

@Mapper
public interface ContribuyenteMapper {
    @Select("""
        EXEC Rentas.recuperar_contrasena
            @opcion = #{dto.opcion},
            @codigo = #{dto.codigo},
            @num_docu = #{dto.numDocu};
    """)
    @Results({
        @Result(property = "rescode", column = "rescode"),
        @Result(property = "message", column = "message"),
        @Result(property = "correoEnmascarado", column = "correo_enmascarado"),
        @Result(property = "email", column = "email")
    })
    RecuperarContrasenaResponseDTO recuperarContrasena (@Param("dto") ContribuyenteDTO dto);

    @Select("""
        EXEC Rentas.recuperar_contrasena
            @opcion = #{dto.opcion},
            @codigo = #{dto.codigo},
            @num_docu = #{dto.numDocu};
    """)
    @Results({
       @Result(property = "rescode",column = "rescode"),
       @Result(property = "message",column = "message"),
       @Result(property = "val",column = "val"),
       @Result(property = "email",column = "email"),
       @Result(property = "enfriamiento",column = "enfriamiento")
    })
    EnviarCodigoRecuperacionResponseDTO enviarCodigoRecuperacion (@Param("dto") ContribuyenteDTO dto);

    @Select("""
        EXEC Rentas.recuperar_contrasena
            @opcion = #{dto.opcion},
            @codigo = #{dto.codigo},
            @email = #{dto.email},
            @num_docu = #{dto.numDocu},
            @telefono = #{dto.telefono};
    """)
    @Results({
            @Result(property = "rescode",column = "rescode"),
            @Result(property = "message",column = "message"),
            @Result(property = "val",column = "val"),
            @Result(property = "email",column = "email"),
            @Result(property = "enfriamiento",column = "enfriamiento")
    })
    EnviarCodigoRecuperacionResponseDTO actualizarRecuperarContrasena (@Param("dto") ContribuyenteDTO dto);

    @Select("""
        EXEC Rentas.recuperar_contrasena
            @opcion = #{dto.opcion},
            @codigo_confirmacion = #{dto.codigoConfirmacion};
    """)
    @Results({
            @Result(property = "rescode",column = "rescode"),
            @Result(property = "message",column = "message"),
            @Result(property = "codigo",column = "codigo"),
            @Result(property = "val",column = "val"),
            @Result(property = "email",column = "email")
    })
    EnviarCodigoRecuperacionResponseDTO enviaNuevaContrasena (@Param("dto") ContribuyenteDTO dto);

    @Select("SELECT codigo, password,dniruc, num_doc,nombres " +
            "FROM dbo.login_contrib " +
            "WHERE codigo = #{codigo} AND password = #{password}")
    ContribuyenteLogin login(@Param("codigo") String codigo, @Param("password") String password);

    @Select("""
            EXEC store_caja_framework
            @msquery = #{dto.msquery},
            @codigo = #{dto.codigo}
            """)
    @Results({
            @Result(property = "codigo",column = "codigo"),
            @Result(property = "minimo",column = "minimo"),
            @Result(property = "maximo",column = "maximo")
    })
    AnioPenContriRespondeDTO annioPendientePagar (@Param("dto") AnioPenContriRespondeDTO dto);
}
